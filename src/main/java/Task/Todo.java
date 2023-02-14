package Task;
public class Todo extends Task {
    public Todo(String Description){
        super(Description);
        super.printMessage();
    }
    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
    @Override
    public String getTaskType() {
        return "T";
    }
}
