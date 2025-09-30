# Eclipse Plugin for Guided Java Exercises

## Overview
This document outlines the architecture and feature plan for an Eclipse plug-in that supports high school Java students. The plug-in will provide:

* Guided exercises bundled with starter code and JUnit tests so students can verify solutions locally.
* An embedded AI chat assistant that offers hints and conceptual guidance without revealing full solutions.
* Automated submission and grading workflows integrated with Google Classroom to streamline assignment management.

The goal is to deliver a scaffolded learning experience that reinforces good development practices while keeping students focused inside the IDE.

## High-Level Architecture

The plug-in will be composed of the following major modules:

| Module | Purpose |
| --- | --- |
| Exercise Manager | Imports exercise packages, displays instructions, and configures projects. |
| Test Runner UI | Surfaces JUnit test results in an approachable panel with teacher-configured messaging. |
| AI Guide View | Embeds a conversational interface backed by an LLM API with guardrails to prevent direct answers. |
| Submission Client | Packages student work, uploads artifacts, and reports grades via Google Classroom APIs. |
| Teacher Portal Integration | Synchronizes class rosters, assignments, and grading rubrics. |

These modules will communicate through Eclipse extension points and shared services registered in the plug-in activator.

## Key Features

### Exercise Packaging
* Exercises delivered as zip archives containing starter code, hidden reference solutions, and student-visible tests.
* A manifest file (JSON or YAML) describing metadata (title, objectives, difficulty), required dependencies, and submission settings.
* Import wizard that unpacks the archive into the student workspace, configures Java build path, and registers tests with JUnit.

### AI Chat Assistant
* Custom Eclipse view that hosts a web-based chat UI rendered with SWT Browser or JavaFX.
* Backend service mediates between the IDE and an AI provider (e.g., OpenAI, Anthropic) using a moderation layer:
  * Detects requests for direct answers and redirects students to underlying concepts or hints.
  * Logs interactions for teacher review.
* Context injection: pass exercise metadata, test summaries, and recent code snippets (with opt-in) to improve guidance.

### Testing Experience
* Integrate with JUnit launch configurations to run tests with one click.
* Display test status in a simplified dashboard that clarifies failures and offers targeted hints (e.g., highlight method under test).
* Optional "checkpoint" tests that remain hidden until prerequisites are met.

### Submission and Grading
* Submission wizard validates that required tests pass (or warns otherwise).
* Automatically packages student source files and selected resources into a zip.
* Uses Google Classroom API to create or update student submissions and attach artifacts.
* Receives rubric scores from automated checks (e.g., unit tests, static analysis) and reports them back to Classroom.

## Google Classroom Integration

1. **Authentication**: OAuth 2.0 flow handled via an embedded browser. Store refresh tokens securely using the Eclipse secure storage API.
2. **Roster Sync**: Teacher config panel lists available classes and assignments; students see only their active assignments.
3. **Grade Reporting**: After automated grading, post draft grades and comments. Teachers can review and publish from Classroom.

## Security and Privacy Considerations

* Comply with school data policies by minimizing stored personal information and encrypting tokens.
* Provide administrative controls to disable AI chat logging or anonymize student names.
* Implement rate limiting and content filtering for the AI assistant to prevent misuse.

## Implementation Roadmap

1. **MVP (Milestone 1)**
   * Exercise importer wizard with manifest parsing.
   * Basic JUnit run configuration and results view.
   * Manual submission packaging without Classroom integration.

2. **Milestone 2**
   * Integrate Google Classroom authentication and assignment upload.
   * Add AI chat view with placeholder backend (scripted hints).
   * Implement teacher settings UI for configuring hint levels and submission requirements.

3. **Milestone 3**
   * Connect AI chat to production LLM service with moderation rules.
   * Add automated grading pipelines (e.g., static analysis, code style checks) feeding into rubric scores.
   * Provide analytics dashboard for teachers (progress tracking, common misconceptions).

## Technology Stack

* **Language**: Java 11+ for plug-in development.
* **Frameworks**: Eclipse RCP, SWT/JFace, OSGi services.
* **Testing**: JUnit 5, Eclipse PDE JUnit for plug-in tests.
* **Backend Services**: Optional companion microservice (Node.js or Java Spring Boot) to handle AI requests and Google Classroom interactions if direct calls are not feasible from the IDE.

## Next Steps

* Create PDE project skeleton with plug-in manifest (`plugin.xml`) and activator class.
* Prototype the exercise import wizard and JUnit integration.
* Define manifest schema and example exercise package for pilot testing.
* Engage school IT for OAuth credentials and ensure compliance with district policies.



## Prototype Plug-in Skeleton

The `student-practice-plugin` project now ships an interactive Exercise Overview view that demonstrates the planned workflow:

* Sample exercises with metadata and step-by-step instructions loaded from an in-memory repository that points to starter projects.
* Quick action buttons that open starter files, reference Run Configuration names for the bundled JUnit 5 suites, and stub out AI-guided help plus Google Classroom submissions.
* A refreshed SWT/JFace layout that previews how students will browse assignments and understand the provided test scaffolding, complete with workspace details (project name, starter file, launcher name).
* Import-ready Java projects in `student-exercises/` that illustrate how teachers can package assignments, including TODO-marked starter code and runnable tests.

Use this project as the foundation for building out the Exercise Manager and wiring in automated services for testing, AI guidance, and Classroom submissions.
