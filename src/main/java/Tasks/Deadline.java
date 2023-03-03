package Tasks;

public class Deadline extends Task {

    protected String by;

    /**
     * This is the constructor of an object of class Deadline. It also
     * calls the default constructor of the class it has inherited (Task).
     *
     * @param taskName: name of the task
     * @param by: description of the task
     */
    public Deadline(String taskName, String by) {
        super(taskName+" (by:" + by + ")", false, "[D]");
        this.by = by;
    }

    public String toString(){
        return super.toString();
    }

}