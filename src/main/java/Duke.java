import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    private static Scanner in = new Scanner(System.in);

    public static String getStatus(int itemNumber, Boolean[] isDone) {
        if (isDone[itemNumber] == true) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public static void command(String line) {
        String exitCommand = "bye";
        String toDo = "list";
        String checkOff = "mark";

        String[] toDoList = new String[100];
        Boolean[] isDone = new Boolean[100];
        int numberOfItems = 0;

        String[] tokens = line.split(" ", 0);
        String command = tokens[0];

        while (!command.equals(exitCommand)) {
            if (tokens[0].equals(toDo)) {
                System.out.println("Here are your list of tasks to complete!");
                for (int i = 0; i < numberOfItems; ++i) {
                    System.out.format("%d. ", (i + 1));
                    String status = getStatus(i, isDone);
                    System.out.println(status + " " + toDoList[i]);
                }
                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else if (command.equals(checkOff)){
                int checkDone = Integer.parseInt(tokens[1]) - 1;
                isDone[checkDone] = true;
                System.out.println("Alright! I have marked the task as complete!");

                for (int i = 0; i < numberOfItems; ++i) {
                    System.out.format("%d. ", (i + 1));
                    String status = getStatus(i, isDone);
                    System.out.println(status + " " + toDoList[i]);
                }

                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else {
                toDoList[numberOfItems] = line;
                isDone[numberOfItems] = false;
                ++numberOfItems;

                System.out.println("added: " + line);
                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            }
        }
        System.out.println("Bye! Hope to see you again");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! My name is Duke. \n Nice to meet you!");

        String line;
        line = in.nextLine();
        command(line);
    }
}
