
package interpreter.model.value;

import interpreter.model.type.IType;
import interpreter.model.type.StringType;

// Represents a string value in Toy Language.
// Stores text and returns its corresponding type.
public record StringValue(String val) implements IValue {

    @Override
    public IType getType() {
        // Returns the string type of this value
        return new StringType();
    }

    @Override
    public String toString() {
        // Displays the string value between quotation marks
        return "\"" + val + "\"";
    }
}
