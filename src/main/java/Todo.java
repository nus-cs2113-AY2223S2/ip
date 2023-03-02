public class Todo extends Tasks{
    public Todo(String TaskName){
        super(TaskName);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}