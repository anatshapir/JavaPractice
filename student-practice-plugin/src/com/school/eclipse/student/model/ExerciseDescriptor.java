package com.school.eclipse.student.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Immutable description of a student exercise surfaced inside the plug-in.
 */
public final class ExerciseDescriptor {

    private final String id;
    private final String title;
    private final String summary;
    private final String difficulty;
    private final List<String> instructions;
    private final List<TestDescriptor> tests;
    private final String projectName;
    private final String starterFilePath;
    private final String testLaunchShortcut;

    public ExerciseDescriptor(String id, String title, String summary, String difficulty,
            List<String> instructions, List<TestDescriptor> tests, String projectName,
            String starterFilePath, String testLaunchShortcut) {
        this.id = Objects.requireNonNull(id, "id");
        this.title = Objects.requireNonNull(title, "title");
        this.summary = Objects.requireNonNull(summary, "summary");
        this.difficulty = Objects.requireNonNull(difficulty, "difficulty");
        this.instructions = List.copyOf(instructions);
        this.tests = List.copyOf(tests);
        this.projectName = Objects.requireNonNull(projectName, "projectName");
        this.starterFilePath = Objects.requireNonNull(starterFilePath, "starterFilePath");
        this.testLaunchShortcut = Objects.requireNonNull(testLaunchShortcut, "testLaunchShortcut");

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public List<String> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }

    public List<TestDescriptor> getTests() {
        return Collections.unmodifiableList(tests);
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStarterFilePath() {
        return starterFilePath;
    }

    public String getTestLaunchShortcut() {
        return testLaunchShortcut;
    }
}
