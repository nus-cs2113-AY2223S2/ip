import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {
    public static final List<String> taskTypes = Arrays.asList("todo", "deadline", "event");
    public static final List<String> listEditableCommands = Arrays.asList("todo", "deadline", "event", "mark","unmark");
    public static final List<String> commands = Arrays.asList("todo", "deadline", "event", "mark", "unmark", "list", "bye");

    public static void printHorizontalLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    public static void listing(ArrayList<Task> listOfTasks, int currentNumberIndex) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < currentNumberIndex; ++i) {
            int counter = i + 1;
            System.out.print("     " + counter + "." + listOfTasks.get(i).taskLabel + listOfTasks.get(i).getStatusIcon() + " ");
            System.out.println(listOfTasks.get(i).description);
        }
    }

    public static int deleting(ArrayList<Task> listOfTasks, int currentNumberIndex, int toDelete){
        System.out.println("     Noted. I've removed this task:");
        currentNumberIndex-=1;
        listOfTasks.remove(toDelete-1);
        System.out.println("     Now you have " + currentNumberIndex + " tasks in the list.");
        return currentNumberIndex;
    }

    public static void checkIfValid(String[] lineComponents) throws InvalidCommand {
        boolean isNotValidCommand = !commands.contains(lineComponents[0]);
        if (isNotValidCommand) {
            throw new InvalidCommand();
        }
    }

    public static void checkIfEmpty(String[] lineComponents) throws InvalidCommand {
        boolean isEmptyCommand = (taskTypes.contains(lineComponents[0]) && lineComponents.length == 1);
        if (isEmptyCommand) {
            throw new InvalidCommand();
        }
    }


    public static void main(String[] args) {
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke \n"
                + "     What can I do for you? \n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        String line;
        ArrayList<Task> taskArrayList= new ArrayList<>(); // Array of Tasks
        int currentNumber = 0; // Current number of tasks

        try {
            currentNumber = LoadFile.loadFileContents("C:\\Users\\aviel\\OneDrive - National University of Singapore\\Documents\\NUS\\Y2\\SEM 4\\CS2113\\ip\\data\\duke.txt",taskArrayList,currentNumber);
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }

        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine(); // Take in input line
            String[] lineComponents = line.split(" ", 2); // Split the input line
            String type = lineComponents[0];
            try {
                checkIfValid(lineComponents);
                try {
                    printHorizontalLine();
                    checkIfEmpty(lineComponents);
                    switch (type) {
                    case "todo":
                        currentNumber = Todo.add(line, taskArrayList, currentNumber);
                        break;
                    case "deadline":
                        currentNumber = Deadline.add(line, taskArrayList, currentNumber);
                        break;
                    case "event":
                        currentNumber = Event.add(line, taskArrayList, currentNumber);
                        break;
                    case "list":
                        listing(taskArrayList, currentNumber);
                        break;
                    case "delete":
                        currentNumber = deleting(taskArrayList, currentNumber, Integer.parseInt(lineComponents[1]));
                        break;
                    case "bye":
                        System.out.println("     Bye. Hope to see you again soon!");
                        break;
                    default:
                        if (line.matches("mark \\d") || line.matches("unmark \\d")) {
                            Task.markOrUnmark(line, taskArrayList, currentNumber);
                            break;
                        }
                    }
                } catch (InvalidCommand e) {
                    System.out.println("     ☹ OOPS!!! The description of a " + lineComponents[0] + " cannot be empty.");
                } finally {
                    printHorizontalLine();
                }
                if (line.matches("bye")) {
                    break;
                }
            } catch (InvalidCommand e) {
                printHorizontalLine();
                System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printHorizontalLine();
            }
            try {
                if(listEditableCommands.contains(type)){
                    WriteFile.writeToFile("C:\\Users\\aviel\\OneDrive - National University of Singapore\\Documents\\NUS\\Y2\\SEM 4\\CS2113\\ip\\data\\duke.txt",taskArrayList);
                }
                else{
                    continue;
                }
            } catch(IOException e){};
        }
    }
}
