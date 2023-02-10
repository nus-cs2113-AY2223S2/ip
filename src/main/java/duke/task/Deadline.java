package duke.task;

public class Deadline extends Task{
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    private String endDate;
    public Deadline(String command){
        super();
        setInitCommand(command);
        setDescription(command.substring(command.indexOf(" "),command.indexOf("/by")).trim());
        setEndDate(command.substring(command.indexOf("/by")+3).trim());
    }
    public Deadline(String description, String endDate){
        super(description);
        setEndDate(endDate);
    }
    @Override
    public String toString(){
        return "[D]["+(isDone()?'X':' ')+"]//////"+super.toString()+" (by: "+getEndDate()+')';
    }
}
