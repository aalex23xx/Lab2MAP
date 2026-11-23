
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

// A4: S12 - Updates an existing value in the Heap
public record WriteHeapStmt(String varName, IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        var symTable = state.getSymTable();

        // A4: Checks that the variable exists
        if (!symTable.isDefined(varName))
            throw new StatementException("wH: " + varName + " is not declared");

        // A4: Checks that the variable is a reference
        if (!(symTable.lookup(varName) instanceof RefValue ref))
            throw new StatementException("wH: " + varName + " is not a reference");

        // A4: Checks that the address exists in the Heap
        if (!state.getHeap().isDefined(ref.address()))
            throw new StatementException("wH: address is not in the Heap");

        // A4: Evaluates the new value
        IValue value = exp.eval(symTable, state.getHeap());

        // A4: Checks that the new value has the correct type
        if (!value.getType().equals(ref.locationType()))
            throw new StatementException("wH: type mismatch");

        // A4: Updates the value stored at the address
        state.getHeap().update(ref.address(), value);

        return state;
    }

    @Override
    public String toString() {
        return "wH(" + varName + ", " + exp + ")";
    }
}
