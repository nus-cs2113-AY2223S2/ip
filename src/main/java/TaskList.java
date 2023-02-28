import java.util.ArrayList;

/**
 * Represents the list of task.
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected boolean isSilent = false;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists all tasks.
     */
    public void listTask(Ui ui) {
        ui.printSmallSpace();
        ui.println("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i = i + 1) {
            int num = i + 1;
            ui.printSmallSpace();
            ui.println(num
                    + ".[" + tasks.get(i).getTypeOfTask()
                    + "][" + tasks.get(i).getStatusIcon()
                    + "] " + tasks.get(i).getDescription());
        }
    }

    /**
     * Marks specified task as done if taskNumber parameter is an integer.
     *
     * @param taskNumber The 1th-index of task to be marked.
     * @throws DukeException If taskNumber is out of bounds.
     */
    public void markTask(int taskNumber, Ui ui) throws DukeException {

        int ind = taskNumber - 1;

        if(ind >= tasks.size() || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks.get(ind).mark();

        if(isSilent) return;

        ui.printSmallSpace();
        ui.println("Nice! I've marked this task as done:\n");
        ui.printBigSpace();
        ui.println("[" + tasks.get(ind).getStatusIcon() + "] "
                + tasks.get(ind).getDescription());

    }

    /**
     * Marks specified task as done if taskNumber parameter is a string.
     *
     * @param taskNumber The 1th-index of task to be marked.
     * @throws DukeException If taskNumber is out of bounds.
     */
    public void markTask(String taskNumber, Ui ui) throws DukeException {
        int ind;
        try {
            ind = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Description must be a number.");
        }


        if(ind >= tasks.size() || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks.get(ind).mark();

        if(isSilent) return;

        ui.printSmallSpace();
        ui.println("Nice! I've marked this task as done:\n");
        ui.printBigSpace();
        ui.println("[" + tasks.get(ind).getStatusIcon() + "] "
                + tasks.get(ind).getDescription());

    }

    /**
     * Marks specified task as not done.
     *
     * @param taskNumber The 1th-index of task to be marked.
     * @throws DukeException If taskNumber is out of bounds.
     */
    public void unmarkTask(String taskNumber, Ui ui) throws DukeException {

        int ind;
        try {
            ind = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Description must be a number.");
        }

        if (ind >= tasks.size() || ind < 0) {
            throw new DukeException("Task " + (ind + 1) + " does not exist.");
        }

        tasks.get(ind).unmark();

        if (isSilent) return;

        ui.printSmallSpace();
        ui.println("OK, I've marked this task as not done yet:\n");
        ui.printBigSpace();
        ui.println("[" + tasks.get(ind).getStatusIcon() + "] "
                + tasks.get(ind).getDescription());

    }

    /**
     * Adds a todo task.
     *
     * @param todoTask The task to be added.
     */
    public void addTodo(String todoTask, Ui ui) throws DukeException {

        tasks.add(new Todo(todoTask));

        if (isSilent) return;

        ui.printSmallSpace();
        ui.println("Got it. I've added this task:");
        ui.println(tasks.get(tasks.size() -1));
        ui.printSmallSpace();
        ui.println("Now you have "
                + tasks.size() + (tasks.size() > 1 ? " tasks " : " task ")
                + "in the list.");

    }

    /**
     * Adds a deadline task.
     *
     * @param todoTask The task to be added.
     * @throws DukeException If incorrect arguments are provided.
     */
    public void addDeadline(String todoTask, Ui ui) throws DukeException {

        String[] taskAndDeadline = todoTask.split(" /by ");

        if (taskAndDeadline.length < 2) {
            throw new DukeException("The due date/time cannot be empty.");
        }

        String theTask = taskAndDeadline[0];
        String dueBy = taskAndDeadline[1];
        tasks.add(new Deadline(theTask, dueBy));

        if (isSilent) return;

        ui.printSmallSpace();
        ui.println("Got it. I've added this task:");
        ui.println(tasks.get(tasks.size() -1));
        ui.printSmallSpace();
        ui.println("Now you have "
                + tasks.size() + (tasks.size() > 1 ? " tasks " : " task ")
                + "in the list.");

    }

    /**
     * Adds an event task.
     *
     * @param todoTask The task to be added.
     * @throws DukeException If incorrect arguments are provided.
     */
    public void addEvent(String todoTask, Ui ui) throws DukeException {

        String[] taskAndDeadline = todoTask.split(" /from ");

        if (taskAndDeadline.length < 2) {
            throw new DukeException("The start date/time cannot be empty.");
        }

        String theTask = taskAndDeadline[0];
        String dueBy = taskAndDeadline[1];
        String[] startAndEnd = dueBy.split(" /to ");

        if (startAndEnd.length < 2) {
            throw new DukeException("The end date/time cannot be empty.");
        }

        String start = startAndEnd[0];
        String end = startAndEnd[1];
        tasks.add(new Event(theTask, start, end));

        if (isSilent) return;

        ui.printSmallSpace();
        ui.println("Got it. I've added this task:");
        ui.println(tasks.get(tasks.size() - 1));
        ui.printSmallSpace();
        ui.println("Now you have "
                + tasks.size() + (tasks.size() > 1 ? " tasks " : " task ")
                + "in the list.");

    }

    /**
     * Deletes a specified task.
     *
     * @param input The 1-th index of the task to be deleted.
     * @throws DukeException If incorrect arguments are provided or task index is out of bounds.
     */
    public void deleteTask(String input, Ui ui) throws DukeException {
        int val;
        try {
            val = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Description must be a number.");
        }

        if(val >= tasks.size() || val < 0) {
            throw new DukeException("Task " + (val + 1) + " does not exist.");
        }

        Task temp = tasks.get(val);
        tasks.remove(val);

        if (isSilent) return;

        ui.printSmallSpace();
        ui.println("Noted. I've removed this task:");
        ui.println(temp);
        ui.printSmallSpace();
        ui.println("Now you have " + (tasks.size()) + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");

    }

    /**
     * Finds all tasks containing a keyword and prints them with its respective 1th-index number.
     *
     * @param keyword The keywords to be found.
     * @param ui Ui class for interaction with user.
     */
    public void find(String keyword, Ui ui) throws DukeException {
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i< tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                ui.printSmallSpace();
                ui.println((i+1) +  ".[" + tasks.get(i).getTypeOfTask()
                        + "][" + tasks.get(i).getStatusIcon()
                        + "] " + tasks.get(i).getDescription());
            }
        }

    }

}
