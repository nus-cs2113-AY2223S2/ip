import java.util.ArrayList;

public class List {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public static void addTask(String item, TaskType taskType) {
        String taskString;
        Task task = new Task(item);
        try {
            if (item.length() == 0) {
                throw new EmptyTaskInputException();
            }

            switch (taskType) {
            case DEADLINE:
                task = new Deadline(item);
                taskString = task.toString();
                break;
            case EVENT:
                task = new Event(item);
                taskString = task.toString();
                break;
            case TODO:
                task = new Task(item);
                taskString = task.toString();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Message.line();
            System.out.println("You are missing some parameters in the input! Please try again!");
            Message.line();
            return;
        } catch (EmptyTaskInputException e) {
            Message.line();
            System.out.println("Your input description cannot be empty! Please try again!");
            Message.line();
            return;
        }
        tasks.add(task);
        Message.line();
        System.out.println("Great job adding a new task!");
        System.out.println("Added: " + task);
        System.out.println("You currently have " + tasks.size() + " tasks.");
        Message.line();
    }

    public static void printList() {
        if (tasks.size() > 0) {
            Message.line();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(Integer.toString(i + 1) + "." + tasks.get(i));
            }
            Message.line();
        } else {
            Message.line();
            System.out.println("You have no tasks at the moment. Please add some tasks!");
            Message.line();
        }
    }

    public static void markDone(int index) {
        tasks.get(index - 1).setIsDone(true);
        Message.line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1));
        Message.line();
    }

    public static void markUndone(int index) {
        tasks.get(index - 1).setIsDone(false);
        Message.line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1));
        Message.line();
    }
}
