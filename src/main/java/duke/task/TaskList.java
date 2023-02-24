package duke.task;

import duke.ui.ExceptionsUi;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private ExceptionsUi exceptionsUi = new ExceptionsUi();
    private Ui ui = new Ui();

    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    // add a new task
    public void addTodo(String taskInfo) {
        Task newTask = new Todo(taskInfo);
        tasks.add(newTask);
        printAddTaskResult(newTask);
    }

    // create deadline
    public Deadline createDeadline(String taskInfo) {
        String description, deadline;
        String[] info = taskInfo.split("#by ", 2);
        description = info[0];
        deadline = info[1];
        return new Deadline(description, deadline);
    }

    // add a new deadline
    public void addDeadline(String taskInfo) {
        Task newTask = createDeadline(taskInfo);
        tasks.add(newTask);
        printAddTaskResult(newTask);
    }

    // create new event
    public Event createEvent(String taskInfo) {
        String description, from, to;
        String[] info = taskInfo.split("#from ", 2);
        String[] timeInfo = info[1].split("#to ", 2);
        description = info[0];
        from = timeInfo[0];
        to = timeInfo[1];
        return new Event(description, from, to);
    }

    // add a new event
    public void addEvent(String taskInfo) {
        Task newTask = createEvent(taskInfo);
        tasks.add(newTask);
        printAddTaskResult(newTask);
    }

    // print result of add task
    public void printAddTaskResult(Task addedTask) {
        System.out.print(ui.BREAK_LINE
                + "\tadded:\n\t\t" + addedTask + '\n'
                + "\t(total: " + tasks.size() + " tasks)\n"
                + ui.BREAK_LINE);
    }

    // list all tasks
    public void listTask() {
        System.out.println(Ui.BREAK_LINE + "\tThese are the tasks you have (" + tasks.size() + " tasks):");
        int order = 1;
        for (Task task : tasks) {
            System.out.print("\t" + order + ". " + task.toString() + '\n');
            order++;
        }
        System.out.print(ui.BREAK_LINE);
    }

    // mark the task
    public void markTask(String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIndex);
            currentTask.mark();
            System.out.print(ui.BREAK_LINE
                    + "\tNice! I've marked this task as done :D\n\t\t"
                    + currentTask + '\n'
                    + ui.BREAK_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(ui.BREAK_LINE
                    + "\t(!) Invalid task number. Please try again :(\n"
                    + ui.BREAK_LINE);
        }
    }

    // unmark the task
    public void unmarkTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            currentTask.unmark();
            System.out.print(ui.BREAK_LINE
                    + "\tOh. I've unmarked this task as not done yet :(\n\t"
                    + currentTask + '\n'
                    + ui.BREAK_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(Ui.BREAK_LINE
                    + "\t(!) Invalid task number. Please try again :(\n"
                    + ui.BREAK_LINE);
        }
    }

    // delete Task
    public void deleteTask(String taskNumber) {
        int taskIdx = Integer.parseInt(taskNumber) - 1;
        try {
            Task currentTask = tasks.get(taskIdx);
            tasks.remove(taskIdx);
            System.out.println(ui.BREAK_LINE
                    + "\tOk! I've deleted the task :D\n\t\t"
                    + currentTask);
            System.out.print("\tThere are " + tasks.size() + " remaining tasks.\n"
                    + ui.BREAK_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(ui.BREAK_LINE
                    + "\t(!) Invalid task number. Please try again :(\n"
                    + ui.BREAK_LINE);
        }
    }

    public void findTask(String keyword) {
        int order = 1;
        try {
            System.out.println(ui.BREAK_LINE + "\tThese are what I found :)");
            for (Task task : tasks) {
                if (task.description.contains(keyword)) {
                    System.out.print("\t" + order + ". " + task.toString() + '\n');
                    order++;
                }
            }
            System.out.print(ui.BREAK_LINE);
        }
        catch (NullPointerException e) {
            exceptionsUi.printFindKeywordMissing();
        }

    }
}
