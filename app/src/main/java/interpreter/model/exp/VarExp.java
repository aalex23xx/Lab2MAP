
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;

// expression that accesses a variable
public class VarExp implements IExp {

    // variable name
    private String id;


    public VarExp(String id) {
        this.id = id;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {

        // if the variable exists in SymTable
        if (!symTable.isDefined(id)) {
            throw new ExpressionException("variable " + id + " is not defined");
        }


        return symTable.lookup(id);
    }

    @Override
    public String toString() {

        return id;
    }
}
