import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    public static void command(String line) {
        String exitCommand = "bye";
        String toDo = "list";
        String[] toDoList = new String[100];
        int numberOfItems = 0;

        while (!line.equals(exitCommand)) {
            if (line.equals(toDo)) {
                for (int i = 0; i < numberOfItems; ++i) {
                    System.out.format("%d. ", (i + 1));
                    System.out.println(toDoList[i]);
                }
                Scanner in = new Scanner(System.in);
                line = in.nextLine();
            } else {
                toDoList[numberOfItems] = line;
                ++numberOfItems;

                System.out.println("added: " + line);

                Scanner in = new Scanner(System.in);
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
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        command(line);
    }
}
