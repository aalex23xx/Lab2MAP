
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompStmtTest {

    @Test
    void testCompStmt() throws MyException {

        // A3: Creates the program state using the factory method
        PrgState state = PrgState.of(new NopStmt());

        state.getExeStack().pop();

        IStmt first = new NopStmt();
        IStmt second = new NopStmt();

        CompStmt stmt = new CompStmt(first, second);
        stmt.execute(state);

        assertSame(first, state.getExeStack().pop());
        assertSame(second, state.getExeStack().pop());
    }
}
