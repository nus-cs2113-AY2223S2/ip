import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String line = "__________________________________________________________";
    private static ArrayList<Task> inputList = new ArrayList<>();
    private static int numTasks = 0;

    private static FileReading f;
    private static final String filePath = "data/duke.txt";

    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, BYE, MARK, UNMARK, DELETE
    }

    public static void deleteTask(String userInput) throws DukeException {
        String taskNum = userInput.substring(userInput.length()-1);
        int x = Integer.parseInt(taskNum);
        if (inputList.get(x-1) == null || inputList.size() == 0) {
            throw new DukeException();
        }
        System.out.println(line + '\n' + "Ok, I have now removed this task: "
                + inputList.get(x-1) + '\n' + line);
        inputList.remove(x-1);
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
                    System.out.println(line);
                    System.out.println("OOPS... task does not exist");
                    System.out.println(line);
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    deleteTask(userInput);
                } catch (DukeException e) {
                    System.out.println(line);
                    //System.out.println("OOPS... The description of a " + userInput + " cannot be empty.");
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("OOPS... I'm sorry, but I don't know what that means :^(");
                    System.out.println(line);
                }
            } else {
                try {
                    addTask(userInput);
                } catch (DukeException e) {
                    System.out.println(line);
                    System.out.println("OOPS... The description of a " + userInput + " cannot be empty.");
                    System.out.println(line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("OOPS... I'm sorry, but I don't know what that means :^(");
                    System.out.println(line);
                }
            }
            f.main(inputList);
            userInput = in.nextLine();
        }
        //f.main(inputList);
        in.close();
    }

    public static void printList(ArrayList<Task> input) {
        System.out.println(line + "\nHere are the tasks in your list: ");
        input.trimToSize();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) == null) {
                break;
            } else {
                System.out.println((i+1) + ". " + input.get(i));
            }
        }
        System.out.println(line);
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + '\n' + logo);
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

    public static void printAddTask(Task t) {
        //print to show Task added to list
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
        if (inputList.get(x-1) == null || inputList.size() == 0) {
            throw new DukeException();
        }
        inputList.get(x-1).markAsDone(userInput);
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
        inputList.add(t);
        numTasks = inputList.size();
        printAddTask(t);
    }

    public static void main(String[] args) throws IOException {
        try {
            //f.createFile();
            f.getFileContents(filePath, inputList);
        } catch (FileNotFoundException e) {
            System.out.println("No such file is found. Creating a new one for you :^)");
            f.createFile();
        } catch (IOException e) {
            System.out.println("An error has occurred :^^( ");
        } catch (NullPointerException e) {
            System.out.println("null pointer :(");
        }

        greet();
        getCommand();
        bye();
    }
}
