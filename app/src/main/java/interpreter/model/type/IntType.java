
package interpreter.model.type;

import interpreter.model.value.IValue;
import interpreter.model.value.IntValue;

// Represents the integer type in Toy Language

public record IntType() implements IType {

    @Override
    public IValue defaultValue() {
        // Returns 0 as the default value for integer variables
        return new IntValue(0);
    }

    @Override
    public String toString() {

        return "int";
    }
}
