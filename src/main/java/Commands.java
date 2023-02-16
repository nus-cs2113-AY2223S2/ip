import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Commands implements Command{
    private static String line = "__________________________________________________________";
    private static ArrayList<Task> inputList = new ArrayList<>();
    private static int numTasks = 0;

    @Override
    public String getCommand() {
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        return userInput.trim();
    }

    public static void printUIResponse(CommandType c) {
        System.out.println(line);
        switch(c) {
        case BYE:
            System.out.println("Thank you for choosing Duke Services. Hope to see you again soon! System will shut down in 3..2..1.");
            break;
        case LIST:
            System.out.println("Here are the tasks in your list: ");
            //printTaskList(ArrayList<Task> taskList);
            break;
        case UNMARK:
            System.out.println("Ok, I've marked this task as not done yet:");
            break;
        case MARK:
            System.out.println("Nice! I've marked this task as done:");
            break;
        case TODO:
            System.out.println("Gotcha. I've added this To-Do to the list: ");
            break;
        case DEADLINE:
            System.out.println("Gotcha. I've added this deadline to the list: ");
            break;
        case EVENT:
            System.out.println("Gotcha. I've added this event to the list: ");
            break;
        case DELETE:
            System.out.println("Are you sure you want to delete this task?");
            break;
        default:
            System.out.println("I don't understand what you are trying to say. :( Press help for more info.");
        }
        System.out.println(line);
    }
    @Override
    public void doCommand(String userInput) {
        if (userInput.equals("bye")) {
            printUIResponse(CommandType.BYE);
            return;
        } else if (userInput.equals("list")) {
            printUIResponse(CommandType.LIST);
        }
        String[] words = userInput.split(" ");
        if(!isValidTask(words)) {
            printUIResponse(CommandType.DEFAULT);
        }
        if (words[0].equals("todo") || words[0].equals("deadline") || words[0].equals("event")) {

            addTask(words);
        }
    }

    @Override
    public void addTask(String[] userInput) {
        Task t;
        String descriptor = String.join(" ", userInput);
        if (userInput[0].equals("todo")) {
            t = new Todo(descriptor);
        } else if (userInput[0].equals("deadline")) {
            String by = descriptor.split("/by ")[1];
            descriptor = descriptor.split("/by ")[0];
            t = new Deadline(descriptor, by);
        } else if (userInput[0].equals("event")) {
            String to = descriptor.split("/to ")[1];
            String from = descriptor.split(" /")[1];
            descriptor = descriptor.split("/")[0];
            t = new Event(descriptor, from, to);
        } else {
            throw new IndexOutOfBoundsException();
        }
        inputList.add(t);
        numTasks = inputList.size();
    }

    @Override
    public void deleteTask(String userInput) {

    }

    @Override
    public boolean isValidTask(String userInput) {
        return false;
    }

    @Override
    public boolean isValidTask(String[] userInput) {
        return false;
    }

    @Override
    public void printTaskList(ArrayList<Task> taskList) {

    }
}
