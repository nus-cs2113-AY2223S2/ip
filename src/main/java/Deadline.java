public class Deadline extends Task {

    protected String whenDue;

    public Deadline(String description){
        super(description);
        String[] splitLine = description.split("/",2);
        String[] getDescription = splitLine[0].split(" ",2);
        String[] splitDueDatePreposition = splitLine[1].split(" ",2);
        this.description = getDescription[1];
        this.whenDue = "(" + splitDueDatePreposition[0] + ": " + splitDueDatePreposition[1] + ")";
        this.isDone = false;
        this.label = "[D]";
    }

}
