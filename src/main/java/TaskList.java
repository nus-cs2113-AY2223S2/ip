import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * This method instantiate a Todo object and add the Todo object to the TaskList
     *
     * @param taskName The name of the TODO task to be added
     **/
    public void addTask(String taskName) {
        if (taskName == null) {
            throw new IllegalArgumentException("Todo description parameter is missing");
        } else {
            Todo t = new Todo(taskName);
            list.add(t);
        }
    }

    /**
     * This method instantiate a Deadline object and add the Deadline object to the TaskList
     *
     * @param taskName a string that represents the name of the Deadline task to add.
     * @param byWhen   a string that represents the deadline of the Deadline task.
     **/
    public void addTask(String taskName, String byWhen) {
        if (taskName == null) {
            throw new IllegalArgumentException("Deadline description parameter is missing");
        } else if (byWhen == null) {
            throw new IllegalArgumentException("Deadline by parameter is missing");
        } else {
            Deadline d = new Deadline(taskName, byWhen);
            list.add(d);
        }
    }

    /**
     * This method instantiate an Event object and add the Event object to the TaskList
     *
     * @param taskName  a string that represents the name of the Event task to add.
     * @param startWhen a string that represents the start date and time of the Event task.
     * @param endWhen   a string that represents the end date and time of the Event task.
     */
    public void addTask(String taskName, String startWhen, String endWhen) {
        if (taskName == null) {
            throw new IllegalArgumentException("Event description parameter is missing");
        } else if (startWhen == null) {
            throw new IllegalArgumentException("Event starting parameter is missing");
        } else if (endWhen == null) {
            throw new IllegalArgumentException("Event ending parameter is missing");
        } else {
            Event e = new Event(taskName, startWhen, endWhen);
            list.add(e);
        }
    }

    /**
     * Unmarks a completed task.
     *
     * @param taskDescription string that represents the index of the task to unmark (1-Indexed).
     * @param ui              an object of the Display class that provides the user interface.
     */
    public void unmarkTask(String taskDescription, Display ui) {
        try {
            int taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber <= 0 || taskNumber > list.size()) {
                ui.taskNotFound();
            } else if (!list.get(taskNumber - 1).isCompleted()) {
                ui.invalidUnmark();
            } else {
                list.get(taskNumber - 1).setCompleted(false);
                ui.validUnmark(list, taskNumber);
            }
        } catch (NumberFormatException e) {
            System.out.println("Index of task to be unmarked is missing");
        }

    }

    /**
     * Marks a completed task.
     *
     * @param taskDescription a string that represents the index of the task to mark (1-Indexed).
     * @param ui              an object of the Display class that provides the user interface.
     */
    public void markTask(String taskDescription, Display ui) {
        try {
            int taskNumber = Integer.parseInt(taskDescription);
            if (taskNumber <= 0 || taskNumber > list.size()) {
                ui.taskNotFound();
            } else if (list.get(taskNumber - 1).isCompleted()) {
                ui.invalidMark();
            } else {
                list.get(taskNumber - 1).setCompleted(true);
                ui.validMark(list, taskNumber);
            }
        } catch (NumberFormatException e) {
            System.out.println("Index of task to be marked is missing");
        }
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1);
            System.out.println(list.get(i).toString());
        }
    }

    public int getTaskCount() {
        return list.size();
    }
}

