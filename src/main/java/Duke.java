import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static void main(String[] args) {
        String lineBreak = "---------------------------------------------";
        String line = "";
        String[] list = new String[100];
        int index = 0;
        Scanner in = new Scanner(System.in);
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println(lineBreak);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(lineBreak);
        while (true) {
            line = in.nextLine();
            System.out.println(lineBreak);
            if (line.equals("list")) {
                int itemNumber = 1;
                String[] fullList = Arrays.copyOf(list, index);
                for (String item : fullList) {
                    System.out.println(itemNumber + ". " + item);
                    itemNumber++;
                }
                System.out.println(lineBreak);
            }
            else if (line.equals("bye")) {
                break;
            }
            else {
                list[index] = line;
                index++;
                System.out.println("added: " + line);
                System.out.println(lineBreak);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
        return;
    }
}
