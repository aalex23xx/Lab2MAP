
package interpreter.model.value;

import interpreter.model.type.IType;
import interpreter.model.type.StringType;

// string value
// Store text and return correct type
public record StringValue(String val) implements IValue {

    @Override
    public IType getType() {

        return new StringType();
    }

    @Override
    public String toString() {
        // Displays the string value
        return "\"" + val + "\"";
    }
}
