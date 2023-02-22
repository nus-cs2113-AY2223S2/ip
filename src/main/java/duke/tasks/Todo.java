package duke.tasks;

/**
 * A Todo object contains todo tasks with their task descriptions.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Overwriting toString method to return the todo task with '[T]' prefix.
     *
     * @return
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Cover the todo task data into appropriate format to be stored in data file.
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
        String data = "T|"+status+"|"+getTaskDescription()+"\n";
        return data;
    }
}
