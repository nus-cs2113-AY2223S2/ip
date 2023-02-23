package Tasks;

public class ToDo extends Task{
    protected String type = "T";

    public String getType() {
        return type;
    }

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return '[' + type + "]" + super.toString();
    }

}
