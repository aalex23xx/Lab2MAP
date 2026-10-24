
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArithExpTest {

    @Test
    void testAddition() throws MyException {
        ArithExp exp = new ArithExp(ArithOp.ADD,
                new ValueExp(new IntValue(2)),
                new ValueExp(new IntValue(3)));

        assertEquals(new IntValue(5),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testSubtraction() throws MyException {
        ArithExp exp = new ArithExp(ArithOp.SUB,
                new ValueExp(new IntValue(5)),
                new ValueExp(new IntValue(2)));

        assertEquals(new IntValue(3),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testMultiplication() throws MyException {
        ArithExp exp = new ArithExp(ArithOp.MUL,
                new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(4)));

        assertEquals(new IntValue(12),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testDivision() throws MyException {
        ArithExp exp = new ArithExp(ArithOp.DIV,
                new ValueExp(new IntValue(10)),
                new ValueExp(new IntValue(2)));

        assertEquals(new IntValue(5),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testDivisionByZero() {
        ArithExp exp = new ArithExp(ArithOp.DIV,
                new ValueExp(new IntValue(10)),
                new ValueExp(new IntValue(0)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }

    @Test
    void testWrongOperandType() {
        ArithExp exp = new ArithExp(ArithOp.ADD,
                new ValueExp(new BoolValue(true)),
                new ValueExp(new IntValue(2)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }
}
