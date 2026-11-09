
package interpreter.repository;

import interpreter.model.PrgState;
import java.util.ArrayList;
import java.util.List;

// Implements IRepository using an ArrayList.

public class Repository implements IRepository {

    // Stores the program states in a list
    private List<PrgState> programs = new ArrayList<>();

    // Initializes the repository with a program state
    public Repository(PrgState program) {
        programs.add(program);
    }

    @Override
    public PrgState getCrtPrg() {
        // Returns the first program state from the list
        return programs.get(0);
    }
}
