
package interpreter.model;

import interpreter.model.adt.IStack;
import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IList;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
// A4: Imports the Heap interface and implementation
import interpreter.model.adt.IHeap;
import interpreter.model.adt.MyHeap;
import interpreter.model.stmt.IStmt;
import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;
import java.io.BufferedReader;
// A4: Sorts heap addresses when writing the log
import java.util.TreeMap;

public class PrgState {

    // Instructions
    private IStack<IStmt> exeStack;

    // Stores variable names and their values
    private IDictionary<String, IValue> symTable;

    // Stores the values produced by print statements
    private IList<IValue> out;

    // A3: Store opened files and readers
    private IDictionary<StringValue, BufferedReader> fileTable;

    // A4: Stores dynamically allocated values at heap addresses
    private IHeap heap;

    // Keeps the original program
    private final IStmt originalProgram;

    // Initializes the program state
    public PrgState(IStack<IStmt> exeStack,
                    IDictionary<String, IValue> symTable,
                    IList<IValue> out,
                    IDictionary<StringValue, BufferedReader> fileTable,
                    IHeap heap,
                    IStmt program) {

        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;

        // A3: Initializes the file table
        this.fileTable = fileTable;

        // A4: Initializes the Heap
        this.heap = heap;

        this.originalProgram = program;

        // Adds the program to the execution stack
        exeStack.push(program);
    }

    // A4: Creates a program state with all five data structures
    public static PrgState of(IStmt program) {
        return new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                new MyDictionary<>(),
                new MyHeap(),
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

    // A4: Returns the Heap
    public IHeap getHeap() {
        return heap;
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

        // Variable names and their values
        sb.append("SymTable:\n");
        symTable.toMap().forEach((k, v) ->
                sb.append(k).append(" --> ").append(v).append('\n'));

        // The output values in order
        sb.append("Out:\n");
        out.toList().forEach(v -> sb.append(v).append('\n'));

        // Names of opened files
        sb.append("FileTable:\n");
        fileTable.toMap().keySet().forEach(f -> sb.append(f.val()).append('\n'));

        // A4: Write the Heap values sorted by address
        sb.append("Heap:\n");
        new TreeMap<>(heap.getContent()).forEach((address, value) ->
                sb.append(address).append(" --> ").append(value).append('\n'));

        return sb.toString();
    }
}
