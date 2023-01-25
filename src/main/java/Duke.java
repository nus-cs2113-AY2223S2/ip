import java.util.Locale;
import java.util.Scanner;
public class Duke {

    public static void printCommand(String s){
        String toPrint = "____________________________________________________________\n"
                + s + "\n"
                + "____________________________________________________________\n";
        System.out.println(toPrint);

    }
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        String byeLine = "Bye. Hope to see you again soon!\n";
        System.out.println(logo);
        boolean flag = false;

        while (!flag){
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            if (s.toLowerCase().equals("bye")){
                System.out.println(byeLine);
                flag = true;
            }
            else {
                printCommand(s);
            }
        }
    }
}
