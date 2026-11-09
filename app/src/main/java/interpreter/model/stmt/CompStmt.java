
package interpreter.model.stmt;

import interpreter.model.PrgState;

// S1: compound statement (first; second).
// Combines two statements and adds them to ExeStack

public record CompStmt(IStmt first, IStmt second) implements IStmt {

    @Override
    public PrgState execute(PrgState state) {

        state.getExeStack().push(second);
        state.getExeStack().push(first);

        // Returns the updated program state
        return state;
    }

    @Override
    public String toString() {

        return "(" + first + "; " + second + ")";
    }
}
