import java.util.ArrayList;

public class Lists {
    private static ArrayList<Task> list = new ArrayList<Task>();

    public static void addItem(String item, TaskType taskType) {
        Task task = new Task(item);
        switch (taskType) {
        case DEADLINE:
            task = new Deadline(item);
            break;
        case EVENT:
            task = new Event(item);
            break;
        case TODO:
            task = new Task(item);
        }
        list.add(task);
        Messages.line();
        System.out.println("Added: " + task);
        System.out.println("You currently have " + list.size() + " tasks.");
        Messages.line();
    }

    public static void printList() {
        Messages.line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ".[" + list.get(i).getType() +
                    "][" + list.get(i).getStatusIcon() + "] " + list.get(i));
        }
        Messages.line();
    }

    public static void markDone(int index) {
        list.get(index - 1).isDone = true;
        Messages.line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + list.get(index - 1).getStatusIcon() + "] " + list.get(index - 1).description);
        Messages.line();
    }

    public static void markUndone(int index) {
        list.get(index - 1).isDone = false;
        Messages.line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [" + list.get(index - 1).getStatusIcon() + "] " + list.get(index - 1).description);
        Messages.line();
    }
}
