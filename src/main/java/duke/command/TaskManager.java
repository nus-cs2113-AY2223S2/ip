package duke.command;

import duke.filemanager.TaskLoader;
import duke.filemanager.TaskWriter;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Event;
import duke.exceptions.ListTooLarge;

import java.util.ArrayList;


public class TaskManager {
    private ArrayList<Task> taskList;
    TaskWriter taskWriter = new TaskWriter();

    public TaskManager() {
        this.taskList = new ArrayList<Task>();
    }

    public void setData() {
        TaskLoader taskLoader = new TaskLoader();
        this.taskList = taskLoader.setClasses();
    }

    /**
     * Adds task to the list to keep track
     *
     * @param description: user task to remember
     */
    public void addToList(String description) throws ListTooLarge {
        Task task = new Task(description);
        taskList.add(task);
        taskWriter.writeToJson(taskList);
        System.out.println("Got it! Added \n"
                + "[T][ ]" + description + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * Overloaded: Adds Deadline to the list to keep track
     *
     * @param description: user Deadline to remember
     */
    public void addToList(String description, String dueBy) throws ListTooLarge {

        Deadline deadline = new Deadline(description, dueBy);
        taskList.add(deadline);
        taskWriter.writeToJson(taskList);
        String deadlineDuration = deadline.getDueDate();
        System.out.println("Got it! Added \n"
                + "[D][ ] " + description + deadlineDuration + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * Overloaded: Adds Event to the list to keep track
     *
     * @param description: user event to remember
     */
    public void addToList(String description, String startDate, String endDate) throws ListTooLarge {
        Event event = new Event(description, startDate, endDate);
        taskList.add(event);
        taskWriter.writeToJson(taskList);
        String eventDuration = event.getDuration();
        System.out.println("Got it! Added \n"
                + "[E][ ] " + description + eventDuration + "\n"
                + "to the list.");
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }

    /**
     * Set the specified task at the given index to done
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public void markAsDone(int taskIndex) {
        String taskType = taskList.get(taskIndex).getTaskType();
        String task = taskList.get(taskIndex).setAsDone();
        taskWriter.writeToJson(taskList);
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
        String taskType = taskList.get(taskIndex).getTaskType();
        String task = taskList.get(taskIndex).setAsUndone();
        taskWriter.writeToJson(taskList);
        System.out.println("Noted sir, I have marked \n"
                + taskType + "[ ]" + task + "\n"
                + "as not done.");

    }

    /**
     * prints all tasks stored in the list
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list!");
            return;
        }
        int count = 1;
        for (Task i : taskList) {
            System.out.println(count + ". " + i.getTaskStatus());
            count++;
        }
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Deletes the specified index
     */
    public void deleteTask(int taskIndex) {
        String taskType = taskList.get(taskIndex).getTaskType();
        String task = taskList.get(taskIndex).getTaskStatus();
        taskList.remove(taskIndex);
        taskWriter.writeToJson(taskList);
        System.out.println("Noted sir, I have removed \n"
                + task);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }
}
