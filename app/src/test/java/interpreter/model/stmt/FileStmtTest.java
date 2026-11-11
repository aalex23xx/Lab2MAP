
package interpreter.model.stmt;

import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;
import interpreter.model.PrgState;
import interpreter.model.exp.ValueExp;
import interpreter.model.type.IntType;
import interpreter.model.value.IntValue;
import interpreter.model.value.StringValue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

// A3: Tests file statements using temporary files
class FileStmtTest {

    @TempDir
    Path dir;

    private PrgState state;
    private ValueExp file;

    @BeforeEach
    void setUp() throws Exception {
        Path f = dir.resolve("numbers.in");
        Files.writeString(f, "7\n");

        file = new ValueExp(new StringValue(f.toString()));
        state = PrgState.of(new NopStmt());

        new VarDeclStmt("x", new IntType()).execute(state);
    }

    @Test
    void readGivesTheNextNumberThenZeroAtTheEnd() throws MyException {
        new OpenRFileStmt(file).execute(state);
        new ReadFileStmt(file, "x").execute(state);

        assertEquals(new IntValue(7), state.getSymTable().lookup("x"));

        new ReadFileStmt(file, "x").execute(state);

        assertEquals(new IntValue(0), state.getSymTable().lookup("x"));

        // A3: Closes the file so JUnit can delete it on Windows
        new CloseRFileStmt(file).execute(state);
    }

    @Test
    void openingTheSameFileTwiceThrows() throws MyException {
        new OpenRFileStmt(file).execute(state);

        assertThrows(StatementException.class,
                () -> new OpenRFileStmt(file).execute(state));

        // A3: Closes the file so JUnit can delete it on Windows
        new CloseRFileStmt(file).execute(state);
    }
}
