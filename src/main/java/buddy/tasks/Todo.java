package buddy.tasks;

public class Todo extends Task {

    /**
     * Constructor for Todo - a type of Task
     *
     * @param description Description of todo
     */
    public Todo(String description) {
        super(description);
    }


    @Override
    public String getType(){
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

