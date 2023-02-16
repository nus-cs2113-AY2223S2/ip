package duke.task;

public class ToDo extends Task{
    public ToDo(String description){
        super(description, "ToDo");
//        this.type = "ToDo";
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
