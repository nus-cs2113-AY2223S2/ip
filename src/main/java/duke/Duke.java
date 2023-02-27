package duke;

import duke.command.CommandManager;
import duke.command.FolderNotFoundException;
import duke.command.NoKeyException;
import duke.command.Storage;
import duke.task.Dateline;
import duke.task.Event;
import duke.task.Tasks;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IOException, NoKeyException {
        Storage storage = new Storage("data", "data/Duke.txt");
        Scanner in = new Scanner(System.in);
        CommandManager.sayHi();
        try {
            storage.load();
        } catch (FileNotFoundException e) {
            System.out.println("Duke.txt file does not exist in /data. Creating one for you...");
            storage.createNewFile();
        } catch (FolderNotFoundException e) {
            System.out.println("data folder does not exist. Creating one for you...");
            storage.createNewFolder();
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
                storage.save();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            command.setUserInput(in.nextLine());
        }
        CommandManager.sayBye();
    }
}
