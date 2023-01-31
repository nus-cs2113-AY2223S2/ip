public class Deadline extends Task{
    private String dueDate;
    public Deadline(String inLine){
        super(inLine);
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
