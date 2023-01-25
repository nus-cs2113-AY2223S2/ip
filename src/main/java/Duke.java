import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static int isNumeric(String strNum, int count) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int d = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        if (d > count) {
            return -1;
        }
        return d; // Returns 1-index of Task or -1 if the input does not fit a number or is bigger than array size
    }

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        String exit = "Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";
        System.out.println(line + greet); // Duke saying hello
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int count = 0; // counter for how many commands
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                System.out.print(line + exit + System.lineSeparator() + line); // Duke saying goodbye and closes program
                break;
            } else if (command.equals("list")) { // List out all recorded commands
                System.out.print(line);
                for (int i = 0; i < count; ++i) {
                    System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
                }
                System.out.print(line);
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                String[] words = command.split(" ");
                if (words.length == 2) {
                    int indexOfMarking = isNumeric(words[1], count);
                    if (indexOfMarking == -1) {
                        // Duke Echoing back commands
                        System.out.println(line + "added: " + command + System.lineSeparator() + line);
                        tasks[count] = new Task(command);
                        count++;
                        continue;
                    }
                    indexOfMarking--; // change to 0-index
                    tasks[indexOfMarking].setDone(words[0]);
                    if (command.startsWith("mark ")) {
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("  " + tasks[indexOfMarking].getStatusIcon() + " "
                            + tasks[indexOfMarking].description);
                } else {
                    // Duke Echoing back commands
                    System.out.println(line + "added: " + command + System.lineSeparator() + line);
                    tasks[count] = new Task(command);
                    count++;
                }
            } else {
                // Duke Echoing back commands
                System.out.println(line + "added: " + command + System.lineSeparator() + line);
                tasks[count] = new Task(command);
                count++;
            }
        }
    }
}
