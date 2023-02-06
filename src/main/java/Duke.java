import java.util.Scanner;

public class Duke {
    private static String line = "__________________________________________________________";
    private static Task[] inputList = new Task[101];
    private static int numTasks = 0;

    public static void printAddTask(Task t) {
        //print to show added to list
        System.out.println(line);
        System.out.println("Got it. I've added this task: \n" + t);
        System.out.println("Now you have " + numTasks + " tasks in the list.");
        System.out.println(line);
    }

    public static void validTask(String[] userInput) throws DukeException{
        if (userInput.length < 2 && (userInput[0].equals("todo") ||
                userInput[0].equals("event") || userInput[0].equals("deadline"))) {
            throw new DukeException();
        }
    }

    public static void validTask(String userInput) throws DukeException {
        String taskNum = userInput.substring(userInput.length()-1);
        int x = Integer.parseInt(taskNum);
        if (inputList[x] == null) {
            throw new DukeException();
        }
        inputList[x].markAsDone(userInput);
    }

    public static void addTask(String userInput) throws DukeException {
        Task t;
        String[] words = userInput.split(" ");
        validTask(words);
        String descriptor = userInput.substring(userInput.indexOf(words[1]), userInput.length());
        if (words[0].equals("todo")) {
            t = new Todo(descriptor);
        } else if (words[0].equals("deadline")) {
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            t = new Deadline(descriptor, by);
        } else if (words[0].equals("event")) {
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            t = new Event(descriptor, from, to);
        } else {
            throw new IndexOutOfBoundsException();
        }
        inputList[numTasks+1] = t; //1-index
        numTasks++;
        printAddTask(t);
    }

    public static void printList(Task[] input) {
        System.out.println(line + "\nHere are the tasks in your list: ");
        for (int i = 1; i < input.length; i++) {
            if (input[i] == null) {
                break;
            }
            System.out.print(i + ". " + input[i]);
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
                try {
                    validTask(userInput);
                } catch (DukeException e) {
                    System.out.println("OOPS... task does not exist");
                }
            } else {
                try {
                    addTask(userInput);
                } catch (DukeException e) {
                    System.out.println("OOPS... The description of a " + userInput + " cannot be empty.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS... I'm sorry, but I don't know what that means :^(");
                }
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
