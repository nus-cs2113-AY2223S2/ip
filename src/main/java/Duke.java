import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static String linePrint = "____________________________________________________________\n";
    public static void addList(String s, ArrayList<String> myList){
        myList.add("[ ] " + s);
        System.out.println(linePrint + "added: " + s + "\n" + linePrint);
    }
    public static void printList(ArrayList<String> myList){
        System.out.println(linePrint);
        for (int i = 0; i < myList.size(); ++i) {
            System.out.println(i+1 + ". " + myList.get(i));
        }
        System.out.println(linePrint);
    }
    public static void markDone(String s, ArrayList<String> myList){
        String taskToMarkString = s.substring(s.length() - 1);
        int taskToMark = Integer.parseInt(taskToMarkString) - 1;
        myList.set(taskToMark,myList.get(taskToMark).replaceFirst(" ", "X"));
        System.out.println(linePrint
                + "Nice! I've marked this task as done:\n"
                + myList.get(taskToMark) + "\n"
                + linePrint);
    }
    public static void markUndone(String s, ArrayList<String> myList){
        String taskToUnmarkString = s.substring(s.length() - 1);
        int taskToUnmark = Integer.parseInt(taskToUnmarkString) - 1;
        myList.set(taskToUnmark,myList.get(taskToUnmark).replaceFirst("X", " "));
        System.out.println(linePrint
                + "OK, I've marked this task as not done yet:\n"
                + myList.get(taskToUnmark) + "\n"
                + linePrint);
    }
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        String byeLine = "Bye. Hope to see you again soon!\n";
        System.out.println(logo);
        boolean flag = false;
        ArrayList<String> myList = new ArrayList<String>();

        while (!flag){
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            String sArray[] = s.split(" ");
            String firstWord = sArray[0];

            if (s.toLowerCase().equals("bye")){
                System.out.println(byeLine);
                flag = true;
            }
            else if (s.toLowerCase().equals("list")){
                printList(myList);
            }
            else if (firstWord.toLowerCase().equals("mark")){
                markDone(s,myList);
            }
            else if (firstWord.toLowerCase().equals("unmark")){
                markUndone(s,myList);
            }
            else {
                addList(s, myList);
            }
        }
    }
}