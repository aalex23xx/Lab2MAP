
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.IntValue;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrintStmtTest {

    @Test
    void testPrint() throws MyException {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        PrintStmt stmt = new PrintStmt(
                new ValueExp(new IntValue(5))
        );

        stmt.execute(state);

        assertEquals(new IntValue(5), state.getOut().get(0));
    }
}
