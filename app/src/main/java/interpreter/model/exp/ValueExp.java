
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.model.adt.IDictionary;
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

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {

        return value;
    }

    @Override
    public String toString() {
        // Displays the constant value
        return value.toString();
    }
}
