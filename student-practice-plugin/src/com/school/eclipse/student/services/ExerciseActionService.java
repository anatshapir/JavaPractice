package com.school.eclipse.student.services;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import com.school.eclipse.student.model.ExerciseDescriptor;

/**
 * Provides placeholder implementations for running tests, opening the AI helper, and submitting
 * work until the backing services are wired up.
 */
public class ExerciseActionService {

    public void runTests(Shell shell, ExerciseDescriptor exercise) {
        MessageDialog.openInformation(shell, "Run Tests",
                String.format("Tests for '%s' will be executed using the course JUnit launcher.\n"
                        + "(Prototype placeholder: integrate with the JDT JUnit launcher.)",
                        exercise.getTitle()));
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
                        + "(Prototype placeholder: implement the Classroom API workflow.)",
                        exercise.getTitle()));
    }
}
