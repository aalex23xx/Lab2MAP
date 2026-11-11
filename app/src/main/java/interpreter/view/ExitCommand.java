
package interpreter.view;

// A3: Command for exiting the application
public class ExitCommand extends Command {

    public ExitCommand(String key, String description) {
        super(key, description);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
