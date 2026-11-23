
package interpreter.model.exp;

import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyHeap;
import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;
import interpreter.model.value.BoolValue;
import interpreter.exceptions.ExpressionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RelationalExpTest {

    @Test
    void testOperators() throws Exception {

        var symTable = new MyDictionary<String, IValue>();

        // A4: Creates an empty Heap for expression evaluation
        var heap = new MyHeap();

        // Integer expressions used for comparisons
        IExp two = new ValueExp(new IntValue(2));
        IExp three = new ValueExp(new IntValue(3));

        // LT: less than
        assertEquals(new BoolValue(true),
                new RelationalExp(RelOp.LT, two, three).eval(symTable, heap));
        assertEquals(new BoolValue(false),
                new RelationalExp(RelOp.LT, three, two).eval(symTable, heap));

        // LE: less than or equal
        assertEquals(new BoolValue(true),
                new RelationalExp(RelOp.LE, two, three).eval(symTable, heap));
        assertEquals(new BoolValue(false),
                new RelationalExp(RelOp.LE, three, two).eval(symTable, heap));

        // EQ: equal
        assertEquals(new BoolValue(true),
                new RelationalExp(RelOp.EQ, two, two).eval(symTable, heap));
        assertEquals(new BoolValue(false),
                new RelationalExp(RelOp.EQ, two, three).eval(symTable, heap));

        // NE: not equal
        assertEquals(new BoolValue(true),
                new RelationalExp(RelOp.NE, two, three).eval(symTable, heap));
        assertEquals(new BoolValue(false),
                new RelationalExp(RelOp.NE, two, two).eval(symTable, heap));

        // GT: greater than
        assertEquals(new BoolValue(true),
                new RelationalExp(RelOp.GT, three, two).eval(symTable, heap));
        assertEquals(new BoolValue(false),
                new RelationalExp(RelOp.GT, two, three).eval(symTable, heap));

        // GE: greater than or equal
        assertEquals(new BoolValue(true),
                new RelationalExp(RelOp.GE, three, two).eval(symTable, heap));
        assertEquals(new BoolValue(false),
                new RelationalExp(RelOp.GE, two, three).eval(symTable, heap));
    }

    @Test
    void testInvalidOperands() {

        var symTable = new MyDictionary<String, IValue>();

        // A4: Creates an empty Heap for expression evaluation
        var heap = new MyHeap();

        IExp number = new ValueExp(new IntValue(2));
        IExp booleanExp = new ValueExp(new BoolValue(true));

        // The left operand is not an integer
        ExpressionException e1 = assertThrows(ExpressionException.class,
                () -> new RelationalExp(RelOp.LT, booleanExp, number).eval(symTable, heap));

        // A3: Checks the error message
        assertNotNull(e1.getMessage());
        assertFalse(e1.getMessage().isBlank());

        // The right operand is not an integer
        ExpressionException e2 = assertThrows(ExpressionException.class,
                () -> new RelationalExp(RelOp.LT, number, booleanExp).eval(symTable, heap));

        // A3: Checks the error message
        assertNotNull(e2.getMessage());
        assertFalse(e2.getMessage().isBlank());
    }
}
