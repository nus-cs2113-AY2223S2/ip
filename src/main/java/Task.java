import java.util.ArrayList;

/**
 * An abstract class that represents a task in general.
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskLabel;

    protected String doneStatusLabel = "[X]";
    protected String notDoneStatusLabel = "[ ]";

    static final int TASK_NUMBER_OFFSET = 1;

    public static void printHorizontalLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
    }


    public String getStatusIcon() {
        return (isDone ? doneStatusLabel : notDoneStatusLabel);
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {

        isDone = done;
    }

    /**
     * Check if the command is to mark or unmark an entry to then run appropriate method.
     *
     * @param command       instruction that could be "mark" or "unmark
     * @param listOfTasks   listOfTasks ArrayList of tasks.
     * @param currentNumber Number of tasks present in the list.
     */
    public static void markOrUnmark(String command, ArrayList<Task> listOfTasks, int currentNumber) {
        if (command.matches("mark \\d")) {
            mark(command, listOfTasks);
        } else if (command.matches("unmark \\d")) {
            unmark(command, listOfTasks);
        }
    }

    protected static int add(String line, ArrayList<Task> list, int currentNumber) {
        return currentNumber;
    }

    protected static void unmark(String command, ArrayList<Task> listOfTasks) {
        String[] seperated = command.split(" ");
        int number = Integer.parseInt(seperated[1]) - TASK_NUMBER_OFFSET;
        listOfTasks.get(number).setDone(false);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("     " + listOfTasks.get(number).taskLabel +
                listOfTasks.get(number).getStatusIcon() + " " + listOfTasks.get(number).description);
    }

    protected static void mark(String command, ArrayList<Task> listOfTasks) {
        String[] seperated = command.split(" ");
        int number = Integer.parseInt(seperated[1]) - TASK_NUMBER_OFFSET;
        listOfTasks.get(number).setDone(true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + listOfTasks.get(number).taskLabel +
                listOfTasks.get(number).getStatusIcon() + " " + listOfTasks.get(number).description);
    }


}