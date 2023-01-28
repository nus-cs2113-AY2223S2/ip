import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printLine() {
        System.out.println("______________________________________________________________");
    }
    public static void main(String[] args) {
        String line;
        String line2;
        Task[] textList = new Task[100]; //store in array of Tasks to track both description and mark status
        int textListCount = 0;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        while(true) {
            line = in.nextLine();
            line2 = line.toLowerCase();
            Task t = new Task(line);
            printLine();
            if (line2.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                return;
            } else if (line2.equals("list")) { //if input is list
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < textListCount; ++i) {
                    String listLine = i+1 + ". " + "[" + textList[i].getStatusIcon() + "] " + textList[i].description; //display x or blank space depending if element is marked or unmarked
                    System.out.println(listLine);
                }
                printLine();
            } else if (line2.contains("mark") && !line2.contains("unmark")) { //mark is in unmark so need to check like this to avoid unmark being detected as mark
                int indexOfTask = Integer.parseInt(line2.substring(5)) - 1; //find index of the task number starting from 0
                textList[indexOfTask].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [" + textList[indexOfTask].getStatusIcon() + "] " + textList[indexOfTask].description);
                printLine();
            } else if (line2.contains("unmark")) {
                int indexOfTask = Integer.parseInt(line2.substring(7)) - 1;
                textList[indexOfTask].unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("  [" + textList[indexOfTask].getStatusIcon() + "] " + textList[indexOfTask].description);
                printLine();
            }
            else { //if input is any other text
                textList[textListCount] = t; //add all input except for list, bye, mark and unmark to array of tasks
                textListCount++; //counter to help input tasks into correct index in tasks array
                System.out.println("added: " + line);
                printLine();
            }
        }
    }
}
