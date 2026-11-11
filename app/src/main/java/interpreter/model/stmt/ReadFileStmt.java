
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.IOException;

// A3: Reads an integer from an opened file into a variable
public record ReadFileStmt(IExp exp, String varName) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        var symTable = state.getSymTable();


        if (!symTable.isDefined(varName))
            throw new StatementException("readFile: variable is not declared");


        if (!(symTable.lookup(varName).getType() instanceof IntType))
            throw new StatementException("readFile: variable is not an integer");


        if (!(exp.eval(symTable) instanceof StringValue fileName))
            throw new StatementException("readFile: expression is not a string");

        var fileTable = state.getFileTable();


        if (!fileTable.isDefined(fileName))
            throw new StatementException("readFile: file is not open");

        try {
            // read one line
            String line = fileTable.lookup(fileName).readLine();

            int number = 0;

            // Converts the line to an integer or keeps 0 if the end of file is reached
            if (line != null)
                number = Integer.parseInt(line.strip());

            // Updates the variable in SymTable
            symTable.update(varName, new IntValue(number));

        } catch (IOException e) {
            throw new StatementException("readFile: " + e.getMessage());

        } catch (NumberFormatException e) {
            throw new StatementException("readFile: invalid integer");
        }

        return state;
    }

    @Override
    public String toString() {
        return "readFile(" + exp + ", " + varName + ")";
    }
}
