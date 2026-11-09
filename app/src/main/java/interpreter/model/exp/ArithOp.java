
package interpreter.model.exp;

// Defines the arithmetic operators used by ArithExp
// Each operator has a symbol for displaying expressions
public enum ArithOp {
    ADD("+"),
    SUB("-"),
    MUL("*"),
    DIV("/");

    // Stores the symbol associated with each operator
    private final String symbol;

    // Initializes the symbol for each operator
    ArithOp(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {

        return symbol;
    }
}
