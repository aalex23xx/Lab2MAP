
package interpreter.repository;

import interpreter.model.PrgState;
import interpreter.model.adt.*;
import interpreter.model.stmt.NopStmt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    @Test
    void testGetCrtPrg() {
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new NopStmt()
        );

        Repository repository = new Repository(state);

        assertSame(state, repository.getCrtPrg());
    }
}
