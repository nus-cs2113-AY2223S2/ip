public class Todo extends Task {
    Todo(String userInput) {
        super(userInput);
    }

    @Override
    public String toString() {
        if (getIsDone()) {
            return "[T][O] " + getContents();
        }
        return "[T][ ] " + getContents();
    }

    @Override
    public String showTask(){
        if (getIsDone()) {
            return "[T][O] " + getContents();
        }
        return "[T][ ] " + getContents();
    }
}
