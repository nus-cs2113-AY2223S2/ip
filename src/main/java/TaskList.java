import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;
    private static final String DIVIDER  = "______________________________";

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public int getSize() {
        return list.size();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task getTask(int taskNum) {
        return list.get(taskNum);
    }

    public void printList() {
        String listMessage = DIVIDER + System.lineSeparator() + "Here are the tasks in your list:";
        System.out.println(listMessage);
        for (int i = 0; i < getSize(); i++) {
            System.out.println((i+1) + "." + getTask(i).toString());
        }
        System.out.println(DIVIDER);
    }

    public void markTask(int taskNum) {
        getTask(taskNum-1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(DIVIDER + System.lineSeparator() + getTask(taskNum-1).toString()
                + System.lineSeparator() + DIVIDER);
    }

    public void unmarkTask(int taskNum) {
        getTask(taskNum-1).markAsNotDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(DIVIDER + System.lineSeparator() + getTask(taskNum-1).toString()
                + System.lineSeparator() + DIVIDER);
    }

    public void deleteTask(int taskNum) {
        String acknowledge = DIVIDER + System.lineSeparator() + "Noted. I've removed this task: "
                + System.lineSeparator() + getTask(taskNum-1).toString() + System.lineSeparator()
                + "Now you have " + (getSize()-1) + " tasks in the list." + System.lineSeparator() + DIVIDER;
        System.out.println(acknowledge);
        list.remove(taskNum-1);
    }

    public void acknowledgementMessage() {
        String acknowledgement = DIVIDER + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + getTask(getSize()-1).toString();
        System.out.println(acknowledgement);
        System.out.println("Now you have " + (getSize()) + " task(s) in the list."
                + System.lineSeparator() + DIVIDER);
    }
}
