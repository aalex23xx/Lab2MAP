
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.IOException;

// A3: Closes an opened file and removes it from FileTable
public record CloseRFileStmt(IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        // A3: Checks if the expression is a string
        if (!(exp.eval(state.getSymTable()) instanceof StringValue fileName))
            throw new StatementException("closeRFile: expression is not a string");

        // A3: Gets the file table
        var fileTable = state.getFileTable();

        // A3: Checks if the file is open
        if (!fileTable.isDefined(fileName))
            throw new StatementException("closeRFile: file is not open");

        try {
            // A3: Closes the file reader
            fileTable.lookup(fileName).close();

        } catch (IOException e) {
            throw new StatementException("closeRFile: " + e.getMessage());
        }

        // A3: Removes the closed file from FileTable
        fileTable.remove(fileName);

        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp + ")";
    }
}
