package com.school.eclipse.student.services;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.school.eclipse.student.model.ExerciseDescriptor;

/**
 * Provides placeholder implementations for running tests, opening the AI helper, and submitting
 * work until the backing services are wired up.
 */
public class ExerciseActionService {

    public void runTests(Shell shell, ExerciseDescriptor exercise) {
        MessageDialog.openInformation(shell, "Run Tests",
                String.format("Launch the '%s' configuration from the Run ▶ Run Configurations menu to "
                        + "execute the provided suite for '%s'.\n\n" +
                        "(Prototype placeholder: wire directly into the JDT JUnit launcher.)",
                        exercise.getTestLaunchShortcut(), exercise.getTitle()));
    }

    public void openAiHelper(Shell shell, ExerciseDescriptor exercise) {
        MessageDialog.openInformation(shell, "AI Helper",
                String.format("An instructional chat session will open for '%s'.\n"
                        + "(Prototype placeholder: wire up the classroom-safe AI assistant.)",
                        exercise.getTitle()));
    }

    public void submitToClassroom(Shell shell, ExerciseDescriptor exercise) {
        MessageDialog.openInformation(shell, "Submit to Classroom",
                String.format("'%s' will be packaged and uploaded to Google Classroom.\n"
                        + "Use the Google Classroom guide to connect the assignment template to your class.\n\n"
                        + "(Prototype placeholder: implement the Classroom API workflow.)",
                        exercise.getTitle()));
    }

    public void openStarterFile(Shell shell, ExerciseDescriptor exercise) {
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        String workspaceRelative = String.format("/%s/%s", exercise.getProjectName(),
                exercise.getStarterFilePath());
        IPath path = new Path(workspaceRelative);
        IFile file = root.getFile(path);
        if (file == null || !file.exists()) {
            MessageDialog.openWarning(shell, "Open Starter File",
                    String.format("The starter file %s was not found in the workspace.\n"
                            + "Import the '%s' project from the repository's student-exercises folder first.",
                            exercise.getStarterFilePath(), exercise.getProjectName()));
            return;
        }

        if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) {
            MessageDialog.openError(shell, "Open Starter File",
                    "No active workbench window is available to open the starter file.");
            return;
        }

        IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
        if (page == null) {
            MessageDialog.openError(shell, "Open Starter File",
                    "No active workbench page is available to open the starter file.");
            return;
        }

        try {
            IDE.openEditor(page, file);
        } catch (PartInitException e) {
            MessageDialog.openError(shell, "Open Starter File",
                    String.format("Unable to open %s: %s", exercise.getStarterFilePath(), e.getMessage()));
        }
    }
}
