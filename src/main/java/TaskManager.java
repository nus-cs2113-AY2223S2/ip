public class TaskManager {
    private Task[] taskList;
    private int listSize;
    private static final int MAXLISTSIZE = 100;

    public TaskManager() {
        this.listSize = 0;
        this.taskList = new Task[MAXLISTSIZE];
    }

    /**
     * Adds task to the list to keep track
     *
     * @param description: user task to remember
     */
    public void addToList(String description) {
        Task task = new Task(description);
        taskList[this.listSize] = task;
        this.listSize += 1;
        System.out.println("Got it! Added \n"
                + "[T][ ]" + description + "\n"
                + "to the list.");
        System.out.println("Now you have " + this.listSize + " task(s) in the list.");
    }

    /**
     * Overloaded: Adds Deadline to the list to keep track
     *
     * @param description: user Deadline to remember
     */
    public void addToList(String description, String dueBy) {
        Deadline event = new Deadline(description, dueBy);
        taskList[this.listSize] = event;
        this.listSize += 1;
        String eventDuration = event.getdueDate();
        System.out.println("Got it! Added \n"
                + "[D][ ] " + description + eventDuration + "\n"
                + "to the list.");
        System.out.println("Now you have " + this.listSize + " task(s) in the list.");
    }

    /**
     * Overloaded: Adds Event to the list to keep track
     *
     * @param description: user event to remember
     */
    public void addToList(String description, String startDate, String endDate) {
        Event event = new Event(description, startDate, endDate);
        taskList[this.listSize] = event;
        this.listSize += 1;
        String eventDuration = event.getDuration();
        System.out.println("Got it! Added \n"
                + "[E][ ] " + description + eventDuration + "\n"
                + "to the list.");
        System.out.println("Now you have " + this.listSize + " task(s) in the list.");
    }

    /**
     * Set the specified task at the given index to done
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public void markAsDone(int taskIndex) {
        if (taskIndex >= listSize || taskIndex < 0) {
            System.out.println("Task not found within the list!");
        } else {
            String taskType = taskList[taskIndex].getTaskType();
            String task = taskList[taskIndex].setAsDone();
            System.out.println("Noted sir, I have marked \n"
                    + taskType + "[X]" + task + "\n"
                    + "as done.");
        }
    }

    /**
     * Set the specified task at the given index to undone
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public void markAsUndone(int taskIndex) {
        if (taskIndex >= listSize || taskIndex < 0) {
            System.out.println("Task not found within the list!");
        } else {
            String taskType = taskList[taskIndex].getTaskType();
            String task = taskList[taskIndex].setAsUndone();
            System.out.println("Noted sir, I have marked \n"
                    + taskType + "[ ]" + task + "\n"
                    + "as not done.");
        }
    }

    /**
     * prints all tasks stored in the list
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < this.listSize + 1; i++) {
            System.out.println(i + ". " + taskList[i - 1].getTaskStatus());
        }
        System.out.println("Now you have " + this.listSize + " tasks in the list.");
    }
}
