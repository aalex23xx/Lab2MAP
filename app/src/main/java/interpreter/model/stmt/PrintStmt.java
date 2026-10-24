
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.exceptions.MyException;

public class PrintStmt implements IStmt {

    private IExp exp;

    public PrintStmt(IExp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        IValue value = exp.eval(state.getSymTable());

        state.getOut().add(value);

        return state;
    }

    @Override
    public String toString() {
        return "print(" + exp + ")";
    }
}
