import java.util.ArrayList;
public class DukeList {
    private static final ArrayList<DukeTask> taskList = new ArrayList<>();
    private static final int MAX_TASK = 100;
    public static void addTask(DukeTask task) {
        if(taskList.size() == MAX_TASK) {
            DukePrinter.printStringln("Sorry, the list is full!");
            return;
        }
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        task.printTask();
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        DukePrinter.printLine();
    }
    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).printTask(i);
        }
        DukePrinter.printLine();
    }
    public static boolean isValidID(int id) {
        boolean isIDInValid = id < 0 || id >= taskList.size();
        if(isIDInValid) {
            DukePrinter.printErrorln("Sorry, the id is invalid!");
            return false;
        }
        return true;
    }

    public static void markDone(int id) {
        if(isValidID(id)) {
            taskList.get(id).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            taskList.get(id).printTask();
            DukePrinter.printLine();
        }
    }
    public static void unmarkDone(int id) {
        if(isValidID(id)) {
            taskList.get(id).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            taskList.get(id).printTask();
            DukePrinter.printLine();
        }
    }
}
