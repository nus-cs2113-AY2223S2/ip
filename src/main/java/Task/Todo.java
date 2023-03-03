package Task;

public class Todo extends Task{
    /**
     * constructor
     *
     * @param description description of the task
     * 
     */
    public Todo (String description){
        super(description);
    }

    /**
     * constructor
     *
     * @param description description of the task\
     * @param isDone true if the task is done, false otherwise
     * 
     */
    public Todo (String description, boolean isDone){
        super(description, isDone);
    }

    /**
     * toString method
     *
     * @return string description
     * 
     */
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }

    /**
     * toString method when saving in file
     *
     * @return string description to be saved in file
     * 
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }

    /**
     * toString method
     *
     * @param line input string from file
     * @return deadline item
     * 
     */
    public static Todo fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        return new Todo(description, isDone);
    }
}
