
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

// A3: Tests opening files and handling errors
class OpenRFileStmtTest {

    @TempDir
    Path dir;

    @Test
    void testOpenFile() throws Exception {

        // A3: Creates a temporary file
        Path file = dir.resolve("test.in");
        Files.writeString(file, "15\n50");

        PrgState state = PrgState.of(new NopStmt());

        OpenRFileStmt stmt = new OpenRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        );

        // A3: Opens the file
        stmt.execute(state);

        // A3: Checks if the file is stored in FileTable
        assertTrue(state.getFileTable().isDefined(
                new StringValue(file.toString())
        ));

        // A3: Closes the file after testing
        new CloseRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        ).execute(state);
    }

    @Test
    void testNotString() {

        PrgState state = PrgState.of(new NopStmt());

        // A3: The file name is an integer instead of a string
        OpenRFileStmt stmt = new OpenRFileStmt(
                new ValueExp(new IntValue(5))
        );

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testAlreadyOpen() throws Exception {

        Path file = dir.resolve("test.in");
        Files.writeString(file, "15");

        PrgState state = PrgState.of(new NopStmt());

        OpenRFileStmt stmt = new OpenRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        );

        // A3: Opens the file for the first time
        stmt.execute(state);

        // A3: Opening the same file again must throw an exception
        assertThrows(StatementException.class,
                () -> stmt.execute(state));

        new CloseRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        ).execute(state);
    }

    @Test
    void testMissingFile() {

        PrgState state = PrgState.of(new NopStmt());

        // A3: The file does not exist
        Path file = dir.resolve("missing.in");

        OpenRFileStmt stmt = new OpenRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        );

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }
}
