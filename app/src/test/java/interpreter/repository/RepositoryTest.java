
package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.model.stmt.NopStmt;

import org.junit.jupiter.api.Test;

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
}
