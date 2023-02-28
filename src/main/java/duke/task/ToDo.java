package duke.task;

public class ToDo extends Task{
    /**
     * Constructor method for the ToDo object.
     * @param description This is the description of the ToDo taskk.
     */
    public ToDo(String description){
        super(description, "ToDo");
//        this.type = "ToDo";
    }

    /**
     * Returns the details of the ToDo task in a specific format.
     * @return String This returns the details of the ToDo Task.
     */
    public String toString(){
        return "[T]" + super.toString();
    }
}
