package com.school.eclipse.student.services;

import java.util.Arrays;
import java.util.List;

import com.school.eclipse.student.model.ExerciseDescriptor;
import com.school.eclipse.student.model.TestDescriptor;

/**
 * Temporary in-memory store for prototype data until backed by classroom integration.
 */
public class ExerciseRepository {

    /**
     * Returns a curated list of sample exercises to drive the prototype UI.
     */
    public List<ExerciseDescriptor> loadExercises() {
        ExerciseDescriptor basics = new ExerciseDescriptor(
                "intro-conditionals",
                "Conditionals Warm-up",
                "Practice writing if/else branches to compare integers.",
                "Beginner",
                Arrays.asList(
                        "Read through the starter code and identify the TODO markers.",
                        "Replace the TODOs with conditionals that return the correct strings.",
                        "Run the provided unit tests to verify edge cases.",
                        "Commit your work and submit through Google Classroom."),
                Arrays.asList(
                        new TestDescriptor("ConditionalsTest.java",
                                "Covers greater-than, equality, and negative number cases.")));

        ExerciseDescriptor loops = new ExerciseDescriptor(
                "array-loops",
                "Looping Through Arrays",
                "Implement enhanced for-loops to aggregate values in an integer array.",
                "Intermediate",
                Arrays.asList(
                        "Open the Exercise Guide to review the examples of for/while loops.",
                        "Implement sum, average, and max methods in ArrayStats.java.",
                        "Ensure the JUnit tests compile by importing the classroom test project.",
                        "Ask the AI helper for hints if the average calculation looks off."),
                Arrays.asList(
                        new TestDescriptor("ArrayStatsTest.java",
                                "Validates positive, negative, and mixed number arrays."),
                        new TestDescriptor("ArrayStatsEdgeCasesTest.java",
                                "Verifies behavior when arrays are empty or null.")));

        ExerciseDescriptor oop = new ExerciseDescriptor(
                "inheritance-quest",
                "Quest Characters with Inheritance",
                "Design a simple RPG inheritance hierarchy with base and specialized classes.",
                "Advanced",
                Arrays.asList(
                        "Refactor the Character base class to include reusable behavior.",
                        "Implement at least two subclasses with unique abilities and tests.",
                        "Document each subclass in the provided README for grading clarity.",
                        "Submit the exported project through the Classroom link."),
                Arrays.asList(
                        new TestDescriptor("CharacterPolymorphismTest.java",
                                "Ensures polymorphic attacks and defenses behave as expected.")));

        return Arrays.asList(basics, loops, oop);
    }
}
