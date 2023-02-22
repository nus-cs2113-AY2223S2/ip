import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Duke {
    public static void main(String[] args) {
        File F = new File("src/main/java/duke.txt");
        if (!F.exists()) {
            F = new File("duke.txt");
        }
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        boolean fileDoesExist;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<String> inputCommands = new ArrayList<>();
        try {
            fileDoesExist = false;
            Scanner s = new Scanner(F);
            while (s.hasNext()) {
                processUserInput(tasks,inputCommands,s.nextLine(),F,fileDoesExist);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
        while (true) {
            String userInput = in.nextLine();
            fileDoesExist = true;
            processUserInput(tasks, inputCommands, userInput, F, fileDoesExist);
        }
    }
    private static void writeToFile (String filePath,ArrayList<String>inputCommands) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (String s:inputCommands) {
            fw.write(s);
            fw.write('\n');
        }
        fw.close();
    }
    private static void appendToFile(String filePath, String userInput) throws IOException{
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(userInput);
        fw.write('\n');
        fw.close();
    }
    private static String[] splitActionAndDescription(String userInput) {
        String[] actionAndDescription = userInput.split(" ", 2);
        return actionAndDescription.length == 2 ? actionAndDescription : new String[]{actionAndDescription[0], ""};
    }
    private static void processUserInput(ArrayList<Task> tasks, ArrayList<String> inputCommands, String userInput, File F, boolean fileDoesExist) {
        inputCommands.add(userInput);
        String[] actionAndDescription = splitActionAndDescription(userInput);
        String action = actionAndDescription[0];
        String description = actionAndDescription[1];
        try {
            switch (action) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "list":
                executeListAction(tasks, fileDoesExist);
                break;
            case "mark":
                executeMarkAction(tasks, description, fileDoesExist);
                if (fileDoesExist) {
                    writeToFile(F.getPath(), inputCommands);
                }
                break;
            case "unmark":
                executeUnmarkAction(tasks, description, fileDoesExist);
                if (fileDoesExist) {
                    writeToFile(F.getPath(), inputCommands);
                }
                break;
            case "todo":
                executeTodoAction(tasks, description, fileDoesExist);
                if (fileDoesExist) {
                    appendToFile(F.getPath(), userInput);
                }
                break;
            case "event":
                executeEventAction(tasks, description, fileDoesExist);
                if (fileDoesExist) {
                    appendToFile(F.getPath(), userInput);
                }
                break;
            case "deadline":
                executeDeadlineAction(tasks, description, fileDoesExist);
                if (fileDoesExist) {
                    appendToFile(F.getPath(), userInput);
                }
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IOException e) {
            System.out.println("Unable to write/append to file.");
        }
    }
    private static void executeListAction(ArrayList<Task> tasks, boolean fileDoesExist) {
        if (fileDoesExist) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.print(i + 1 + ".");
                System.out.println(tasks.get(i).toString());
            }
        }
    }
    private static void executeMarkAction(ArrayList<Task> tasks, String description, boolean fileDoesExist) {
        Integer index = Integer.parseInt(description) - 1;
        tasks.get(index).isCompleted = true;
        if (fileDoesExist) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index).toString());
        }
    }
    private static void executeUnmarkAction(ArrayList<Task> tasks, String description, boolean fileDoesExist) {
        Integer index = Integer.parseInt(description) - 1;
        tasks.get(index).isCompleted = false;
        if (fileDoesExist) {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index).toString());
        }
    }
    private static void printAcknowledgement(ArrayList<Task> tasks) {
        int lastIndexOfTasks = tasks.size() - 1;
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(lastIndexOfTasks).toString());
        if (tasks.size() == 1) {
            System.out.println("Now you have " +tasks.size()+" task in the list.");
        } else {
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }
    private static void executeTodoAction(ArrayList<Task> tasks, String description, boolean fileDoesExist) {
        try {
            addTodoToList(tasks, description);
            if (fileDoesExist) {
                printAcknowledgement(tasks);
            }
        } catch (Exception e) {
            System.out.println("Unable to add todo: No tasks given.");
        }
    }
    private static void addTodoToList(ArrayList<Task> tasks, String description) throws DukeException {
        if (description.equals(" ")) {
            throw new DukeException(new IllegalArgumentException());
        }
        tasks.add(new Todo(description));
    }
    private static void executeEventAction(ArrayList<Task> tasks, String description, boolean fileDoesExist) {
        try {
            String[] indexArr = splitDescriptionEvent(description);
            String name = indexArr[0].trim();
            String fromDate = indexArr[1].substring(5).trim();
            String toDate = indexArr[2].substring(3).trim();
            tasks.add(new Event(name, fromDate, toDate));
            if (fileDoesExist) {
                printAcknowledgement(tasks);
            }
        } catch (DukeException e) {
            System.out.println("Not enough commands to execute \"event\"");
        }
    }
    private static String[] splitDescriptionEvent(String description) throws DukeException {
        String[] indexArr = description.split("/", 3);
        if (indexArr.length < 3){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }
    private static void executeDeadlineAction(ArrayList<Task> tasks, String description, boolean fileDoesExist) {
        try {
            String[] indexArr = splitDescriptionDeadline(description);
            String name = indexArr[0].trim();
            String byDate = indexArr[1].substring(3).trim();
            tasks.add(new Deadline(name, byDate));
            if (fileDoesExist) {
                printAcknowledgement(tasks);
            }
        } catch(DukeException e) {
            System.out.println("Not enough commands to execute \"deadline\"");
        }
    }
    private static String[] splitDescriptionDeadline(String description) throws DukeException {
        String[] indexArr = description.split("/", 2);
        if (indexArr.length < 2) {
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }
}