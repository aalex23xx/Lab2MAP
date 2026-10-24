
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AssignStmtTest {

    private PrgState createState() {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );
    }

    @Test
    void testAssign() throws MyException {
        PrgState state = createState();
        state.getSymTable().put("x", new IntValue(0));

        AssignStmt stmt = new AssignStmt("x",
                new ValueExp(new IntValue(5)));

        stmt.execute(state);

        assertEquals(new IntValue(5),
                state.getSymTable().lookup("x"));
    }

    @Test
    void testUndeclaredVariable() {
        PrgState state = createState();

        AssignStmt stmt = new AssignStmt("x",
                new ValueExp(new IntValue(5)));

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testWrongType() {
        PrgState state = createState();
        state.getSymTable().put("x", new IntValue(0));

        AssignStmt stmt = new AssignStmt("x",
                new ValueExp(new BoolValue(true)));

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }
}
