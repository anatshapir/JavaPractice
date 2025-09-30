package com.school.eclipse.student.model;

import java.util.Objects;

/**
 * Metadata about a test resource packaged with an exercise.
 */
public final class TestDescriptor {

    private final String name;
    private final String description;

    public TestDescriptor(String name, String description) {
        this.name = Objects.requireNonNull(name, "name");
        this.description = Objects.requireNonNull(description, "description");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
