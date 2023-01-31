public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }
    public String toString() {
        String checkbox = "[ ]";
        String typeIndicator = null;
        if(status){
            checkbox = "[X]";
        }
        return "[T]" + checkbox + " " + name;
    }
}
