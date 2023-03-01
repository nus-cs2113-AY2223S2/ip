package Tasklist;

public class Deadline extends Tasklist {
    public String dueDate = new String();

    public Deadline(String taskName, int taskNumber, String dueDate, String type) {
        super(taskName, taskNumber);
        this.dueDate = dueDate;
        description = "  [D]" + this.getDoneString() + " " + this.taskName + " (by: " + this.dueDate + ")";
        this.type = type;
    }

    /**
     * @param input unchanged string from scanner
     * @return substring after "deadline" and before "/by'
     */
    public static String readName(String input) {
        String[] splitByForwardSlash = input.split("/");
        return (splitByForwardSlash[0].substring(9)).trim();
    }

    /**
     * @param input unchanged string from scanner
     * @return substring after "/by"
     * example of splitByForwardSlash is ["deadline return book","by Sunday"]
     */
    public static String readBy(String input) {
        String[] splitByForwardSlash = input.split("/");
        return (splitByForwardSlash[1]).substring(3);
    }

    @Override
    public void updateTaskDescription() {
        this.description = "  [D]" + this.getDoneString() + " " + this.taskName + " (by: " + this.dueDate + ")";
    }

    public String createEntry() {
        return Integer.toString(this.taskNumber) + "." + this.type + "." + this.getDoneString() + "." + this.taskName +
                "." + this.dueDate + "\n";
    }
}