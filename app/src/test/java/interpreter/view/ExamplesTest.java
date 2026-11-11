
package interpreter.view;

import interpreter.controller.Controller;
import interpreter.model.PrgState;
import interpreter.model.value.IntValue;
import interpreter.repository.Repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A3: Tests the complete file example
public class ExamplesTest {

    @TempDir
    Path dir;

    @Test
    void testFileExample() throws Exception {

        // A3: Creates a temporary input file
        Path file = dir.resolve("test.in");
        Files.writeString(file, "15\n50");

        // A3: Creates the program using the temporary file
        PrgState state = PrgState.of(
                Examples.fileExample(file.toString())
        );

        // A3: Creates a temporary log file
        Repository repository = new Repository(
                state, dir.resolve("log4.txt").toString()
        );

        Controller controller = new Controller(repository, false);

        // A3: Executes the complete program
        controller.allStep();

        // A3: Checks the two numbers read from the file
        assertEquals(
                List.of(new IntValue(15), new IntValue(50)),
                state.getOut().toList()
        );

        // A3: Checks that the program finished
        assertTrue(state.getExeStack().isEmpty());

        // A3: Checks that the file was closed
        assertTrue(state.getFileTable().toMap().isEmpty());
    }

    // A3: Tests the file example with an empty file
    @Test
    void testEmptyFileExample() throws Exception {

        // A3: Creates an empty temporary file
        Path file = dir.resolve("empty.in");
        Files.writeString(file, "");

        PrgState state = PrgState.of(
                Examples.fileExample(file.toString())
        );

        Repository repository = new Repository(
                state, dir.resolve("log-empty.txt").toString()
        );

        Controller controller = new Controller(repository, false);

        controller.allStep();

        // A3: Both reads return 0 at the end of the file
        assertEquals(
                List.of(new IntValue(0), new IntValue(0)),
                state.getOut().toList()
        );

        assertTrue(state.getExeStack().isEmpty());
        assertTrue(state.getFileTable().toMap().isEmpty());
    }

}
