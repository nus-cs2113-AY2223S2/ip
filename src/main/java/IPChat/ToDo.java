package IPChat;

/**
 * Class which is used for creating todo tasks
 *
 * @author DeepanjaliDhawan
 */
public class ToDo extends Task{

    /**
     * Constructor to instantiate the todo objects
     * @param description Description of the todo tasks
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method to return the string in the ArrayList
     * @return String in the ArrayList
     */
    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.toString();
    }

    /**
     * Method to save the todo tasks in the file
     * @return descriptions of the todo tasks
     */
    @Override
    public String saveStuff () {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "todo" + description + " | " + save;
    }
}