import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static final int MAX_ARRAY_SIZE = 100;

    public static int isNumeric(String strNum, int count) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int index = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            index = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        if (index > count) {
            return -1;
        }
        return index; // Returns 1-index of Task or -1 if the input does not fit a number or is bigger than array size
    }

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        String exit = "Bye. Hope to see you again soon!\n";
        String line = "____________________________________________________________\n";
        System.out.println(line + greet); // Duke saying hello
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[MAX_ARRAY_SIZE];
        int commandCount = 0; // counter for how many commands
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                System.out.print(line + exit + System.lineSeparator() + line); // Duke saying goodbye and closes program
                break;
            } else if (command.equals("list")) { // List out all recorded commands
                System.out.print(line);
                for (int i = 0; i < commandCount; ++i) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
                System.out.println(line);
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                String[] words = command.split(" ");
                if (words.length == 2) {
                    int indexOfMarking = isNumeric(words[1], commandCount);
                    if (indexOfMarking == -1) {
                        // Duke Echoing back commands
                        System.out.println(line + "added: " + command + System.lineSeparator() + line);
                        tasks[commandCount] = new Task(command);
                        commandCount++;
                        continue;
                    }
                    indexOfMarking--; // change to 0-index
                    tasks[indexOfMarking].setDone(words[0]);
                    System.out.print(line);
                    if (command.startsWith("mark ")) {
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("  " + tasks[indexOfMarking].toString() + System.lineSeparator() + line);
                } else {
                    // Duke Echoing back commands
                    System.out.println(line + "added: " + command + System.lineSeparator() + line);
                    tasks[commandCount] = new Task(command);
                    commandCount++;
                }
            } else if (command.startsWith("todo ")) {
                String todo = command.replace("todo ", "");
                tasks[commandCount] = new Todo(todo);
                System.out.println(line + tasks[commandCount].addTaskMessage() + "Now you have " + (commandCount + 1)
                        + " tasks in the list." + System.lineSeparator() + line);
                commandCount++;
            } else if (command.startsWith("deadline ")) {
                command = command.replace("deadline ", "");
                String[] stringSplit = command.split(" /by ");
                tasks[commandCount] = new Deadline(stringSplit[0], stringSplit[1]);
                System.out.println(line + tasks[commandCount].addTaskMessage() + "Now you have " + (commandCount + 1)
                        + " tasks in the list." + System.lineSeparator() + line);
                commandCount++;
            } else if (command.startsWith("event ")) {
                command = command.replace("event ", "");
                String[] stringSplit = command.split(" /from ");
                String[] fromAndTo = stringSplit[1].split(" /to ");
                tasks[commandCount] = new Event(stringSplit[0], fromAndTo[0], fromAndTo[1]);
                System.out.println(line + tasks[commandCount].addTaskMessage() + "Now you have " + (commandCount + 1)
                        + " tasks in the list." + System.lineSeparator() + line);
                commandCount++;
            } else {
                // Duke Echoing back commands
                System.out.println(line + "added: " + command + System.lineSeparator() + line);
                tasks[commandCount] = new Task(command);
                commandCount++;
            }
        }
    }
}
