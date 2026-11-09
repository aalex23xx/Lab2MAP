
package interpreter.model.stmt;

import interpreter.model.PrgState;

// S6: No Operation statement


public class NopStmt implements IStmt {

    @Override
    public PrgState execute(PrgState state) {

        return state;
    }

    @Override
    public String toString() {

        return "nop";
    }
}
