
package interpreter.model.stmt;

import interpreter.exceptions.ADTException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NopStmtTest {

    @Test
    void testNop() throws ADTException {

        // A3: Creates the program state using the factory method
        PrgState state = PrgState.of(new NopStmt());

        state.getExeStack().pop();

        NopStmt stmt = new NopStmt();
        PrgState result = stmt.execute(state);

        assertSame(state, result);
        assertTrue(state.getExeStack().isEmpty());
        assertEquals("{}", state.getSymTable().toString());
        assertEquals("[]", state.getOut().toString());
    }
}
