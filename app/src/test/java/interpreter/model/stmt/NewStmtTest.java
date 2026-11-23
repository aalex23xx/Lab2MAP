
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;

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
    void testNewAllocates() throws MyException {
        // A4: Allocates 20 in Heap and updates variable v
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);

        assertEquals(new RefValue(1, new IntType()),
                state.getSymTable().lookup("v"));

        assertEquals(new IntValue(20), state.getHeap().get(1));
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

    @Test
    void testWrongType() {
        // A4: Checks that a boolean cannot be stored in Ref int
        assertThrows(StatementException.class,
                () -> new NewStmt("v",
                        new ValueExp(new BoolValue(true))).execute(state));
    }
}
