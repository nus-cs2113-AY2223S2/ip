import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static String linePrint = "____________________________________________________________\n";
    public static void addList(String s, ArrayList<String> myList){
        myList.add(s);
        System.out.println(linePrint + "added: " + s + "\n" + linePrint);
    }
    public static void printList(ArrayList<String> myList){
        System.out.println(linePrint);
        for (int i = 0; i < myList.size(); ++i) {
            System.out.println(i+1 + ". " + myList.get(i));
        }
        System.out.println(linePrint);
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

            if (s.toLowerCase().equals("bye")){
                System.out.println(byeLine);
                flag = true;
            }
            else if (s.toLowerCase().equals("list")){
                printList(myList);
            }
            else {
                addList(s, myList);
            }
        }
    }
}
