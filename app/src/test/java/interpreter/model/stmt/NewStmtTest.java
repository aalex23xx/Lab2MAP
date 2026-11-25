
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IntValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewStmtTest {

    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        // A4: Creates a program state with a Ref int variable
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
    }

    @Test
    void testUndeclaredVariable() {
        // A4: Checks that an undeclared variable throws
        assertThrows(StatementException.class,
                () -> new NewStmt("x",
                        new ValueExp(new IntValue(20))).execute(state));
    }

    @Test
    void testNotReference() throws MyException {
        // A4: Declares an integer instead of a reference
        new VarDeclStmt("x", new IntType()).execute(state);

        assertThrows(StatementException.class,
                () -> new NewStmt("x",
                        new ValueExp(new IntValue(20))).execute(state));
    }
}
