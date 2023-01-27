public class Todo extends Task {
    public Todo(String userInput, int startIdx) {
        this.description = userInput.substring(startIdx);
        this.type = "T";
    }
}
