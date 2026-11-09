
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.BoolValue;

// logical expression with two operands
public class LogicExp implements IExp {

    // Stores the logical operator and the two expressions
    private LogicOp op;
    private IExp left;
    private IExp right;

    // the logical expression
    public LogicExp(LogicOp op, IExp left, IExp right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {

        // left operand type
        IValue v1 = left.eval(symTable);
        if (!(v1 instanceof BoolValue)) {
            throw new ExpressionException("first operand is not a boolean");
        }

        // right operand type
        IValue v2 = right.eval(symTable);
        if (!(v2 instanceof BoolValue)) {
            throw new ExpressionException("second operand is not a boolean");
        }

        // Extracts the boolean values
        boolean b1 = ((BoolValue) v1).val();
        boolean b2 = ((BoolValue) v2).val();

        // Performs the selected logical operation
        boolean result;

        if (op == LogicOp.AND) {
            result = b1 && b2;
        } else {
            result = b1 || b2;
        }

        // Returns the result as a BoolValue
        return new BoolValue(result);
    }

    @Override
    public String toString() {

        return "(" + left + " " + op + " " + right + ")";
    }
}
