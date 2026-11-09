
package interpreter.model.type;

import interpreter.model.PrgState;
import interpreter.model.adt.MyStack;
import interpreter.model.adt.MyDictionary;
import interpreter.model.adt.MyList;
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

        // Creates the program state
        PrgState state = new PrgState(
                new MyStack<>(),
                new MyDictionary<>(),
                new MyList<>(),
                declaration
        );

        // Executes the declaration
        declaration.execute(state);

        // Checks if s has the default value ""
        assertEquals(new StringValue(""),
                state.getSymTable().lookup("s"));
    }
}
