import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list = new ArrayList<>();

    /**
     * This method instantiate a Todo object and add the Todo object to the TaskList
     *
     * @param taskName The name of the TODO task to be added
     **/
    public void addTask(String taskName) {
        Todo t = new Todo(taskName);
        list.add(t);
    }

    /**
     * This method instantiate a Todo object and add the Todo object to the TaskList
     *
     * @param taskName a string that represents the name of the TODO task to add.
     **/
    public void addTask(String taskName, String byWhen) {
        Deadline d = new Deadline(taskName, byWhen);
        list.add(d);
    }

    /**
     * This method instantiate an Event object and add the Event object to the TaskList
     *
     * @param taskName  a string that represents the name of the EVENT task to add.
     * @param startWhen a string that represents the start date and time of the EVENT task.
     * @param endWhen   a string that represents the end date and time of the EVENT task.
     */
    public void addTask(String taskName, String startWhen, String endWhen) {
        Event e = new Event(taskName, startWhen, endWhen);
        list.add(e);
    }

    /**
     * Unmarks a completed task.
     *
     * @param taskNumber an integer that represents the index of the task to unmark (1-Indexed).
     * @param ui         an object of the Display class that provides the user interface.
     */
    public void unmarkTask(int taskNumber, Display ui) {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            ui.taskNotFound();
        } else if (!list.get(taskNumber - 1).isCompleted()) {
            ui.invalidUnmark();
        } else {
            list.get(taskNumber - 1).setCompleted(false);
            ui.validUnmark(list, taskNumber);
        }
    }

    /**
     * Marks a completed task.
     *
     * @param taskNumber an integer that represents the index of the task to mark (1-Indexed).
     * @param ui         an object of the Display class that provides the user interface.
     */
    public void markTask(int taskNumber, Display ui) {
        if (taskNumber <= 0 || taskNumber > list.size()) {
            ui.taskNotFound();
        } else if (list.get(taskNumber - 1).isCompleted()) {
            ui.invalidMark();
        } else {
            list.get(taskNumber - 1).setCompleted(true);
            ui.validMark(list, taskNumber);
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

