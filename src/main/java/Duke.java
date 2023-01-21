import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static int checkInput(String input) {

        if (input.equals("bye")) {
            return 0;
        } else if (input.equals("list")) {
            return 1;
        } else if (input.equals("mark")){
            return 2;
        } else if (input.equals("unmark")) {
            return 3;
        } else {
            return -1;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontalLine();
        String line;
        ArrayList<Task> listOfTasks = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            printHorizontalLine();
            String[] inputs = new String[2];
            inputs = line.split(" ");
            int instruction = checkInput(inputs[0]);
            if (instruction < 0) {
                Task.addToTasksList(line, listOfTasks);
            } else if (instruction == 1) {
                Task.listTasks(listOfTasks);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
            }
            printHorizontalLine();
        } while (!line.equals("bye"));
    }
}
