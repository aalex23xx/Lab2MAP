
package interpreter.model.exp;

import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;

// Compares two integer expressions using a relational operator.
// Returns a boolean value (true or false).
public record RelationalExp(RelOp op, IExp left, IExp right) implements IExp {

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {

        // Evaluates the left expression and checks if it is an integer
        if (!(left.eval(symTable) instanceof IntValue(int n1)))
            throw new ExpressionException("first operand is not an integer");

        // Evaluates the right expression and checks if it is an integer
        if (!(right.eval(symTable) instanceof IntValue(int n2)))
            throw new ExpressionException("second operand is not an integer");

        // Compares the two integers and returns a BoolValue
        return new BoolValue(switch (op) {
            case LT -> n1 < n2;
            case LE -> n1 <= n2;
            case EQ -> n1 == n2;
            case NE -> n1 != n2;
            case GT -> n1 > n2;
            case GE -> n1 >= n2;
        });
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }
}
