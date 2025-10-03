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
                        "Import the `ConditionalsWarmup` starter project from the `student-exercises` folder.",
                        "Open `src/com/school/student/Conditionals.java` and complete the TODO markers.",
                        "Run the `ConditionalsTest` JUnit configuration to verify edge cases.",
                        "Commit your work and submit through Google Classroom."),
                Arrays.asList(
                        new TestDescriptor("ConditionalsTest.java",
                                "Covers greater-than, equality, and negative number cases.")),
                "ConditionalsWarmup",
                "src/com/school/student/Conditionals.java",
                "ConditionalsTest");

        ExerciseDescriptor loops = new ExerciseDescriptor(
                "array-loops",
                "Looping Through Arrays",
                "Implement enhanced for-loops to aggregate values in an integer array.",
                "Intermediate",
                Arrays.asList(
                        "Import the `ArrayLoops` starter project from the `student-exercises` folder.",
                        "Implement sum, average, and max methods in `src/com/school/student/ArrayStats.java`.",
                        "Run the `ArrayStatsTestSuite` configuration to cover edge cases.",
                        "Ask the AI helper for hints if the average calculation looks off."),
                Arrays.asList(
                        new TestDescriptor("ArrayStatsTest.java",
                                "Validates positive, negative, and mixed number arrays."),
                        new TestDescriptor("ArrayStatsEdgeCasesTest.java",
                                "Verifies behavior when arrays are empty or null.")),
                "ArrayLoops",
                "src/com/school/student/ArrayStats.java",
                "ArrayStatsTestSuite");

        ExerciseDescriptor oop = new ExerciseDescriptor(
                "inheritance-quest",
                "Quest Characters with Inheritance",
                "Design a simple RPG inheritance hierarchy with base and specialized classes.",
                "Advanced",
                Arrays.asList(
                        "Import the `InheritanceQuest` starter project from the `student-exercises` folder.",
                        "Refactor the `Character` base class to include reusable behavior.",
                        "Implement subclasses in `src/com/school/student/characters` and expand their tests.",
                        "Submit the exported project through the Classroom link."),
                Arrays.asList(
                        new TestDescriptor("CharacterPolymorphismTest.java",
                                "Ensures polymorphic attacks and defenses behave as expected.")),
                "InheritanceQuest",
                "src/com/school/student/characters/Character.java",
                "InheritanceQuestAllTests");

        return Arrays.asList(basics, loops, oop);
    }
}
