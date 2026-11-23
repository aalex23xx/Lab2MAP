
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.IOException;

// A3: Closes the BufferedReader and removes the file entry from the FileTable dictionary
public record CloseRFileStmt(IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        if (!(exp.eval(state.getSymTable()) instanceof StringValue fileName))
            throw new StatementException("closeRFile: expression is not a string");

        // Gets the file table
        var fileTable = state.getFileTable();


        if (!fileTable.isDefined(fileName))
            throw new StatementException("closeRFile: file is not open");

        try {
            // Closes the file reader
            fileTable.lookup(fileName).close();

        } catch (IOException e) {
            throw new StatementException("closeRFile: " + e.getMessage());
        }

        // Removes the closed file from FileTable
        fileTable.remove(fileName);

        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp + ")";
    }
}
