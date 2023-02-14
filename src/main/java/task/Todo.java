package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }

    // Returns a String containing taskName
    public static String handler(String userInput) {

        // Format of userInput: <command> <task_name>
        String taskName = userInput.replaceFirst("todo ", "");
        return taskName;

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
