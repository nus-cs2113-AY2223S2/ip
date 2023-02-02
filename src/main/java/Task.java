public class Task {
    //Instantiate

    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";
    private final String taskName;
    protected boolean isDone;
    protected int taskNumber;

    public Task(String taskName, int taskNumber) {
        this.taskName = taskName;
        this.isDone = false;
        this.taskNumber = taskNumber;
    }

    public boolean getIsDone() {
        return isDone;
    }
    public void setDone() {
        isDone = true;
    }
    public void setNotDone() {
        isDone = false;
    }
    public String getTaskName() {
        return taskName;
    }

    public void printTaskType() {
        System.out.print("T");
    }
    public void printTask() {
        System.out.print("[");
        printTaskType();
        System.out.print("][");
        if (this.isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.getTaskName());
    }
    public void printAddTask() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Added to list:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + "Number of tasks: " + taskNumber);
        System.out.println(BLANK + LINE);
    }
    public void printMarkedTask() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Nice! I've marked this task as done:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + LINE);
    }
    public void printUnmarkedTask() {
        System.out.println(BLANK + LINE);
        System.out.print(BLANK);
        System.out.println(BLANK + "OK, I've marked this task as not done yet:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + LINE);
    }
}
