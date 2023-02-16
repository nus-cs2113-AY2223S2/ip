package duke.instructions;

public class Delete extends Task{

    public Delete(String description) {
        super(description);
    }

    public void deleteTask(){

    }

    @Override
    public String guideline(){

        return "     Noted. I've removed this task:"
                + System.lineSeparator();
    }
}
