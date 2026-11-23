
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.value.IValue;

// Stores an IValue, which can be an IntValue or a BoolValue
// The eval() method returns the stored value directly,
// without calculations or accessing SymTable
public class ValueExp implements IExp {

    //(IntValue or BoolValue)
    private IValue value;

    // Initializes the expression with a value
    public ValueExp(IValue value) {
        this.value = value;
    }

    // A4: Accepts Heap as an additional parameter
    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException {

        return value;
    }

    @Override
    public String toString() {
        // Displays the constant value
        return value.toString();
    }
}
