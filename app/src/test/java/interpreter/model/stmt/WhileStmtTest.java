
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.*;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhileStmtTest {

    // A4: Runs a loop that increments v until it reaches limit
    private PrgState runLoop(int limit) throws MyException {
        PrgState state = PrgState.of(new NopStmt());

        new VarDeclStmt("v", new IntType()).execute(state);

        // A4: Condition v < limit
        IExp condition = new RelationalExp(RelOp.LT,
                new VarExp("v"),
                new ValueExp(new IntValue(limit)));

        // A4: Body v = v + 1
        IStmt body = new AssignStmt("v",
                new ArithExp(ArithOp.ADD,
                        new VarExp("v"),
                        new ValueExp(new IntValue(1))));

        WhileStmt loop = new WhileStmt(condition, body);
        state.getExeStack().push(loop);

        // A4: Executes statements from ExeStack
        int steps = 0;
        while (!state.getExeStack().isEmpty() && steps < 50) {
            IStmt stmt = state.getExeStack().pop();
            stmt.execute(state);
            steps++;
        }

        // A4: Checks that the loop has finished
        assertTrue(state.getExeStack().isEmpty());

        return state;
    }

    @Test
    void testZeroIterations() throws MyException {
        // A4: Condition is false from the beginning
        PrgState state = runLoop(0);

        assertEquals(new IntValue(0),
                state.getSymTable().lookup("v"));
    }

    @Test
    void testOneIteration() throws MyException {
        // A4: Body executes once
        PrgState state = runLoop(1);

        assertEquals(new IntValue(1),
                state.getSymTable().lookup("v"));
    }

    @Test
    void testMultipleIterations() throws MyException {
        // A4: Body executes four times
        PrgState state = runLoop(4);

        assertEquals(new IntValue(4),
                state.getSymTable().lookup("v"));
    }

    @Test
    void testNonBooleanCondition() {
        // A4: An integer condition must throw an exception
        PrgState state = PrgState.of(new NopStmt());

        WhileStmt loop = new WhileStmt(
                new ValueExp(new IntValue(5)),
                new NopStmt());

        assertThrows(StatementException.class,
                () -> loop.execute(state));
    }
}
