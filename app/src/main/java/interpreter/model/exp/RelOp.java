
package interpreter.model.exp;


// Each operator has a symbol used for displaying expressions
public enum RelOp {
    LT("<"), LE("<="), EQ("=="), NE("!="), GT(">"), GE(">=");

    // Stores the symbol of each operator
    private final String symbol;

    // Initializes the operator symbol
    RelOp(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
