public class Todo extends Task{
    public Todo(String taskName){
        super(taskName);
    }
    @Override
    public String getTaskIdentity() {
        String todoSymbol = "[T]";
        return todoSymbol + super.getTaskIdentity();
    }
}
