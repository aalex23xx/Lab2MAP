
package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.exceptions.MyException;
import interpreter.exceptions.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

// Implements IRepository using an ArrayList.
public class Repository implements IRepository {

    // Stores the program states in a list
    private List<PrgState> programs = new ArrayList<>();

    // A3: Stores the path
    private final String logFilePath;

    // Initializes the repository with a program state
    public Repository(PrgState program, String logFilePath) {
        programs.add(program);

        // A3: Saves the log file path
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        // Returns the first program state from the list
        return programs.get(0);
    }

    // A3: Write the current program state to the log file
    @Override
    public void logPrgStateExec() throws MyException {

        try (var logFile = new PrintWriter(
                new BufferedWriter(new FileWriter(logFilePath, true)))) {

            // Appends the program state to the log file
            logFile.println(getCrtPrg().toLogString());

        } catch (IOException e) {
            throw new RepositoryException("cannot write the log file " + logFilePath
                    + ": " + e.getMessage());
        }
    }
}
