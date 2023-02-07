package task;

import exceptions.InvalidSyntaxException;
import ui.Command;

public class Task {


    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createFromInput(String[] splitInput) throws InvalidSyntaxException {
        try {
            return new Task(splitInput[1]);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidSyntaxException(Command.TODO.expectedSyntax);
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
