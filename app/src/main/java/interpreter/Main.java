
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
                examples.ex5,
                examples.ex6,  // A4: Adds Ex6
                examples.ex7,  // A4: Adds Ex7
                examples.ex8,  // A4: Adds Ex8
                examples.ex9,  // A4: Tests the safe Garbage Collector
                Examples.EX10 // A4: Adds Ex10 - WhileStmt
        );

        // Creates the command-based menu
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));

        // Creates a separate controller for each example
        for (int i = 0; i < programs.size(); i++) {

            IStmt program = programs.get(i);

            int exampleNumber = i + 1;

            Repository repo = new Repository(
                    PrgState.of(program),
                    "log" + exampleNumber + ".txt"
            );

            Controller controller = new Controller(repo, true);

            menu.addCommand(new RunExampleCommand(
                    String.valueOf(exampleNumber),
                    program.toString(),
                    controller
            ));
        }

        menu.show();
    }
}
