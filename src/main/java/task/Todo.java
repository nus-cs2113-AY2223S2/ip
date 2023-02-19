package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description, 'T');
    }

    /**
     * This method extracts the relevant information (description) from the userInput.
     * It returns String containing the description of the Task.
     *
     * @param userInput The input entered by the user.
     * @return A String containing the description of the Task.
     */
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
