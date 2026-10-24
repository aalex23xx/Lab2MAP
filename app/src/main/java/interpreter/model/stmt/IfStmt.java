
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.BoolValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

public class IfStmt implements IStmt {

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

        IValue value = exp.eval(state.getSymTable());

        if (!(value instanceof BoolValue)) {
            throw new StatementException("condition is not a boolean");
        }

        boolean condition = ((BoolValue) value).val();

        if (condition) {
            state.getExeStack().push(thenStmt);
        } else {
            state.getExeStack().push(elseStmt);
        }

        return state;
    }

    @Override
    public String toString() {
        return "if " + exp + " then " + thenStmt +
                " else " + elseStmt;
    }
}
