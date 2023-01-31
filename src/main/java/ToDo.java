//Overridden the toString method of Object class. Do regression testing before continuing with week 4 requirements

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
