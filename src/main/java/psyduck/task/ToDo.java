package psyduck.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }


    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
