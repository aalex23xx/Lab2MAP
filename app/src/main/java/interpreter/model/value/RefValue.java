
package interpreter.model.value;

import interpreter.model.type.IType;
import interpreter.model.type.RefType;

// A4: Stores a memory address and the type of the value stored there
public record RefValue(int address, IType locationType) implements IValue {

    // A4: Returns the reference type of this value
    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}
