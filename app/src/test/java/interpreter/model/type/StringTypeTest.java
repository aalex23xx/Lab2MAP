
package interpreter.model.type;

import interpreter.model.PrgState;
import interpreter.model.stmt.VarDeclStmt;
import interpreter.model.value.StringValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTypeTest {

    @Test
    void testDefaultValue() {
        StringType type = new StringType();

        // Checks the default value of StringType
        assertEquals(new StringValue(""), type.defaultValue());
    }

    @Test
    void testStringDeclaration() throws Exception {

        // Creates a string variable declaration
        VarDeclStmt declaration =
                new VarDeclStmt("s", new StringType());

        // A3: Creates the program state using the factory method
        PrgState state = PrgState.of(declaration);

        // Executes the declaration
        declaration.execute(state);

        // Checks if s has the default value ""
        assertEquals(new StringValue(""),
                state.getSymTable().lookup("s"));
    }
}
