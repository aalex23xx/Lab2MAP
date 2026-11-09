
package interpreter.model;

import interpreter.model.adt.IStack;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IList;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;


public class PrgState {

    // Instructions
    private IStack<IStmt> exeStack;

    // Stores variable names and theirvalues
    private IDictionary<String, IValue> symTable;

    // Stores the values produced by print statemeents
    private IList<IValue> out;

    // Keeps the original program
    private final IStmt originalProgram;

    // Initializes the program state
    public PrgState(IStack<IStmt> exeStack,
                    IDictionary<String, IValue> symTable,
                    IList<IValue> out,
                    IStmt program) {

        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = program;

        // Adds the program to the execution stack
        exeStack.push(program);
    }

    // GETTERS-SETTERS

    // Returns the execution stack
    public IStack<IStmt> getExeStack() {
        return exeStack;
    }

    // Returns the symbol table
    public IDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    // Returns the output list
    public IList<IValue> getOut() {
        return out;
    }

    // Returns the original program
    public IStmt getOriginalProgram() {
        return originalProgram;
    }



    // Replaces the execution stack
    public void setExeStack(IStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    // Replaces the symbol table
    public void setSymTable(IDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    // Replaces the output list
    public void setOut(IList<IValue> out) {
        this.out = out;
    }

    @Override
    public String toString() {

        return "ExeStack: " + exeStack +
                "\nSymTable: " + symTable +
                "\nOut: " + out;
    }
}
