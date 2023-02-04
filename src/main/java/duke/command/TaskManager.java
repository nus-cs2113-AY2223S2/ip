package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.Event;
import duke.exceptions.ListTooLarge;

public class TaskManager {
    private Task[] taskList;
    private int listSize;
    private static final int MAX_LIST_SIZE = 1;

    public TaskManager() {
        this.listSize = 0;
        this.taskList = new Task[MAX_LIST_SIZE];
    }

    /**
     * Adds task to the list to keep track
     *
     * @param description: user task to remember
     */
    public void addToList(String description) throws ListTooLarge {
        if (this.listSize >= MAX_LIST_SIZE) {
            throw new ListTooLarge();
        }
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
    public void addToList(String description, String dueBy) throws ListTooLarge {
        if (this.listSize >= MAX_LIST_SIZE) {
            throw new ListTooLarge();
        }
        Deadline deadline = new Deadline(description, dueBy);
        taskList[this.listSize] = deadline;
        this.listSize += 1;
        String deadlineDuration = deadline.getDueDate();
        System.out.println("Got it! Added \n"
                + "[D][ ] " + description + deadlineDuration + "\n"
                + "to the list.");
        System.out.println("Now you have " + this.listSize + " task(s) in the list.");
    }

    /**
     * Overloaded: Adds Event to the list to keep track
     *
     * @param description: user event to remember
     */
    public void addToList(String description, String startDate, String endDate) throws ListTooLarge {
        if (this.listSize >= MAX_LIST_SIZE) {
            throw new ListTooLarge();
        }
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
        String taskType = taskList[taskIndex].getTaskType();
        String task = taskList[taskIndex].setAsDone();
        System.out.println("Noted sir, I have marked \n"
                + taskType + "[X] " + task + "\n"
                + "as done.");

    }

    /**
     * Set the specified task at the given index to undone
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public void markAsUndone(int taskIndex) {
        String taskType = taskList[taskIndex].getTaskType();
        String task = taskList[taskIndex].setAsUndone();
        System.out.println("Noted sir, I have marked \n"
                + taskType + "[ ]" + task + "\n"
                + "as not done.");

    }

    /**
     * prints all tasks stored in the list
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        if (this.listSize == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        for (int i = 1; i < this.listSize + 1; i++) {
            System.out.println(i + ". " + taskList[i - 1].getTaskStatus());
        }
        System.out.println("Now you have " + this.listSize + " tasks in the list.");
    }
}
