
package interpreter.model.type;

import interpreter.exceptions.MyException;
import interpreter.model.PrgState;
import interpreter.model.stmt.NopStmt;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.value.RefValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefTypeTest {

    // A4: Checks that a reference has address 0 by default
    @Test
    void testDefaultValue() {
        RefType type = new RefType(new IntType());

        assertEquals(new RefValue(0, new IntType()), type.defaultValue());
    }

    // A4: Checks the default value of a declared reference variable
    @Test
    void testReferenceDeclaration() throws MyException {
        PrgState state = PrgState.of(new NopStmt());

        new VarDeclStmt("v", new RefType(new IntType())).execute(state);

        assertEquals(new RefValue(0, new IntType()),
                state.getSymTable().lookup("v"));
    }

    // A4: Checks equality of nested reference types
    @Test
    void testNestedReferenceTypes() {
        RefType a = new RefType(new RefType(new IntType()));
        RefType b = new RefType(new RefType(new IntType()));
        RefType c = new RefType(new IntType());

        assertEquals(a, b);
        assertNotEquals(a, c);
    }
}
