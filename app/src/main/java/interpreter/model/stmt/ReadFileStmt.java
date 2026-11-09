
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

        // A3: Checks if the variable is declared
        if (!symTable.isDefined(varName))
            throw new StatementException("readFile: variable is not declared");

        // A3: Checks if the variable has type int
        if (!(symTable.lookup(varName).getType() instanceof IntType))
            throw new StatementException("readFile: variable is not an integer");

        // A3: Checks if the file name is a string
        if (!(exp.eval(symTable) instanceof StringValue fileName))
            throw new StatementException("readFile: expression is not a string");

        var fileTable = state.getFileTable();

        // A3: Checks if the file is open
        if (!fileTable.isDefined(fileName))
            throw new StatementException("readFile: file is not open");

        try {
            // A3: Reads one line from the file
            String line = fileTable.lookup(fileName).readLine();

            int number = 0;

            // A3: Converts the line to an integer (0 at end of file)
            if (line != null)
                number = Integer.parseInt(line.strip());

            // A3: Updates the variable in SymTable
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
