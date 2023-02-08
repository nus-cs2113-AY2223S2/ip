public class Deadline extends Task {
    protected String deadlineBy;

    public static String[] parseCommand (String command) throws InvalidCommandException {
        String[] descriptionArray = new String[2];
        String[] descriptionAndDeadline = command.split("deadline")[1].split("/");
        if (descriptionAndDeadline.length < 2){
            throw new InvalidCommandException("Incomplete deadline description!");
        }
        for (int i = 0; i < 2; i++) {
            descriptionArray[i] = descriptionAndDeadline[i].trim();
        }
        return descriptionArray;
    }
    public Deadline(String[] descriptionArray) {
        super(descriptionArray);
        this.deadlineBy = descriptionArray[1];
    }

    @Override
    public String toString() {
        String deadlinePrefix = "[D]";
        String taskString = super.toString();
        String deadlinePostfix = " (" + "by: " + this.deadlineBy + ")";
        return deadlinePrefix + taskString + deadlinePostfix;
    }
}
