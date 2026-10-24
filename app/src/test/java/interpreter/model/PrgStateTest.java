
package interpreter.model;

import interpreter.exceptions.ADTException;
import interpreter.model.adt.*;
import interpreter.model.stmt.*;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PrgStateTest {

    private PrgState createState() {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
    }

    @Test
    void testConstructor() throws ADTException {
        IStmt program = new NopStmt();

        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                program
        );

        assertSame(program, state.getOriginalProgram());
        assertSame(program, state.getExeStack().pop());
    }

    @Test
    void testInitialStructures() {
        PrgState state = createState();

        assertFalse(state.getExeStack().isEmpty());
        assertEquals("{}", state.getSymTable().toString());
        assertEquals("[]", state.getOut().toString());
    }

    @Test
    void testGetters() {
        PrgState state = createState();

        assertNotNull(state.getExeStack());
        assertNotNull(state.getSymTable());
        assertNotNull(state.getOut());
        assertNotNull(state.getOriginalProgram());
    }

    @Test
    void testSetters() {
        PrgState state = createState();

        IStack<IStmt> stack = new MyStack<>();
        IDictionary<String, IValue> table = new MyDictionary<>();
        IList<IValue> out = new MyList<>();

        state.setExeStack(stack);
        state.setSymTable(table);
        state.setOut(out);

        assertSame(stack, state.getExeStack());
        assertSame(table, state.getSymTable());
        assertSame(out, state.getOut());
    }

    @Test
    void testToString() {
        PrgState state = createState();

        String result = state.toString();

        assertTrue(result.contains("ExeStack:"));
        assertTrue(result.contains("SymTable:"));
        assertTrue(result.contains("Out:"));
    }
}
