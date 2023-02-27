package duke.task;

/**
 * Todo class that keep tracks of a task description
 */
public class Todo extends Task {
    /**
     * Todo class constructor that creates a new todo object and initialises its description
     *
     * @param description describes the todo task that the user is referring to
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the specific Object Task in readable format of a string to the user which contains information
     * such as the description and whether it's marked or not
     *
     * @return the String in a readable format referring to the Todo Task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string in the format to be saved in database as a todo Task
     *
     * @return the string in the correct format to be saved
     */
    @Override
    public String saveTaskString() {
        String saveString = new String();
        saveString += "T" + COMMA_TASK_SEPARATOR + isDone + COMMA_TASK_SEPARATOR + description;
        return saveString;
    }
}
