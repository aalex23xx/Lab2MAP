
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.IDictionary;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;

// arithmetic expression with an operator and two operands
public record ArithExp(ArithOp op, IExp left, IExp right) implements IExp {

    @Override
    public IValue eval(IDictionary<String, IValue> symTable) throws MyException {

        // left operand is an integer
        if (!(left.eval(symTable) instanceof IntValue(int n1)))
            throw new ExpressionException("first operand is not an integer");

        // right operand is an integer
        if (!(right.eval(symTable) instanceof IntValue(int n2)))
            throw new ExpressionException("second operand is not an integer");


        return switch (op) {
            case ADD -> new IntValue(n1 + n2);
            case SUB -> new IntValue(n1 - n2);
            case MUL -> new IntValue(n1 * n2);
            case DIV -> {

                if (n2 == 0) throw new ExpressionException("division by zero");


                yield new IntValue(n1 / n2);
            }
        };
    }

    @Override
    public String toString() {
        // Displays the expression using the operator symbol
        return "(" + left + " " + op + " " + right + ")";
    }
}
