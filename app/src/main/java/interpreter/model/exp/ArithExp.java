
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;

public class ArithExp implements IExp {

    private ArithOp op;
    private IExp left;
    private IExp right;

    public ArithExp(ArithOp op, IExp left, IExp right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {

        IValue v1 = left.eval(symTable);
        if (!(v1 instanceof IntValue)) {
            throw new ExpressionException("first operand is not an integer");
        }

        IValue v2 = right.eval(symTable);
        if (!(v2 instanceof IntValue)) {
            throw new ExpressionException("second operand is not an integer");
        }

        int n1 = ((IntValue) v1).val();
        int n2 = ((IntValue) v2).val();

        int result;

        if (op == ArithOp.ADD) {
            result = n1 + n2;
        } else if (op == ArithOp.SUB) {
            result = n1 - n2;
        } else if (op == ArithOp.MUL) {
            result = n1 * n2;
        } else {
            if (n2 == 0) {
                throw new ExpressionException("division by zero");
            }
            result = n1 / n2;
        }

        return new IntValue(result);
    }

    @Override
    public String toString() {
        return "(" + left + " " + op + " " + right + ")";
    }
}
