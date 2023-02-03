package orca;

public class Todo extends Task {
    public Todo(String userInput, int startIdx) throws OrcaException {
        try {
            this.description = userInput.substring(startIdx);
            this.type = "T";
        } catch (StringIndexOutOfBoundsException e) {
            throw new OrcaException("The description of a todo cannot be empty.");
        }
    }
}
