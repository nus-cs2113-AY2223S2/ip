import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String line;
        String line2;
        String[] textList = new String[100];
        int textListCount = 0;
        Scanner in = new Scanner(System.in);

        System.out.println("______________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________________");

        while(true) {
            line = in.nextLine();
            line2 = line.toLowerCase();
            System.out.println("______________________________________________________________");
            if (line2.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________________________");
                return;
            } else if (line2.equals("list")) { //if input is list
                for (int i = 0; i < textListCount; ++i) {
                    System.out.println(i+1 + ". " + textList[i]);
                }
                System.out.println("______________________________________________________________");
            } else { //if input is any other text
                textList[textListCount] = line;
                textListCount++;
                System.out.println("added: " + line);
                System.out.println("______________________________________________________________");
            }
        }
    }
}
