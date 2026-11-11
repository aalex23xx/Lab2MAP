
package interpreter.controller;

import interpreter.repository.Repository;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.stmt.*;
import interpreter.model.type.*;
import interpreter.model.value.*;
import interpreter.view.Examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    // A3: Creates a temporary folder for each test
    @TempDir
    Path dir;

    private PrgState createState(IStmt program) {

        // A3: Creates the program state using the factory method
        return PrgState.of(program);
    }

    // A3: Runs a program with a temporary log file
    private PrgState run(IStmt program) throws MyException {
        PrgState state = PrgState.of(program);
        var repo = new Repository(state, dir.resolve("log.txt").toString());
        new Controller(repo, false).allStep();
        return state;
    }

    @Test
    void testOneStep() throws MyException {
        PrgState state = createState(
                new VarDeclStmt("x", new IntType())
        );

        Controller controller = new Controller(
                new Repository(state, dir.resolve("log-test.txt").toString()), false // A3
        );

        controller.oneStep(state);

        assertEquals(new IntValue(0),
                state.getSymTable().lookup("x"));
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void testOneStepEmpty() throws MyException {
        PrgState state = createState(new NopStmt());
        state.getExeStack().pop();

        Controller controller = new Controller(
                new Repository(state, dir.resolve("log-test.txt").toString()), false // A3
        );

        assertThrows(StatementException.class,
                () -> controller.oneStep(state));
    }

    @Test
    void testExample1() throws MyException {
        Examples examples = new Examples();
        PrgState state = createState(examples.ex1);

        Controller controller = new Controller(
                new Repository(state, dir.resolve("log-test.txt").toString()), false // A3
        );

        controller.allStep();

        assertEquals("[2]", state.getOut().toString());
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void testExample2() throws MyException {
        Examples examples = new Examples();
        PrgState state = createState(examples.ex2);

        Controller controller = new Controller(
                new Repository(state, dir.resolve("log-test.txt").toString()), false // A3
        );

        controller.allStep();

        assertEquals("[18]", state.getOut().toString());
        assertTrue(state.getExeStack().isEmpty());
    }

    @Test
    void testExample3() throws MyException {
        Examples examples = new Examples();
        PrgState state = createState(examples.ex3);

        Controller controller = new Controller(
                new Repository(state, dir.resolve("log-test.txt").toString()), false // A3
        );

        controller.allStep();

        assertEquals("[2]", state.getOut().toString());
        assertTrue(state.getExeStack().isEmpty());
    }

    // A3: Tests Example 5 with relational expressions
    @Test
    void testExample5() throws MyException {

        Examples examples = new Examples();
        PrgState state = createState(examples.ex5);

        Controller controller = new Controller(
                new Repository(state, dir.resolve("log-test.txt").toString()), false
        );

        controller.allStep();

        assertEquals("[5]", state.getOut().toString());
        assertTrue(state.getExeStack().isEmpty());
    }

    // A3: Tests Example 4 using a temporary file
    @Test
    void fileExampleReadsBothNumbersAndClosesTheFile() throws Exception {
        Path in = dir.resolve("test.in");
        Files.writeString(in, "15\n50\n");
        PrgState s = run(Examples.fileExample(in.toString()));

        assertEquals(new IntValue(15), s.getOut().toList().get(0));
        assertEquals(new IntValue(50), s.getOut().toList().get(1));
        assertTrue(s.getFileTable().toMap().isEmpty());
    }

    // A3: Tests the order of sections in the log file
    @Test
    void logFileContainsTheFourSectionsInOrder() throws Exception {
        run(new Examples().ex1);
        String log = Files.readString(dir.resolve("log.txt"));

        int a = log.indexOf("ExeStack:"), b = log.indexOf("SymTable:");
        int c = log.indexOf("Out:"), d = log.indexOf("FileTable:");

        assertTrue(0 <= a && a < b && b < c && c < d);
    }
}
