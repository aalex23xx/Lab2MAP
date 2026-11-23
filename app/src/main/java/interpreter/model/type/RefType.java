
package interpreter.model.type;

import interpreter.model.value.IValue;
import interpreter.model.value.RefValue;

// A4: Represents a reference to another data type
public record RefType(IType inner) implements IType {

    // A4: A reference has address 0 by default
    @Override
    public IValue defaultValue() {
        return new RefValue(0, inner);
    }

    @Override
    public String toString() {
        return "Ref(" + inner + ")";
    }
}
