
package interpreter.model.type;

import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;

// Represents the string type in Toy Language.
// Provides the default value for string variables.
public record StringType() implements IType {

    @Override
    public IValue defaultValue() {
        // Returns an empty string as the default value
        return new StringValue("");
    }

    @Override
    public String toString() {
        // Displays the type as "string"
        return "string";
    }
}
