
package interpreter.model.exp;

import interpreter.exceptions.MyException;
import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;
import interpreter.model.value.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicExpTest {

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
    void testWrongOperandType() {
        LogicExp exp = new LogicExp(LogicOp.AND,
                new ValueExp(new IntValue(5)),
                new ValueExp(new BoolValue(true)));

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }
}
