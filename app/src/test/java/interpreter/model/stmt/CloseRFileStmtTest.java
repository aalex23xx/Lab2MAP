
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.value.StringValue;
import interpreter.model.value.IntValue;
import interpreter.exceptions.StatementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

// A3: Tests closing files and removing them from FileTable
class CloseRFileStmtTest {

    @TempDir
    Path dir;

    @Test
    void testCloseFile() throws Exception {

        // A3: Creates a temporary file
        Path file = dir.resolve("test.in");
        Files.writeString(file, "15\n50");

        PrgState state = PrgState.of(new NopStmt());

        StringValue fileName = new StringValue(file.toString());

        // A3: Opens the file
        new OpenRFileStmt(
                new ValueExp(fileName)
        ).execute(state);

        // A3: Checks that the file is open
        assertTrue(state.getFileTable().isDefined(fileName));

        // A3: Closes the file
        new CloseRFileStmt(
                new ValueExp(fileName)
        ).execute(state);

        // A3: Checks that the file was removed from FileTable
        assertFalse(state.getFileTable().isDefined(fileName));
    }

    @Test
    void testFileNotOpen() {

        PrgState state = PrgState.of(new NopStmt());

        CloseRFileStmt stmt = new CloseRFileStmt(
                new ValueExp(new StringValue("test.in"))
        );

        // A3: Cannot close a file that is not open
        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testNotString() {

        PrgState state = PrgState.of(new NopStmt());

        CloseRFileStmt stmt = new CloseRFileStmt(
                new ValueExp(new IntValue(5))
        );

        // A3: File name must be a string
        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }
}
