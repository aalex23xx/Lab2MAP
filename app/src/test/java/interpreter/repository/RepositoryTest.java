
package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.model.stmt.NopStmt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    @Test
    void testGetCrtPrg() {

        // A3: Creates the program state using the factory method
        PrgState state = PrgState.of(new NopStmt());

        // A3: Repository now requires a log file path
        Repository repository = new Repository(state, "log-test.txt");

        assertSame(state, repository.getCrtPrg());
    }

    // A3: Tests writing the program state to a log file
    @Test
    void testLogPrgStateExec(@TempDir Path dir) throws Exception {

        PrgState state = PrgState.of(new NopStmt());

        // A3: Creates a temporary log file path
        Path file = dir.resolve("log-test.txt");
        Repository repository = new Repository(state, file.toString());

        // A3: Writes the program state
        repository.logPrgStateExec();

        // A3: Reads the log file
        String log = Files.readString(file);

        // A3: Checks the four required sections
        assertTrue(log.contains("ExeStack:"));
        assertTrue(log.contains("SymTable:"));
        assertTrue(log.contains("Out:"));
        assertTrue(log.contains("FileTable:"));

        // A3: Checks that another log is appended
        repository.logPrgStateExec();
        assertEquals(log + log, Files.readString(file));
    }
}
