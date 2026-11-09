
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.BoolValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

// S5: conditional statement (if-then-else)
// Evaluates a boolean condition and selects one of two statements
// Pushes the selected statement onto ExeStack for execution
public class IfStmt implements IStmt {

    // Stores the condition and the two possible statements
    private IExp exp;
    private IStmt thenStmt;
    private IStmt elseStmt;


    public IfStmt(IExp exp, IStmt thenStmt, IStmt elseStmt) {
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        // Evaluates the condition using SymTable
        IValue value = exp.eval(state.getSymTable());

        if (!(value instanceof BoolValue)) {
            throw new StatementException("condition is not a boolean");
        }

        // Extracts the boolean value (true or false)
        boolean condition = ((BoolValue) value).val();

        // Pushes the selected branch onto ExeStack
        if (condition) {
            state.getExeStack().push(thenStmt);
        } else {
            state.getExeStack().push(elseStmt);
        }

        // Returns the updated program state
        return state;
    }

    @Override
    public String toString() {

        return "if " + exp + " then " + thenStmt +
                " else " + elseStmt;
    }
}
