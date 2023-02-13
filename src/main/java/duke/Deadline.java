package duke;

public class Deadline extends Task {
    protected String cutoffDate;

    public Deadline(String userInput) {
        super();
        String[] userInputArray;
        userInputArray = userInput.split("/by", 2);
        String deadlineDescription = userInputArray[0].split("/deadline", 2)[1].trim();
        String deadlineCutoff = userInputArray[1].trim();
        this.cutoffDate = deadlineCutoff;
        super.description = deadlineDescription;
    }

    @Override
    public String formattedString() {
        String formatted = "Deadline:" + super.isDone + ":" + super.description + ":" + cutoffDate;
        return formatted;
    }

    @Override
    public String toString() {
        return "[DEADLINE]\n" + super.toString() + " (By: " + cutoffDate + ")";
    }

}
