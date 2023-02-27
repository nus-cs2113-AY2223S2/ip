
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static void readFromFile() throws FileNotFoundException, FolderNotFoundException {
        File folder = new File("data");
        if (!folder.exists()) {
            throw new FolderNotFoundException();
        }
        File f = new File("data/Duke.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String row = s.nextLine();
            int posOfStartBracket = row.indexOf("(");
            int posOfEndBracket = row.indexOf(")");
            String[] timeSpan = new String[2]; //String array of size 2;
            String date = new String();
            if (row.contains("[E]")) {
                timeSpan = (row.substring(posOfStartBracket + 1, posOfEndBracket)).split(" To: ", 2);
                timeSpan[0] = timeSpan[0].replaceFirst("From: ", "");
            }
            if (row.contains("D")) {
                date = row.substring(posOfStartBracket + 1, posOfEndBracket).replace("By: ", "");
            }
            String taskIdentifier = row.substring(0, 6);
            switch (taskIdentifier) {
            case "[T][ ]":
                Tasks.addToList(new Todo(row.substring(7), false));
                break;
            case "[T][X]":
                Tasks.addToList(new Todo(row.substring(7), true));
                break;
            case "[D][ ]":
                Tasks.addToList(new Dateline(row.substring(7, posOfStartBracket - 1), false,
                        date));
                break;
            case "[D][X]":
                Tasks.addToList(new Dateline(row.substring(7, posOfStartBracket - 1), true,
                        date));
                break;
            case "[E][ ]":
                Tasks.addToList(new Event(row.substring(7, posOfStartBracket - 1), false,
                        timeSpan[0], timeSpan[1]));
                break;
            case "[E][X]":
                Tasks.addToList(new Event(row.substring(7, posOfStartBracket - 1), true,
                        timeSpan[0], timeSpan[1]));
                break;
            }
        }
    }
    public static void main(String[] args) throws IOException, NoKeyException {
        Scanner in = new Scanner(System.in);
        CommandManager.sayHi();
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("Duke.txt file does not exist in /data. Creating one for you...");
            File f = new File("data/Duke.txt");
            f.createNewFile();
        } catch (FolderNotFoundException e) {
            System.out.println("data folder does not exist. Creating one for you...");
            File folder = new File("data");
            folder.mkdir();
        }
        CommandManager command = new CommandManager();
        command.setUserInput(in.nextLine());
        while (!command.getUserInput().equals("bye")) {
            String[] userInput = command.getUserInput().split(" ", 2);
            String key = userInput[0];
            switch (key) {
            case "mark":
                try {
                    Tasks markTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                    markTask.setMarked(true);
                    command.setKey("mark");
                    command.printOutput(markTask);
                } catch (NumberFormatException e) {
                    System.out.println("You entered an INVALID token after mark.\nPlease enter a number" +
                            " after mark. EXAMPLE: mark 2");
                }
                break;
            case "unmark":
                try {
                    Tasks unMarkTask = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                    unMarkTask.setMarked(false);
                    command.setKey("unmark");
                    command.printOutput(unMarkTask);
                } catch (NumberFormatException e) {
                    System.out.println("You entered an INVALID token after mark.\n" +
                            "CORRECT UNMARK FORMAT: unmark 2");
                }
                break;
            case "todo":
                try {
                    Tasks newToDo = new Todo(userInput[1], false);
                    Tasks.addToList(newToDo);
                    command.setKey("add");
                    command.printOutput(newToDo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There is NO description!!!");
                }
                break;
            case "deadline":
                try {
                    String[] taskDate = userInput[1].split(" /by ", 2);
                    Tasks newDeadline = new Dateline(taskDate[0], false, taskDate[1]);
                    Tasks.addToList(newDeadline);
                    command.setKey("add");
                    command.printOutput(newDeadline);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("INVALID DEADLINE FORMAT:" + "\nCORRECT DEADLINE FORMAT: \"deadline return book/by Sunday\"");
                }
                break;
            case "event":
                if (!userInput[1].contains(" /from ") | (!userInput[1].contains(" /to "))) {
                    throw new NoKeyException();
            }
                String[] eventSlashDate = userInput[1].split(" /from | /to ", 3);
                Tasks newEvent = new Event(eventSlashDate[0], false, eventSlashDate[1], eventSlashDate[2]);
                Tasks.addToList(newEvent);
                command.setKey("add");
                command.printOutput(newEvent);
                break;
            case "list":
                command.printOutput();
                break;
            case "delete":
                try {
                    Tasks toDelete = Tasks.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
                    command.setKey("delete");
                    command.printOutput(toDelete);
                    Tasks.deleteFromList(Integer.parseInt(userInput[1]) - 1);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Delete failed. No items in list.");
                }
                break;
            default:
                System.out.println("Try again!");
            }
            try {
                CommandManager.writeToFile("data/Duke.txt");
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            command.setUserInput(in.nextLine());
        }
        CommandManager.sayBye();
    }
}
