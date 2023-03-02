package duke.data;

/**
 * the todo task with description
 */
public class Todo extends Task{
    public Todo(String description){
        super(description);
    }

    public String toString(){
        return "[T]" + super.toString() ;
    }
}
