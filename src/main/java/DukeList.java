import java.util.ArrayList;
public class DukeList {
    protected static ArrayList<DukeTask> list = new ArrayList<>();
    public static void added(String s) {
        if(list.size() == 100){
            Print.PrintString("Sorry, the list is full!");
            return;
        }
        list.add(new DukeTask(s));
        Print.PrintString("added: " + s);
    }
    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < list.size(); i++) {
            list.get(i).printTask(i);
        }
        Print.PrintLine();
    }
    public static boolean checkid(int id) {
        if(id >= list.size() || id < 0) {
            System.err.println("Sorry, the id is invalid!");
            Print.PrintLine();
            return false;
        }
        else {
            return true;
        }
    }
    public static void markDone(int i) {
        if(checkid(i)) {
            list.get(i).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + list.get(i).description);
            Print.PrintLine();
        }
    }
    public static void unmarkDone(int i) {
        if(checkid(i)) {
            list.get(i).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[ ] " + list.get(i).description);
            Print.PrintLine();
        }
    }
}
