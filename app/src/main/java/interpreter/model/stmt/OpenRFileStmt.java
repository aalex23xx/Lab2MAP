
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.StringValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// A3: Opens a text file and stores its reader in FileTable
public record OpenRFileStmt(IExp exp) implements IStmt {

    @Override
    public PrgState execute(PrgState state) throws MyException {

        // A3: Checks if the expression is a string
        if (!(exp.eval(state.getSymTable()) instanceof StringValue fileName))
            throw new StatementException("openRFile: " + exp + " is not a string");

        // A3: Gets the file table from the program state
        var fileTable = state.getFileTable();

        // A3: Checks if the file is already open
        if (fileTable.isDefined(fileName))
            throw new StatementException("openRFile: " + fileName + " is already open");

        try {
            // A3: Opens the file for reading
            // The file stays open until closeRFile
            var reader = new BufferedReader(new FileReader(fileName.val()));

            // A3: Stores the file name and reader in FileTable
            fileTable.put(fileName, reader);

        } catch (IOException e) {
            throw new StatementException("openRFile: cannot open " + fileName
                    + ": " + e.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + exp + ")";
    }
}
