
package interpreter.model.type;

import interpreter.model.value.IValue;
import interpreter.model.value.StringValue;

// string type
// default value for string variables
public record StringType() implements IType {

    @Override
    public IValue defaultValue() {

        return new StringValue("");
    }

    @Override
    public String toString() {
        // Displays the type as "string"
        return "string";
    }
}
