
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

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private PrgState createState(IStmt program) {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                program
        );
    }

    @Test
    void testOneStep() throws MyException {
        PrgState state = createState(
                new VarDeclStmt("x", new IntType())
        );

        Controller controller = new Controller(
                new Repository(state), false
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
                new Repository(state), false
        );

        assertThrows(StatementException.class,
                () -> controller.oneStep(state));
    }

    @Test
    void testExample1() throws MyException {
        Examples examples = new Examples();
        PrgState state = createState(examples.ex1);

        Controller controller = new Controller(
                new Repository(state), false
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
                new Repository(state), false
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
                new Repository(state), false
        );

        controller.allStep();

        assertEquals("[2]", state.getOut().toString());
        assertTrue(state.getExeStack().isEmpty());
    }
}
