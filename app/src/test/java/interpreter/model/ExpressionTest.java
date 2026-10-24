
package interpreter.model;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.exp.*;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {

    @Test
    void testValueExp() throws MyException {
        ValueExp exp = new ValueExp(new IntValue(5));

        assertEquals(new IntValue(5),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testVarExp() throws MyException {
        MyDictionary<String, IValue> table = new MyDictionary<>();
        table.put("x", new IntValue(7));

        VarExp exp = new VarExp("x");

        assertEquals(new IntValue(7), exp.eval(table));
    }

    @Test
    void testUndefinedVariable() {
        VarExp exp = new VarExp("x");

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }

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
                new ValueExp(new IntValue(8)),
                new ValueExp(new IntValue(3)));

        assertEquals(new IntValue(5),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testMultiplication() throws MyException {
        ArithExp exp = new ArithExp(ArithOp.MUL,
                new ValueExp(new IntValue(3)),
                new ValueExp(new IntValue(5)));

        assertEquals(new IntValue(15),
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
    void testFirstArithmeticOperand() {
        ArithExp exp = new ArithExp(ArithOp.ADD,
                new ValueExp(new BoolValue(true)),
                new ValueExp(new IntValue(2)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }

    @Test
    void testSecondArithmeticOperand() {
        ArithExp exp = new ArithExp(ArithOp.ADD,
                new ValueExp(new IntValue(2)),
                new ValueExp(new BoolValue(true)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }

    @Test
    void testAnd() throws MyException {
        LogicExp exp = new LogicExp(LogicOp.AND,
                new ValueExp(new BoolValue(true)),
                new ValueExp(new BoolValue(false)));

        assertEquals(new BoolValue(false),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testOr() throws MyException {
        LogicExp exp = new LogicExp(LogicOp.OR,
                new ValueExp(new BoolValue(true)),
                new ValueExp(new BoolValue(false)));

        assertEquals(new BoolValue(true),
                exp.eval(new MyDictionary<>()));
    }

    @Test
    void testFirstLogicalOperand() {
        LogicExp exp = new LogicExp(LogicOp.AND,
                new ValueExp(new IntValue(2)),
                new ValueExp(new BoolValue(true)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }

    @Test
    void testSecondLogicalOperand() {
        LogicExp exp = new LogicExp(LogicOp.OR,
                new ValueExp(new BoolValue(true)),
                new ValueExp(new IntValue(2)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }
}
