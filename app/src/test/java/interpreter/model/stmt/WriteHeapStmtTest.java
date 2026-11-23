
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

class WriteHeapStmtTest {

    private PrgState state;

    @BeforeEach
    void setUp() throws MyException {
        // A4: Creates a program state with a Ref int variable
        state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);
    }

    @Test
    void testWriteHeap() throws MyException {
        // A4: Allocates 20 at address 1
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);

        // A4: Updates the Heap value from 20 to 30
        new WriteHeapStmt("v", new ValueExp(new IntValue(30))).execute(state);

        assertEquals(new IntValue(30), state.getHeap().get(1));
        assertEquals(new RefValue(1, new IntType()),
                state.getSymTable().lookup("v"));
    }

    @Test
    void testUndeclaredVariable() {
        // A4: Checks that the variable must be declared
        assertThrows(StatementException.class,
                () -> new WriteHeapStmt("x",
                        new ValueExp(new IntValue(30))).execute(state));
    }

    @Test
    void testNotReference() throws MyException {
        // A4: Declares an integer variable instead of a reference
        new VarDeclStmt("x", new IntType()).execute(state);

        assertThrows(StatementException.class,
                () -> new WriteHeapStmt("x",
                        new ValueExp(new IntValue(30))).execute(state));
    }

    @Test
    void testInvalidAddress() {
        // A4: The default Ref int value has address 0
        assertThrows(StatementException.class,
                () -> new WriteHeapStmt("v",
                        new ValueExp(new IntValue(30))).execute(state));
    }

    @Test
    void testWrongType() throws MyException {
        // A4: Allocates an integer in Heap
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);

        // A4: Checks that a boolean cannot replace an integer
        assertThrows(StatementException.class,
                () -> new WriteHeapStmt("v",
                        new ValueExp(new BoolValue(true))).execute(state));
    }
}
