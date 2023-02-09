import java.util.ArrayList;
import java.util.List;

public class Duke {

    private List<Task> taskList = new ArrayList<Task>();

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    // public void addTask(String taskName){
    // Task t = new Task(taskName);
    // taskList.add(t);
    // System.out.printf(
    // "Got it. I've added this task:\n" +
    // String.format("added: %s\n", taskName)

    // );
    // }

    public void addEvent(String taskName) {
        Event t = new Event(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void addTodo(String taskName) {
        Todo t = new Todo(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void addDeadline(String taskName) {
        Deadline t = new Deadline(taskName);
        taskList.add(t);
        System.out.printf(
                "Got it. I've added this task:\n" +
                        t.toString() +
                        String.format("\nNow you have %d tasks in the list.\n", taskList.size()));
    }

    public void changeTaskState(boolean doneState, Integer index) {
        if (index > taskList.size()) {
            System.out.println("Please input valid task number!");
        } else {
            index--;
            if (doneState) {
                taskList.get(index).markAsDone();
            } else {
                taskList.get(index).markAsUndone();
            }
        }
    }

    public void list() {
        if (taskList.size() == 0) {
            System.out.println("Task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            Integer i = 0;
            for (Task task : taskList) {
                System.out.printf(String.format("%d.%s\n", i + 1, task.toString()));
                i++;
            }
        }
    }
}
