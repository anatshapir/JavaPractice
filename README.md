# JavaPractice

This repository contains planning materials for an Eclipse plug-in that helps high school students practice Java programming.

## Documentation

* [Eclipse Plugin Plan](docs/eclipse_plugin_plan.md)

## Eclipse Plug-in Prototype
The `student-practice-plugin` directory contains a PDE plug-in prototype that opens an interactive "Exercise Overview" view inside the Eclipse IDE. Import it into Eclipse via *File → Import → Existing Projects into Workspace* to explore the sample exercises, review placeholder AI/test/submission actions, and evaluate the evolving student workflow.

### Running the Prototype in Eclipse
1. **Install the right Eclipse distribution** – download the *Eclipse IDE for RCP and RAP Developers* package so that PDE tooling is available.
2. **Import the project** – start Eclipse, choose *File → Import → Existing Projects into Workspace*, and select the `student-practice-plugin` folder from this repository.
3. **Launch a runtime Eclipse** – with the plug-in project selected, choose *Run → Run Configurations… → Eclipse Application*, accept the defaults, and click *Run*. PDE will start a second “runtime” instance of Eclipse with the plug-in installed.
4. **Open the Exercise Overview view** – in the runtime workspace, navigate to *Window → Show View → Other… → Student Practice → Exercise Overview*. The sample assignments, instructions, and placeholder buttons should now appear.
5. **(Optional) Wire in real tests** – the current prototype does not bundle runnable JUnit classes. Once you add them, create a JUnit launch configuration (or use the toolbar button that the view will eventually drive) to execute the tests from within Eclipse.

These steps verify that the plug-in loads correctly and let you preview the workflow students will follow as additional functionality (real tests, AI guidance, Google Classroom integration) is implemented.
