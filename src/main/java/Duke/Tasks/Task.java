package Duke;

public class Task {
    //Instantiate

    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
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

    public String getTaskType() {
        return "error with print task type override";
    };

    public void printTask() {
        System.out.print("[");
        System.out.print(getTaskType());
        System.out.print("][");
        if (this.isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.getTaskName());
    }

    public void printAddTask(int taskNumber) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Added to list:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + "Number of tasks: " + (taskNumber + 1));
        System.out.println(BLANK + LINE);
    }

    public void printDeleteTask(int numberOfTask) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Deleting from list:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + "Number of tasks: " + (numberOfTask - 1));
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

    public String saveInfo () {
        return "error with save info override" + "\n";
    }
}
