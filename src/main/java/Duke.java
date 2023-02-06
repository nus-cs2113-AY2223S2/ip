import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String line = "__________________________________________________________";
    private static Task[] inputList = new Task[101];
    private static int numTasks = 0;

    public static void addTask(String userInput) {
        Task t;
        String descriptor = userInput.substring(userInput.indexOf(" "), userInput.length());
        if (userInput.startsWith("todo")) {
            t = new Todo(descriptor);
        } else if (userInput.startsWith("deadline")) {
            t = new Deadline(descriptor);
        } else if (userInput.startsWith("event")) {
            t = new Event(descriptor);
        } else {
            System.out.println("OOPS! I'm sorry, but I don't know what that means :^(");
            return;
        }
        t.printAddTask();
        inputList[numTasks+1] = t; //1-index
        numTasks++;
        System.out.println("Now you have " + numTasks + " tasks in the list.");
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

    public static void getCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while (true) {
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
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
        getCommand();
        bye();
    }
}
