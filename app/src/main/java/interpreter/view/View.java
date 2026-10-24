
package interpreter.view;

import interpreter.controller.Controller;
import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.stmt.IStmt;
import interpreter.repository.Repository;

import java.util.Scanner;

public class View {

    public void run() {

        Scanner scanner = new Scanner(System.in);
        Examples examples = new Examples();
        int selected = 0;

        while (true) {

            System.out.println();
            System.out.println("1. Select program");
            System.out.println("2. Run selected program");
            System.out.println("0. Exit");
            System.out.print("Option: ");

            if (!scanner.hasNextLine()) {
                return;
            }

            String option = scanner.nextLine();

            if (option.equals("1")) {

                System.out.println("1. Example 1");
                System.out.println("2. Example 2");
                System.out.println("3. Example 3");
                System.out.print("Select: ");

                if (!scanner.hasNextLine()) {
                    return;
                }

                String choice = scanner.nextLine();

                if (choice.equals("1")) {
                    selected = 1;
                } else if (choice.equals("2")) {
                    selected = 2;
                } else if (choice.equals("3")) {
                    selected = 3;
                } else {
                    System.out.println("Invalid program");
                    continue;
                }

                System.out.println("Program " + selected + " selected");

            } else if (option.equals("2")) {

                IStmt program;

                if (selected == 1) {
                    program = examples.ex1;
                } else if (selected == 2) {
                    program = examples.ex2;
                } else if (selected == 3) {
                    program = examples.ex3;
                } else {
                    System.out.println("Select a program first");
                    continue;
                }

                PrgState state = new PrgState(
                        new MyStack<>(),
                        new MyDictionary<>(),
                        new MyList<>(),
                        program
                );

                Controller controller = new Controller(
                        new Repository(state), true
                );

                try {
                    controller.allStep();
                } catch (MyException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else if (option.equals("0")) {
                System.out.println("Goodbye!");
                return;

            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
