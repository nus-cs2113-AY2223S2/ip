import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
        String openingMsg = "Hello! I'm Dukebot\n\tWhat can I do for you?";
        String closingMsg = "Goodbye! Hope to see you again soon ^^!";
        String horizontal = "---------------------------------";
        Task[] list = new Task[100];

        System.out.println(horizontal + "\n\t" + openingMsg + "\n" + horizontal);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine().toLowerCase();
        String lineAr[] = new String[2];
        int listNum;

        int item = 0;

        while (!line.equals("bye")) {
            if (!(line.equals("list") || line.startsWith("mark") || line.startsWith("unmark"))) {
                list[item] = new Task(line);
                System.out.println(horizontal + "\n\tadded: " + list[item].description + "\n" + horizontal);
                item++;
            }

            line = in.nextLine().toLowerCase();

            // display list
            if (line.equals("list")) {
                System.out.println(horizontal + "\n\tHere are the tasks in your list:");
                for (int i = 0; i < item; i++) {
                    System.out.println("\n\t" + (i + 1) + ". [" + list[i].getStatusIcon() + "] " + list[i].description + "\n");
                }
                System.out.println(horizontal);
            }

            // mark item as done
            if (line.startsWith("mark")) {


                lineAr = line.split(" ");
                listNum = Integer.parseInt(lineAr[1]) - 1;

                // check the list item number is in range

                if (listNum < item) {
                    list[listNum].isDone = true;
                    System.out.println(horizontal + "\n\tNice! I've marked this task as done:");
                    System.out.println("\n\t\t" + "[" + list[listNum].getStatusIcon() + "] " + list[listNum].description + "\n");
                    System.out.println(horizontal);
                }
                else {
                    System.out.println("Item number " + (listNum + 1) + " does not exist yet");
                }

                // TODO maybe do some checks like if it is already done
            }

            // unmark item
            if (line.startsWith("unmark")) {

                // check the list item number is in range
                lineAr = line.split(" ");
                listNum = Integer.parseInt(lineAr[1]) - 1;

                if (listNum < item) {
                    list[listNum].isDone = false;
                    System.out.println(horizontal + "\n\tOki! I've marked this task as not done yet:");
                    System.out.println("\n\t\t" + "[" + list[listNum].getStatusIcon() + "] " + list[listNum].description + "\n");
                    System.out.println(horizontal);
                }
                else {
                    System.out.println("Item number " + (listNum + 1) + " does not exist yet");
                }

                // TODO maybe do some checks like if it is already not done
            }

        }

        System.out.println(horizontal + "\n\t" + closingMsg + "\n" + horizontal);

    }
}
