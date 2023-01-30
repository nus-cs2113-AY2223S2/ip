public class Todo extends Task {
    public Todo(String Description){
        super(Description);
        super.printMessage();
    }
    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }
}
