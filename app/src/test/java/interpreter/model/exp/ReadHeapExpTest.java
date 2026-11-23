
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.PrgState;
import interpreter.model.stmt.NewStmt;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.type.IntType;
import interpreter.model.type.RefType;
import interpreter.model.value.IntValue;
import interpreter.model.value.RefValue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadHeapExpTest {

    @Test
    void testReadHeap() throws MyException {

        // A4: Creates a reference variable
        PrgState state = PrgState.of(new NopStmt());
        new VarDeclStmt("v", new RefType(new IntType())).execute(state);

        // A4: Allocates the value 20 in Heap
        new NewStmt("v", new ValueExp(new IntValue(20))).execute(state);

        // A4: Reads the value stored at the reference address
        ReadHeapExp exp = new ReadHeapExp(new VarExp("v"));

        assertEquals(new IntValue(20),
                exp.eval(state.getSymTable(), state.getHeap()));
    }

    @Test
    void testNotReference() {

        // A4: Checks that an integer is not a reference
        PrgState state = PrgState.of(new NopStmt());

        ReadHeapExp exp = new ReadHeapExp(
                new ValueExp(new IntValue(10)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(state.getSymTable(), state.getHeap()));
    }

    @Test
    void testInvalidAddress() {

        // A4: Checks that the address does not exist in Heap
        PrgState state = PrgState.of(new NopStmt());

        ReadHeapExp exp = new ReadHeapExp(
                new ValueExp(new RefValue(5, new IntType())));

        assertThrows(ExpressionException.class,
                () -> exp.eval(state.getSymTable(), state.getHeap()));
    }
}
