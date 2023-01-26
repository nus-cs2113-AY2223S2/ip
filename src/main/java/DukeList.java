import java.util.ArrayList;
public class DukeList {
    protected static ArrayList<DukeTask> TaskList = new ArrayList<>();
    public static void addTask(String s) {
        if(TaskList.size() == 100){
            DukePrinter.printString("Sorry, the list is full!");
            return;
        }
        TaskList.add(new DukeTask(s));
        DukePrinter.printString("added: " + s);
    }
    public static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < TaskList.size(); i++) {
            TaskList.get(i).printTask(i);
        }
        DukePrinter.printLine();
    }
    public static boolean checkID(int id) {
        if(id >= TaskList.size() || id < 0) {
            System.err.println("Sorry, the id is invalid!");
            DukePrinter.printLine();
            return false;
        } else { return true; }
    }
    public static void markDone(int i) {
        if(checkID(i)) {
            TaskList.get(i).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + TaskList.get(i).TaskName);
            DukePrinter.printLine();
        }
    }
    public static void unmarkDone(int i) {
        if(checkID(i)) {
            TaskList.get(i).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + TaskList.get(i).TaskName);
            DukePrinter.printLine();
        }
    }
}
