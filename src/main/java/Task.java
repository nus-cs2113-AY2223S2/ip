import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskLabel;

    static final int TASK_NUMBER_OFFSET = 1;

    public static void printHorizontalLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark done task with X
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
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
     * executeNonAdd method executes all instructions
     * except the instructions to add tasks to the list
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

    protected static void unmark(String command, ArrayList<Task>  listOfTasks) {
        String[] seperated = command.split(" ");
        int number = Integer.parseInt(seperated[1]) - TASK_NUMBER_OFFSET;
        listOfTasks.get(number).setDone(false);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("     " + listOfTasks.get(number).taskLabel + listOfTasks.get(number).getStatusIcon() + " " + listOfTasks.get(number).description);
    }

    protected static void mark(String command, ArrayList<Task>  listOfTasks) {
        String[] seperated = command.split(" ");
        int number = Integer.parseInt(seperated[1]) - TASK_NUMBER_OFFSET;
        listOfTasks.get(number).setDone(true);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("     " + listOfTasks.get(number).taskLabel + listOfTasks.get(number).getStatusIcon() + " " + listOfTasks.get(number).description);
    }


}