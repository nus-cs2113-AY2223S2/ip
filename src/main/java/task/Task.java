package task;

import exceptions.InvalidSyntaxException;
import java.io.Serializable;
import ui.Syntax;

/**
 * Represents a regular to-do task
 */
public class Task implements Serializable {

    // For serialization
    private static final long serialVersionUID = (1 << 5);

    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a {@link Task} from user input
     *
     * @param splitInput Tokenized user input
     * @return Parsed Task
     * @throws InvalidSyntaxException If user input does not match expected syntax
     */
    public static Task createFromInput(String[] splitInput) throws InvalidSyntaxException {
        try {
            return new Task(splitInput[1]);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidSyntaxException(Syntax.TODO.expectedSyntax);
        }
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "x" : " ") + "] " + description;
    }
}
