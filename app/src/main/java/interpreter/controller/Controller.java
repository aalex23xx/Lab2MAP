
package interpreter.controller;

import interpreter.repository.IRepository;
import interpreter.model.PrgState;
import interpreter.model.stmt.IStmt;
import interpreter.exceptions.MyException;
import interpreter.exceptions.StatementException;


// statements from ExeStack

public class Controller {


    private IRepository repo;
    private boolean displayFlag; //state

    // repository and display option
    public Controller(IRepository repo, boolean displayFlag) {
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    public PrgState oneStep(PrgState state) throws MyException {

        // Gets the execution stack from the program state
        var stack = state.getExeStack();

        // Checks if there are any statements left to execute
        if (stack.isEmpty())
            throw new StatementException("program state stack is empty");

        // Removes the next statement
        IStmt crtStmt = stack.pop();

        // updated
        return crtStmt.execute(state);
    }

    // Executes the program until ExeStack becomes empty
    public void allStep() throws MyException {

        // Gets the current program state from Repository
        PrgState prg = repo.getCrtPrg();

        // A3: Saves the initial program state to the log file
        repo.logPrgStateExec();

        // Displays the initial state if enabled
        if (displayFlag)
            display(prg);

        // Executes statements one by one until the stack is empty
        while (!prg.getExeStack().isEmpty()) {
            oneStep(prg);

            // A3: Saves the program state after each execution step
            repo.logPrgStateExec();

            // Displays the state
            if (displayFlag)
                display(prg);
        }
    }

    // Prints (ExeStack, SymTable, Out)
    public void display(PrgState state) {
        System.out.println(state);
    }
}
