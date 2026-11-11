
package interpreter;

import interpreter.controller.Controller;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.repository.Repository;
import interpreter.view.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // A3: Gets the example programs
        Examples examples = new Examples();

        List<IStmt> programs = List.of(
                examples.ex1,
                examples.ex2,
                examples.ex3,
                examples.ex4,
                examples.ex5
        );

        // A3: Creates the command-based menu
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        // A3: Creates a separate controller for each example
        for (int i = 0; i < programs.size(); i++) {

            IStmt program = programs.get(i);

            Repository repo = new Repository(
                    PrgState.of(program),
                    "log" + (i + 1) + ".txt"
            );

            Controller controller = new Controller(repo, true);

            menu.addCommand(new RunExampleCommand(
                    String.valueOf(i + 1),
                    program.toString(),
                    controller
            ));
        }

        // A3: Starts the menu
        menu.show();
    }
}
