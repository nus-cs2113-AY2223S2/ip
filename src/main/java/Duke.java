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
        } else {
            return 2;
        }
    }
    public static void addToList(String input, ArrayList<String> listOfInputs) {
        System.out.println("added: "+input);
        listOfInputs.add(input);
        printHorizontalLine();
    }

    public static void listInputs(ArrayList<String> listOfInputs) {
        for (int i = 0; i < listOfInputs.size(); i++) {
            System.out.println((i+1) + ": " + listOfInputs.get(i));
        }
        printHorizontalLine();
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
        ArrayList<String> listOfInputs = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            printHorizontalLine();
            int instruction = checkInput(line);
            if (instruction == 1) {
                listInputs(listOfInputs);
            }
            else if (instruction == 2) {
                addToList(line, listOfInputs);
            }
            else {
                break;
            }
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }
}
