
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IfStmtTest {

    @Test
    void testIfTrue() throws MyException {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        state.getExeStack().pop();

        IStmt thenStmt = new NopStmt();
        IStmt elseStmt = new NopStmt();

        IfStmt stmt = new IfStmt(
                new ValueExp(new BoolValue(true)),
                thenStmt,
                elseStmt
        );

        stmt.execute(state);

        assertSame(thenStmt, state.getExeStack().pop());
    }

    @Test
    void testIfFalse() throws MyException {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        state.getExeStack().pop();

        IStmt thenStmt = new NopStmt();
        IStmt elseStmt = new NopStmt();

        IfStmt stmt = new IfStmt(
                new ValueExp(new BoolValue(false)),
                thenStmt,
                elseStmt
        );

        stmt.execute(state);

        assertSame(elseStmt, state.getExeStack().pop());
    }

    @Test
    void testWrongCondition() {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        IfStmt stmt = new IfStmt(
                new ValueExp(new IntValue(5)),
                new NopStmt(),
                new NopStmt()
        );

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }
}
