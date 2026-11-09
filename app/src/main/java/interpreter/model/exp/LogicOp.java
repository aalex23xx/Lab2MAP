
package interpreter.model.exp;

// Defines the logical operators
public enum LogicOp {
    AND("and"),
    OR("or");

    // Stores the symbol of each logical operator
    private final String symbol;

    // Initializes the symbol for each operator
    LogicOp(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
