
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

// A4: S11 - Allocates a new value in the Heap
public record NewStmt(String varName, IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {
        var symTable = state.getSymTable();

        // Checks that the variable exists
        if (!symTable.isDefined(varName))
            throw new StatementException("new: " + varName + " is not declared");

        // A4: Checks that the variable is a reference
        if (!(symTable.lookup(varName) instanceof RefValue ref))
            throw new StatementException("new: " + varName + " is not a reference");

        // Evaluates the expression and checks its type
        IValue value = exp.eval(symTable, state.getHeap());
        if (!value.getType().equals(ref.locationType()))
            throw new StatementException("new: " + exp + " has type " + value.getType()
                    + ", expected " + ref.locationType());

        // Allocate the value and updates the reference
        int address = state.getHeap().allocate(value);
        symTable.update(varName, new RefValue(address, ref.locationType()));

        return state;
    }

    @Override
    public String toString() {
        return "new(" + varName + ", " + exp + ")";
    }
}
