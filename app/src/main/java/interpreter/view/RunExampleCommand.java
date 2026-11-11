
package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;

// A3: Command for running an example
public class RunExampleCommand extends Command {

    private final Controller controller;
    private boolean executed = false;

    // command's key/ description/ controller
    public RunExampleCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {

        // Each example can run only once
        if (executed) {
            System.out.println("This example has already been executed.");
            return;
        }

        executed = true;

        try {
            controller.allStep();
        } catch (MyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
