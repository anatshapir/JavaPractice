package com.school.eclipse.student.views;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.school.eclipse.student.model.ExerciseDescriptor;
import com.school.eclipse.student.model.TestDescriptor;
import com.school.eclipse.student.services.ExerciseActionService;
import com.school.eclipse.student.services.ExerciseRepository;

public class ExerciseOverviewView extends ViewPart {
    public static final String ID = "com.school.eclipse.student.views.ExerciseOverviewView";

    private final ExerciseRepository repository = new ExerciseRepository();
    private final ExerciseActionService actions = new ExerciseActionService();

    private ListViewer exerciseViewer;
    private Label exerciseTitle;
    private Text summaryText;
    private org.eclipse.swt.widgets.List instructionsList;
    private org.eclipse.swt.widgets.List testsList;
    private Label workspaceProjectLabel;
    private Label starterFileLabel;
    private Label testLauncherLabel;
    private Button runTestsButton;
    private Button aiHelperButton;
    private Button submitButton;
    private Button openStarterButton;


    private ExerciseDescriptor currentExercise;

    @Override
    public void createPartControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new GridLayout(2, false));

        createExerciseList(container);
        createDetailPanel(container);

        refreshExercises();
    }

    private void createExerciseList(Composite parent) {
        Group listGroup = new Group(parent, SWT.NONE);
        listGroup.setText("Exercises");
        listGroup.setLayout(new GridLayout());
        listGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));

        exerciseViewer = new ListViewer(listGroup, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL);
        exerciseViewer.getList().setLayoutData(new GridData(240, 300));
        exerciseViewer.setContentProvider(ArrayContentProvider.getInstance());
        exerciseViewer.setLabelProvider(new ExerciseLabelProvider());
        exerciseViewer.setComparator(new ViewerComparator());
        exerciseViewer.addSelectionChangedListener(event -> {
            IStructuredSelection selection = event.getStructuredSelection();
            ExerciseDescriptor exercise = (ExerciseDescriptor) selection.getFirstElement();
            updateCurrentExercise(exercise);
        });
    }

    private void createDetailPanel(Composite parent) {
        Composite details = new Composite(parent, SWT.NONE);
        details.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        details.setLayout(new GridLayout(1, false));

        exerciseTitle = new Label(details, SWT.WRAP);
        exerciseTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        exerciseTitle.setText("Select an exercise to view the details.");

        summaryText = new Text(details, SWT.WRAP | SWT.MULTI | SWT.READ_ONLY | SWT.V_SCROLL);
        GridData summaryData = new GridData(SWT.FILL, SWT.TOP, true, false);
        summaryData.heightHint = 70;
        summaryText.setLayoutData(summaryData);
        summaryText.setBackground(details.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
        summaryText.setText(
                "Choose an exercise from the list to see a summary, step-by-step instructions,"
                        + " associated test files, and quick actions for the AI helper and submissions.");

        Group instructionsGroup = new Group(details, SWT.NONE);
        instructionsGroup.setText("Instructions");
        instructionsGroup.setLayout(new GridLayout());
        instructionsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        instructionsList = new org.eclipse.swt.widgets.List(instructionsGroup,
                SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
        instructionsList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        Group workspaceGroup = new Group(details, SWT.NONE);
        workspaceGroup.setText("Workspace");
        workspaceGroup.setLayout(new GridLayout(2, false));
        workspaceGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

        new Label(workspaceGroup, SWT.NONE).setText("Project:");
        workspaceProjectLabel = new Label(workspaceGroup, SWT.NONE);
        workspaceProjectLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        workspaceProjectLabel.setText("—");

        new Label(workspaceGroup, SWT.NONE).setText("Starter File:");
        starterFileLabel = new Label(workspaceGroup, SWT.WRAP);
        starterFileLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        starterFileLabel.setText("—");

        new Label(workspaceGroup, SWT.NONE).setText("JUnit Launcher:");
        testLauncherLabel = new Label(workspaceGroup, SWT.NONE);
        testLauncherLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        testLauncherLabel.setText("—");

        GridLayout actionsLayout = new GridLayout(4, false);
        actionsLayout.marginWidth = 0;
        actionsComposite.setLayout(actionsLayout);

        openStarterButton = createActionButton(actionsComposite, "Open Starter File", shell -> {
            if (currentExercise != null) {
                actions.openStarterFile(shell, currentExercise);
            }
        });


        runTestsButton = createActionButton(actionsComposite, "Run Tests", shell -> {
            if (currentExercise != null) {
                actions.runTests(shell, currentExercise);
            }
        });

        aiHelperButton = createActionButton(actionsComposite, "Open AI Helper", shell -> {
            if (currentExercise != null) {
                actions.openAiHelper(shell, currentExercise);
            }
        });

        submitButton = createActionButton(actionsComposite, "Submit", shell -> {
            if (currentExercise != null) {
                actions.submitToClassroom(shell, currentExercise);
            }
        });

        updateActionEnablement();
    }

    private Button createActionButton(Composite parent, String label, ShellConsumer consumer) {
        Button button = new Button(parent, SWT.PUSH);
        button.setText(label);
        button.addListener(SWT.Selection, event -> consumer.accept(parent.getShell()));
        return button;
    }

    private void refreshExercises() {
        List<ExerciseDescriptor> exercises = repository.loadExercises();
        exerciseViewer.setInput(exercises);
        if (!exercises.isEmpty()) {
            ExerciseDescriptor first = exercises.get(0);
            exerciseViewer.setSelection(new StructuredSelection(first), true);
            updateCurrentExercise(first);
        } else {
            updateCurrentExercise(null);
        }
    }

    private void updateCurrentExercise(ExerciseDescriptor exercise) {
        currentExercise = exercise;
        if (exercise == null) {
            exerciseTitle.setText("Select an exercise to view the details.");
            summaryText.setText("");
            instructionsList.removeAll();
            testsList.removeAll();
            workspaceProjectLabel.setText("—");
            starterFileLabel.setText("—");
            testLauncherLabel.setText("—");
        } else {
            exerciseTitle.setText(String.format("%s (%s)", exercise.getTitle(), exercise.getDifficulty()));
            summaryText.setText(exercise.getSummary());

            instructionsList.removeAll();
            int stepNumber = 1;
            for (String instruction : exercise.getInstructions()) {
                instructionsList.add(String.format("%d. %s", stepNumber++, instruction));
            }

            workspaceProjectLabel.setText(exercise.getProjectName());
            starterFileLabel.setText(exercise.getStarterFilePath());
            testLauncherLabel.setText(exercise.getTestLaunchShortcut());


            testsList.removeAll();
            for (TestDescriptor test : exercise.getTests()) {
                testsList.add(String.format("%s — %s", test.getName(), test.getDescription()));
            }
        }
        updateActionEnablement();
    }

    private void updateActionEnablement() {
        boolean enabled = currentExercise != null;
        runTestsButton.setEnabled(enabled);
        aiHelperButton.setEnabled(enabled);
        submitButton.setEnabled(enabled);
        openStarterButton.setEnabled(enabled);

    }

    @Override
    public void setFocus() {
        if (exerciseViewer != null) {
            exerciseViewer.getControl().setFocus();
        }
    }

    private static class ExerciseLabelProvider extends org.eclipse.jface.viewers.LabelProvider {
        @Override
        public String getText(Object element) {
            if (element instanceof ExerciseDescriptor) {
                ExerciseDescriptor descriptor = (ExerciseDescriptor) element;
                return String.format("%s — %s", descriptor.getTitle(), descriptor.getDifficulty());
            }
            return super.getText(element);
        }
    }

    @FunctionalInterface
    private interface ShellConsumer {
        void accept(Shell shell);
    }
}
