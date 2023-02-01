import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String line = "__________________________________________________________";
    private static Task[] inputList = new Task[101];
    private static int length = 0;

    public static void addTask(String userInput) {
        Task t;
        if (userInput.contains("todo")) {
            t = new Todo(userInput.substring(userInput.indexOf(" "), userInput.length()));
        } else if (userInput.contains("deadline")){
            t = new Deadline(userInput.substring(userInput.indexOf(" "), userInput.length()));
        } else {
            t = new Event(userInput.substring(userInput.indexOf(" "), userInput.length()));
        }
        t.printAddTask();
        inputList[length+1] = t; //1-index
        length++;
        System.out.println("Now you have " + length + " tasks in the list.");
        System.out.println(line);
    }

    public static void printList(Task[] input) {
        System.out.println(line + "\nHere are the tasks in your list: ");
        for (int i = 1; i < input.length; i++) {
            if (input[i] == null) {
                break;
            }
            System.out.print((i) + ".");
            input[i].printTask();
        }
        System.out.println(line);
    }

    public static void commands() {
        Scanner in = new Scanner(System.in);
        //Task[] inputList = new Task[101];
        String userInput = in.nextLine();
        boolean exit = false;
        //int length = 0; //0-index
        while (!exit) {
            if (userInput.contains("bye")) {
                break;
            } else if (userInput.contains("list")) {
                //System.out.println(inputList.length);
                printList(inputList);
            } else if (userInput.contains("mark")) {
                String taskNum = userInput.substring(userInput.length()-1);
                int x = Integer.parseInt(taskNum);
                inputList[x].markAsDone();
            } else {
                addTask(userInput);
            }
            userInput = in.nextLine();
        }
        in.close();
    }

    public static void greet() {
        String line = "__________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! i'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line + '\n');
        return;
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        //System.out.println("Hello from\n" + logo);
        greet();
        commands();
        bye();
    }
}
