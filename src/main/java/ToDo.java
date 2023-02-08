public class ToDo extends Task {


    public ToDo(String description) throws ArgumentBlankException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}