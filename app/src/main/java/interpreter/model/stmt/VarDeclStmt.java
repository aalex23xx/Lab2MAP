
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.type.IType;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.exceptions.StatementException;

// S3: variable declaration statement

// Prevent declaring the same variable more than once
public class VarDeclStmt implements IStmt {

    // Stores the variable name and its type
    private String name;
    private IType type;

    // Initializes the declaration with a name and type
    public VarDeclStmt(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException {

        // Gets the symbol table from the current program state
        IDictionary<String, IValue> symTable = state.getSymTable();

        if (symTable.isDefined(name)) {
            throw new StatementException("variable already declared");
        }

        // Adds the variable with the default value of its type
        symTable.put(name, type.defaultValue());


        return state;
    }

    @Override
    public String toString() {

        return type + " " + name;
    }
}
