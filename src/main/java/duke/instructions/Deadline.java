package duke;

public class Deadline extends Task{
    String input;
    public Deadline(String input) {
        super(input);
        this.input = input;
    }

    @Override
    public String getState(){
        String deadline = input.substring(input.indexOf('/') + 1);
        StringBuffer showDeadline = new StringBuffer(deadline);
        showDeadline.insert(2, ":");
        String arrayOfDeadline = this.description.substring(description.indexOf(" ") + 1);
        String[] eventName = arrayOfDeadline.split("/");
        return "      [D]" + "[" + getStatusIcon() + "]" +  " "
                + eventName[0] + " (" + showDeadline + ")"
                + System.lineSeparator();
    }

    @Override
    public String guideline(){
        return "     Got it. I've added this task: "
                + System.lineSeparator();
    }
}
