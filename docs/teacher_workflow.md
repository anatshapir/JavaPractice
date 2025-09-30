# Teacher Workflow Guide

This guide explains how to author new programming exercises, connect them to the Student Practice plug-in, and share them with Google Classroom.

## 1. Prepare a Starter Project
1. Copy one of the templates in [`student-exercises/`](../student-exercises/).
2. Rename the project folder and update the `.project` `<name>` value to match.
3. Place starter source files in `src/` and solution tests in `test/`.
4. Keep the `.classpath` entries for the JRE and JUnit 5 container so the plug-in can launch tests without extra setup.

## 2. Register the Exercise in the Plug-in
1. Open `student-practice-plugin/src/com/school/eclipse/student/services/ExerciseRepository.java`.
2. Duplicate an existing `ExerciseDescriptor` block and update:
   - `id`: a unique identifier (e.g., `strings-palindrome`).
   - `title`, `summary`, and `difficulty` strings.
   - Instruction list items so they reference the new project folder.
   - `projectName` (must match the `.project` name), `starterFilePath`, and `testLaunchShortcut` values.
   - `TestDescriptor` entries for every JUnit class in the starter project.
3. Save and launch the plug-in from Eclipse PDE to verify the new card appears and the **Open Starter File** button targets the correct file.

## 3. Create a JUnit Launch Configuration
1. Import the starter project into Eclipse.
2. Right-click the desired test class or suite and choose **Run As ▸ JUnit Test**.
3. In the **Run Configurations** dialog, rename the generated entry to match the `testLaunchShortcut` value you set in the repository (for example, `ArrayStatsTestSuite`).
4. Share this `.launch` file with students (Team Project, Classroom attachment, or repository) so the **Run Tests** button message points to an existing configuration.

## 4. Publish to Google Classroom
1. Zip the starter project folder (students submit the same structure after completing the exercise).
2. In Google Classroom, create an **Assignment** and attach the zipped starter project as **Make a copy for each student**.
3. Record the Classroom assignment link or ID in your internal tracker. The plug-in's **Submit** button currently opens a placeholder dialog, but the planned API integration will use this reference to upload submissions automatically.
4. Link back to this repository for students who want to pull the starter projects manually.

## 5. Iterate and Extend
- Add README notes inside each starter project to highlight grading rubrics or stretch goals.
- Update [`docs/eclipse_plugin_plan.md`](./eclipse_plugin_plan.md) when introducing new features so the roadmap stays aligned.
- When the Classroom integration ships, revisit the submission workflow to bind assignments directly through OAuth credentials.

By following these steps, teachers can steadily grow a library of guided programming exercises while keeping the plug-in content in sync with classroom expectations.
