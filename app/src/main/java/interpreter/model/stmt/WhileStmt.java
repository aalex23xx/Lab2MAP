
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.BoolValue;
import interpreter.model.value.IValue;

// A4: S13 - Executes a statement repeatedly while the condition is true
public record WhileStmt(IExp cond, IStmt body) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        // Evaluate condition using SymTable and Heap
        IValue value = cond.eval(state.getSymTable(), state.getHeap());


        if (!(value instanceof BoolValue boolValue))
            throw new StatementException("while: condition is not a boolean");

        // Pushes the loop and its body when the condition is true
        if (boolValue.val()) {
            state.getExeStack().push(this);
            state.getExeStack().push(body);
        }

        return state;
    }

    @Override
    public String toString() {
        return "(while (" + cond + ") " + body + ")";
    }
}
