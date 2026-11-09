
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.model.adt.IDictionary;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

// S2: assignment statement (id = exp)
// Evaluates an expression and assigns its value to an existing variable

public record AssignStmt(String id, IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        // symbol table from the current program state
        var symTable = state.getSymTable();

        // Checks if the variable was declared
        if (!symTable.isDefined(id))
            throw new StatementException("variable " + id + " was not declared");


        IValue val = exp.eval(symTable);

        // Checks if the expression type matches the variable type
        if (!val.getType().equals(symTable.lookup(id).getType()))
            throw new StatementException("declared type of variable " + id
                    + " and type of the assigned expression do not match");


        symTable.update(id, val);


        return state;
    }

    @Override
    public String toString() {

        return id + " = " + exp;
    }
}
