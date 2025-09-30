# JavaPractice

This repository contains planning materials for an Eclipse plug-in that helps high school students practice Java programming.

## Documentation

* [Eclipse Plugin Plan](docs/eclipse_plugin_plan.md)
* [Teacher Workflow Guide](docs/teacher_workflow.md)

## Starter Exercises

The [`student-exercises/`](student-exercises/) folder ships three import-ready Java projects—**ConditionalsWarmup**, **ArrayLoops**, and **InheritanceQuest**—that pair with the cards surfaced in the plug-in. Each project contains starter source files, JUnit 5 tests, and a README with student/teacher notes. Import these projects alongside the plug-in to let students open the starter files directly from the **Exercise Overview** view.
=======


## Eclipse Plug-in Prototype
The `student-practice-plugin` directory contains a PDE plug-in prototype that opens an interactive "Exercise Overview" view inside the Eclipse IDE. Import it into Eclipse via *File → Import → Existing Projects into Workspace* to explore the sample exercises, review placeholder AI/test/submission actions, and evaluate the evolving student workflow.

### Running the Prototype in Eclipse
1. **Install the right Eclipse distribution** – download the *Eclipse IDE for RCP and RAP Developers* package so that PDE tooling is available.
2. **Import the project** – start Eclipse, choose *File → Import → Existing Projects into Workspace*, and select the `student-practice-plugin` folder from this repository.
3. **Launch a runtime Eclipse** – with the plug-in project selected, choose *Run → Run Configurations… → Eclipse Application*, accept the defaults, and click *Run*. PDE will start a second “runtime” instance of Eclipse with the plug-in installed.
4. **Open the Exercise Overview view** – in the runtime workspace, navigate to *Window → Show View → Other… → Student Practice → Exercise Overview*. The sample assignments, instructions, and placeholder buttons should now appear.
5. **Import the starter projects** – choose *File → Import → Existing Projects into Workspace* again, select the folders inside [`student-exercises/`](student-exercises/), and finish the wizard. The view's **Open Starter File** button uses these project names to launch editors.
6. **Create JUnit launchers** – after importing, right-click each provided test suite and choose *Run As → JUnit Test*. Save the generated Run Configuration with the names shown in the view (for example, `ArrayStatsTestSuite`).


These steps verify that the plug-in loads correctly and let you preview the workflow students will follow as additional functionality (real tests, AI guidance, Google Classroom integration) is implemented.
