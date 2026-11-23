
package interpreter.model.stmt;

import interpreter.model.PrgState;
import interpreter.model.exp.IExp;
import interpreter.model.value.IValue;
import interpreter.exceptions.MyException;

// S4: print statement

// Stores the resulting value in Out (MyList)
public class PrintStmt implements IStmt {

    private IExp exp;

    // Initializes the print statement with an expression
    public PrintStmt(IExp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        // A4: Evaluates the expression using SymTable and Heap
        IValue value = exp.eval(state.getSymTable(), state.getHeap());

        // Add value to output list
        state.getOut().add(value);

        return state;
    }

    @Override
    public String toString() {

        return "print(" + exp + ")";
    }
}
