package duke.task;

/**
 * The main Task class that the other subclasses todo, deadline and event inherit from
 */
public abstract class Task {
    /**
     * describes the event that the user is referring to
     */
    protected String description;
    /**
     * Boolean value on whether the task is done or not
     */
    protected boolean isDone;
    /**
     * The task in the format of a String saved in the database
     */
    protected String taskString;
    /**
     * The separation to split the details inside each taskString
     */
    public static final String COMMA_TASK_SEPARATOR = " , ";

    /**
     * Constructor of the Task object that creates a new Task which initialises its description, makes it unmarked at
     * the start and creates its dedicated taskString in the correct format
     *
     * @param description describes what the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskString = saveTaskString();
    }

    /**
     * Returns the specific Object Task in readable format of a string to the user which contains information
     * such as the description and whether it's marked or not
     *
     * @return the String in a readable format referring to the Task
     */
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    /**
     * Updates the Task Object's isDone depending on whether the user input of mark or unmark
     * When mark is the String of userCommand, make isDone true
     * When unmark is the String of userCommand, make isDone false
     * Updating to the database is then done
     *
     * @param userCommand the first word of the user input corresponding to the type of command
     */
    public void setDone(String userCommand) {
        if (userCommand.equals("mark")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
        this.taskString = saveTaskString();
    }

    /**
     * Returns the string in the format to be saved in database
     *
     * @return the string in the correct format to be saved
     */
    public abstract String saveTaskString();

    /**
     * Returns the String to be used by Ui class
     *
     * @return the String that would be shown to the user after adding a new task
     */
    public String addTaskMessage() {
        return "Got it. I've added this task:\n  " + this.toString() + System.lineSeparator();
    }

    /**
     * Return the String to be used by Ui class
     *
     * @return the String that would be shown to the user after deleting a task
     */
    public String deleteTaskMessage() {
        return "Noted. I've removed this task:\n  " + this.toString() + System.lineSeparator();
    }

    /**
     * Returns the taskString of the current Task object
     *
     * @return the String taskString which is a string formatted for saving in the database
     */
    public String getTaskString() {
        return taskString;
    }

    /**
     * Returns the description of the task
     *
     * @return the description String of the task
     */
    public String getDescription() {
        return description;
    }

}
