
package interpreter.model.exp;

import interpreter.exceptions.ExpressionException;
import interpreter.model.adt.MyDictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VarExpTest {

    @Test
    void testUndefinedVariable() {
        VarExp exp = new VarExp("x");

        assertThrows(ExpressionException.class,
                () -> exp.eval(new MyDictionary<>()));
    }
}
