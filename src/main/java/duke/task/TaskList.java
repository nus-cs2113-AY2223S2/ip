package duke.task;

import duke.ui.ExceptionsUi;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    /* list of tasks */
    private ArrayList<Task> tasks;

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /* add new Todo task to task list*/
    public void addTodo(String taskInfo) {
        Task newTask = new Todo(taskInfo);
        tasks.add(newTask);
        printAddTaskResult(newTask);
    }

    /* create a new Deadline task from the input received */
    public Deadline createDeadline(String taskInfo) {
        String description, deadline;
        String[] info = taskInfo.split("#by ", 2);
        description = info[0];
        deadline = info[1];
        return new Deadline(description, deadline);
    }

    /* add a new Deadline to task list */
    public void addDeadline(String taskInfo) {
        Task newTask = createDeadline(taskInfo);
        tasks.add(newTask);
        printAddTaskResult(newTask);
    }

    /* create a new Event task from the input received */
    public Event createEvent(String taskInfo) {
        String description, from, to;
        String[] info = taskInfo.split("#from ", 2);
        String[] timeInfo = info[1].split("#to ", 2);
        description = info[0];
        from = timeInfo[0];
        to = timeInfo[1];
        return new Event(description, from, to);
    }

    /* add a new Event to task list */
    public void addEvent(String taskInfo) {
        Task newTask = createEvent(taskInfo);
        tasks.add(newTask);
        printAddTaskResult(newTask);
    }

    /* print what task was recently added */
    public void printAddTaskResult(Task addedTask) {
        System.out.print(Ui.BREAK_LINE
                + "\tadded:\n\t\t" + addedTask + '\n'
                + "\t(total: " + tasks.size() + " tasks)\n"
                + Ui.BREAK_LINE);
    }

    /* list all the task in the list */
    public void listTask() {
        System.out.println(Ui.BREAK_LINE + "\tThese are the tasks you have (" + tasks.size() + " tasks):");
        int order = 1;
        for (Task task : tasks) {
            System.out.print("\t" + order + ". " + task.toString() + '\n');
            order++;
        }
        System.out.print(Ui.BREAK_LINE);
    }

    /* mark a specific task by the task number */
    public void markTask(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIndex);
            currentTask.mark();
            System.out.print(Ui.BREAK_LINE
                    + "\tNice! I've marked this task as done :D\n\t\t"
                    + currentTask + '\n'
                    + Ui.BREAK_LINE);
        } catch (IndexOutOfBoundsException e) {
            ExceptionsUi.printInvalidTaskNumberError();
        }
    }

    /* unmark a specific task by the task number */
    public void unmarkTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            currentTask.unmark();
            System.out.print(Ui.BREAK_LINE
                    + "\tOh. I've unmarked this task as not done yet :(\n\t"
                    + currentTask + '\n'
                    + Ui.BREAK_LINE);
        } catch (IndexOutOfBoundsException e) {
            ExceptionsUi.printInvalidTaskNumberError();
        }
    }

    /* delete a specific task by the task number */
    public void deleteTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            System.out.println(Ui.BREAK_LINE
                    + "\tOk! I've deleted the task :D\n\t\t"
                    + currentTask);
            System.out.print("\tThere are " + tasks.size() + " remaining tasks.\n"
                    + Ui.BREAK_LINE);
        } catch (IndexOutOfBoundsException e) {
            ExceptionsUi.printInvalidTaskNumberError();
        }
    }

    /* find tasks that contain a specific keyword */
    public void findTask(String keyword) {
        int order = 1;
        try {
            System.out.println(Ui.BREAK_LINE + "\tThese are what I found :)");
            for (Task task : tasks) {
                if (task.description.contains(keyword)) {
                    System.out.print("\t" + order + ". " + task.toString() + '\n');
                    order++;
                }
            }
            System.out.print(Ui.BREAK_LINE);
        }
        catch (NullPointerException e) {
            ExceptionsUi.printFindKeywordMissing();
        }

    }
}
