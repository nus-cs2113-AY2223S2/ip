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
        String checkDone = "mark";
        String[] toDoList = new String[100];
        Boolean[] isDone = new Boolean[100];
        int numberOfItems = 0;

        while (!line.equals(exitCommand)) {
            if (line.equals(toDo)) {
                System.out.println("Here are your list of tasks to complete!");
                for (int i = 0; i < numberOfItems; ++i) {
                    System.out.format("%d. ", (i + 1));
                    String status = getStatus(i, isDone);
                    System.out.println(status + " " + toDoList[i]);
                }
                line = in.nextLine();
            } else if (line.equals(checkDone)){
                System.out.println("check done");
                line = in.nextLine();
            } else {
                toDoList[numberOfItems] = line;
                isDone[numberOfItems] = false;
                ++numberOfItems;

                System.out.println("added: " + line);
                line = in.nextLine();
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
