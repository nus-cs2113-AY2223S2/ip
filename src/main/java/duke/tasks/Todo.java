package duke.tasks;

public class Todo extends Task {
    public Todo(String name, Boolean status) {

        super(name, status);
    }

    @Override
    public String toString() {
        String checkbox = "[ ]";
        String typeIndicator = null;
        if(status){
            checkbox = "[X]";
        }
        return "[T]" + checkbox + " " + name;
    }

    @Override
    public String toTextFileFormat(){
        return "todo/" + name + "/" + status;
    }
}
