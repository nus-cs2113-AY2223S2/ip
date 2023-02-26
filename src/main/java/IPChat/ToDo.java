package IPChat;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.toString();
    }

    @Override
    public String saveStuff () {
        int save = 0;
        if (this.isDone) {
            save = 1;
        }
        return "todo" + description + " | " + save;
    }
}