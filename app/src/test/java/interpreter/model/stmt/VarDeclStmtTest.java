
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.type.*;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VarDeclStmtTest {

    @Test
    void testDeclaration() throws MyException {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        new VarDeclStmt("x", new IntType()).execute(state);
        new VarDeclStmt("a", new BoolType()).execute(state);

        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));
        assertEquals(new BoolValue(false), state.getSymTable().lookup("a"));
    }

    @Test
    void testAlreadyDeclared() throws MyException {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        VarDeclStmt stmt = new VarDeclStmt("x", new IntType());
        stmt.execute(state);

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }
}
