
package interpreter.model;

import interpreter.model.adt.IStack;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import java.io.BufferedReader;

public class PrgState {

    // Instructions
    private IStack<IStmt> exeStack;

    // Stores variable names and theirvalues
    private IDictionary<String, IValue> symTable;

    // Stores the values produced by print statemeents
    private IList<IValue> out;

    // A3: Store opened files and readers
    private IDictionary<StringValue, BufferedReader> fileTable;

    // Keeps the original program
    private final IStmt originalProgram;

    // Initializes the program state
    public PrgState(IStack<IStmt> exeStack,
                    IDictionary<String, IValue> symTable,
                    IList<IValue> out,
                    IDictionary<StringValue, BufferedReader> fileTable,
                    IStmt program) {

        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;

        // A3: Initializes the file table
        this.fileTable = fileTable;

        this.originalProgram = program;

        // Adds the program to the execution stack
        exeStack.push(program);
    }

    // A3: Create a program state with empty data structures
    public static PrgState of(IStmt program) {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                program
        );
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

    // A3: Returns the file table
    public IDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
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

    // A3: Replaces the file table
    public void setFileTable(IDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    @Override
    public String toString() {

        return "ExeStack: " + exeStack +
                "\nSymTable: " + symTable +
                "\nOut: " + out +
                "\nFileTable: " + fileTable; // A3
    }


    // A3: Converts the program state into text for log file
    public String toLogString() {

        var sb = new StringBuilder();

        // ExeStack top element first
        sb.append("ExeStack:\n");
        exeStack.toList().forEach(s -> sb.append(s).append('\n'));

        // variable names and their values
        sb.append("SymTable:\n");
        symTable.toMap().forEach((k, v) ->
                sb.append(k).append(" --> ").append(v).append('\n'));

        // the output values in order
        sb.append("Out:\n");
        out.toList().forEach(v -> sb.append(v).append('\n'));

        // names of opened files
        sb.append("FileTable:\n");
        fileTable.toMap().keySet().forEach(f -> sb.append(f.val()).append('\n'));

        return sb.toString();
    }

}