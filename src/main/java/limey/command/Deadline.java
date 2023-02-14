package limey.command;
import limey.exception.invalidDateException;
public class Deadline extends Task{
    private String dueDate;
    public Deadline(String inLine) throws invalidDateException{
        super(inLine);
        if(!inLine.contains("/by")) {
            throw new invalidDateException();
        }
        int indexOfBy = inLine.indexOf("/by");
        dueDate = inLine.substring(indexOfBy + 3);
        setTaskName(super.getTaskName() + "(by: " + getDueDate() +")");
    }
    @Override
    public String getTaskIdentity() {
        String todoSymbol = "[D]";
        return todoSymbol + super.getTaskIdentity();
    }
    public String getDueDate(){
        return dueDate.trim();
    }
}
