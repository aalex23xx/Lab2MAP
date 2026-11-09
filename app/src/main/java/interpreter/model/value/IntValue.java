
package interpreter.model.value;

import interpreter.model.type.IType;
import interpreter.model.type.IntType;

// Represents an integer value in Toy Language

public record IntValue(int val) implements IValue {

    @Override
    public IType getType() {
        // Returns the integer type of this value
        return new IntType();
    }

    @Override
    public String toString() {

        return Integer.toString(val);
    }
}
