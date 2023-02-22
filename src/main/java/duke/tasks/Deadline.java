package duke.tasks;

/**
 * A deadline object contains deadline tasks with their task descriptions and due date.
 */
public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * Overwriting toString method to return the event task with '[D]' prefix and append it with its due date.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Cover the deadline task data into appropriate format to be stored in data file.
     *
     * @return
     */
    public String convertToData(){
        String status;
        if(getTaskStatus().equals("X")){
            status = "1";
        }else{
            status = "0";
        }
        String data = "D|"+status+"|"+getTaskDescription()+"|"+getBy()+"\n";
        return data;
    }
}
