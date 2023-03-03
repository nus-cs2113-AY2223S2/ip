package Duke.Tasks;

public class Task {

    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";
    protected String taskName;
    protected boolean isDone;

    /**
     * Instantiation of task
     * @param taskName Name of task
     */
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

    /***
     * Outputs task type
     * @return String representing task type. Method is overridden in children classes.
     */
    public String getTaskType() {
        return "error with print task type override";
    }

    ;
    /**
     * Prints information of task with format. Overridden by deadline and event.
     */
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
    /***
     * Prints message with information of task being added to taskList.
     * @param taskNumber int representing index of task in taskList
     */
    public void printAddTask(int taskNumber) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Added to list:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + "Number of tasks: " + (taskNumber + 1));
        System.out.println(BLANK + LINE);
    }

    /***
     * Prints message with information of task being deleted from taskList.
     * @param numberOfTask int representing number of task in list
     */
    public void printDeleteTask(int numberOfTask) {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Deleting from list:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + "Number of tasks: " + (numberOfTask - 1));
        System.out.println(BLANK + LINE);
    }

    /**
     * Prints message of task being marked.
     */
    public void printMarkedTask() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Nice! I've marked this task as done:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + LINE);
    }

    /**
     * Prints message of task being unmarked.
     */
    public void printUnmarkedTask() {
        System.out.println(BLANK + LINE);
        System.out.print(BLANK);
        System.out.println(BLANK + "OK, I've marked this task as not done yet:");
        System.out.print(BLANK);
        printTask();
        System.out.println(BLANK + LINE);
    }

    /***
     * Outputs a formatted String containing information of the task saved in a text file.
     * Overridden by respective children classes.
     * @return String containing information of the task.
     */
    public String saveInfo() {
        return "error with save info override" + "\n";
    }
}
