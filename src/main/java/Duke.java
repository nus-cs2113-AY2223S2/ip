import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printLine() {
        System.out.println("______________________________________________________________");
    }
    public static void main(String[] args) {
        String line;
        String lowerCaseLine;
        Task[] textList = new Task[100]; //store in array of Tasks to track both description and mark status
        int textListCount = 0;
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();

        while(true) {
            line = in.nextLine();
            lowerCaseLine = line.toLowerCase();
            Task task = new Task(line);
            printLine();
            if (lowerCaseLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                return;
            } else if (lowerCaseLine.equals("list")) { //if input is list
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < textListCount; ++i) {
                    String listLine = i+1 + ". " + textList[i].toString();
                    System.out.println(listLine);
                }
                printLine();
            } else if (lowerCaseLine.contains("mark") && !lowerCaseLine.contains("unmark")) { //mark is in unmark so need to check like this to avoid unmark being detected as mark
                int indexOfTask = Integer.parseInt(lowerCaseLine.substring(5)) - 1; //find index of the task number starting from 0
                textList[indexOfTask].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(" " + textList[indexOfTask].toString());
                printLine();
            } else if (lowerCaseLine.contains("unmark")) {
                int indexOfTask = Integer.parseInt(lowerCaseLine.substring(7)) - 1;
                textList[indexOfTask].unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(" " + textList[indexOfTask].toString());
                printLine();
            }
            else { //if input is any other text
                if (lowerCaseLine.contains("todo")) {
                    task = new Todo(line.substring(5));
                } else if (lowerCaseLine.contains("deadline")) {
                    int indexOfBy = line.indexOf("/by");
                    task = new Deadline(line.substring(9, indexOfBy), line.substring(indexOfBy + 4));
                } else if (lowerCaseLine.contains("event")) {
                    int indexOfFrom = line.indexOf("/from");
                    int indexOfTo = line.indexOf("/to");
                    task = new Event(line.substring(6, indexOfFrom), line.substring(indexOfFrom + 6, indexOfTo), line.substring(indexOfTo + 4));
                }
                textList[textListCount] = task; //add all input except for list, bye, mark and unmark to array of tasks
                textListCount++; //counter to help input tasks into correct index in tasks array
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + task.toString());
                System.out.println("Now you have " + textListCount + " tasks in the list.");
                printLine();
            }
        }
    }
}
