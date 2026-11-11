
package interpreter.view;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;

// A3: Menu built from commands
public class TextMenu {

    // A3: Stores commands in insertion order
    private final Map<String, Command> commands = new LinkedHashMap<>();

    // A3: Adds a command to the menu
    public void addCommand(Command c) {
        commands.put(c.getKey(), c);
    }

    // A3: Displays all menu options
    private void printMenu() {
        for (Command c : commands.values()) {
            System.out.printf("%4s : %s%n", c.getKey(), c.getDescription());
        }
    }

    // A3: Reads and executes menu commands
    public void show() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Input the option: ");

            Command c = commands.get(scanner.nextLine().strip());

            if (c == null) {
                System.out.println("Invalid option");
                continue;
            }

            c.execute();
        }
    }
}
