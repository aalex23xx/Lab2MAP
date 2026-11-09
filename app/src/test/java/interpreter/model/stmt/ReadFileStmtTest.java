
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.type.BoolType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;
import interpreter.exceptions.StatementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

// A3: Tests reading integers from files and handling errors
class ReadFileStmtTest {

    @TempDir
    Path dir;

    @Test
    void testReadFile() throws Exception {

        // A3: Creates a file containing two numbers
        Path file = dir.resolve("numbers.in");
        Files.writeString(file, "15\n50");

        PrgState state = PrgState.of(new NopStmt());

        // A3: Declares integer variable x
        new VarDeclStmt("x", new IntType()).execute(state);

        // A3: Opens the file
        new OpenRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        ).execute(state);

        ReadFileStmt stmt = new ReadFileStmt(
                new ValueExp(new StringValue(file.toString())), "x"
        );

        // A3: Reads the first number
        stmt.execute(state);
        assertEquals(new IntValue(15), state.getSymTable().lookup("x"));

        // A3: Reads the second number
        stmt.execute(state);
        assertEquals(new IntValue(50), state.getSymTable().lookup("x"));

        // A3: End of file gives 0
        stmt.execute(state);
        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));

        // A3: Closes the file
        new CloseRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        ).execute(state);
    }

    @Test
    void testUndeclaredVariable() {

        PrgState state = PrgState.of(new NopStmt());

        ReadFileStmt stmt = new ReadFileStmt(
                new ValueExp(new StringValue("test.in")), "x"
        );

        // A3: The variable x was not declared
        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testWrongType() throws Exception {

        PrgState state = PrgState.of(new NopStmt());

        // A3: Declares a boolean instead of an integer
        new VarDeclStmt("x", new BoolType()).execute(state);

        ReadFileStmt stmt = new ReadFileStmt(
                new ValueExp(new StringValue("test.in")), "x"
        );

        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testFileNotOpen() throws Exception {

        PrgState state = PrgState.of(new NopStmt());

        new VarDeclStmt("x", new IntType()).execute(state);

        ReadFileStmt stmt = new ReadFileStmt(
                new ValueExp(new StringValue("test.in")), "x"
        );

        // A3: The file was never opened
        assertThrows(StatementException.class,
                () -> stmt.execute(state));
    }

    @Test
    void testInvalidInteger() throws Exception {

        // A3: Creates a file containing invalid text
        Path file = dir.resolve("numbers.in");
        Files.writeString(file, "hello");

        PrgState state = PrgState.of(new NopStmt());

        new VarDeclStmt("x", new IntType()).execute(state);

        new OpenRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        ).execute(state);

        ReadFileStmt stmt = new ReadFileStmt(
                new ValueExp(new StringValue(file.toString())), "x"
        );

        // A3: The line cannot be converted to an integer
        assertThrows(StatementException.class,
                () -> stmt.execute(state));

        new CloseRFileStmt(
                new ValueExp(new StringValue(file.toString()))
        ).execute(state);
    }
}
