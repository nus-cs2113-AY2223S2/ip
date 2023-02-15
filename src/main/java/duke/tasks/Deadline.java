package duke.tasks;

public class Deadline extends Task {

    public String deadline;
    public Deadline(String name, String deadline, Boolean status) {
        super(name, status);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        String checkbox = "[ ]";
        String typeIndicator = null;
        if(status){
            checkbox = "[X]";
        }
        return "[D]" + checkbox + " " + name + " (by:" + deadline + ")";
    }

    @Override
    public String toTextFileFormat(){
        return "deadline/" + name + "/" + status + "/" + deadline;
    }
}
