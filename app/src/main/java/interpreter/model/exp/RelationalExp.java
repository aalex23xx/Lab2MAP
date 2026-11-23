
package interpreter.model.exp;

import interpreter.model.adt.IDictionary;
import interpreter.model.adt.IHeap;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;
import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;

// Compares two integer expressions using a relational operator
// Returns true or false
public record RelationalExp(RelOp op, IExp left, IExp right) implements IExp {

    // A4: Evaluates relational expressions using SymTable and Heap
    @Override
    public IValue eval(IDictionary<String, IValue> symTable, IHeap heap) throws MyException {

        // A4: Passes Heap when evaluating the left expression
        if (!(left.eval(symTable, heap) instanceof IntValue(int n1)))
            throw new ExpressionException("first operand is not an integer");

        // A4: Passes Heap when evaluating the right expression
        if (!(right.eval(symTable, heap) instanceof IntValue(int n2)))
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
