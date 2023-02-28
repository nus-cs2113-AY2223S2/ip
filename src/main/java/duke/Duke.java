package duke;


import duke.exception.FolderNotFoundException;
import duke.exception.NoKeyException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    public static void main(String[] args) throws IOException{
        Storage storage = new Storage("data", "data/Duke.txt");
        Ui ui = new Ui();
        Ui.sayHi();
        try {
            storage.load();
        } catch (FileNotFoundException e) {
            Ui.displayErrorFileNotFoundException();
            storage.createNewFile();
        } catch (FolderNotFoundException e) {
            Ui.displayErrorFolderNotFoundException();
            storage.createNewFolder();
        }
        do {
            try {
                ui.readUserInput();
                Parser.parseCommand(ui.getUserInput());
                storage.save();
            } catch (NoKeyException e) {
                Ui.displayErrorNoKeyException();
            } catch (IOException e) {
                Ui.displayErrorIOException();
            }
        } while (!ui.getUserInput().equals("/bye"));

//        while (!Ui.readUserInput().equals("bye")) {
//            String[] userInput = command.getUserInput().split(" ", 2);
//            String key = userInput[0];
//            //parse String command
//            switch (key) {
//            case "mark":
//                try {
//                    Tasks markTask = TaskList.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
//                    markTask.setMarked(true);
//                    command.setKey("mark");
//                    command.printOutput(markTask);
//                } catch (NumberFormatException e) {
//                    System.out.println("You entered an INVALID token after mark.\nPlease enter a number" +
//                            " after mark. EXAMPLE: mark 2");
//                }
//                break;
//            case "unmark":
//                try {
//                    Tasks unMarkTask = TaskList.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
//                    unMarkTask.setMarked(false);
//                    command.setKey("unmark");
//                    command.printOutput(unMarkTask);
//                } catch (NumberFormatException e) {
//                    System.out.println("You entered an INVALID token after mark.\n" +
//                            "CORRECT UNMARK FORMAT: unmark 2");
//                }
//                break;
//            case "todo":
//                try {
//                    Tasks newToDo = new Todo(userInput[1], false);
//                    TaskList.addToList(newToDo);
//                    command.setKey("add");
//                    command.printOutput(newToDo);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("There is NO description!!!");
//                }
//                break;
//            case "deadline":
//                try {
//                    String[] taskDate = userInput[1].split(" /by ", 2);
//                    Tasks newDeadline = new Dateline(taskDate[0], false, taskDate[1]);
//                    TaskList.addToList(newDeadline);
//                    command.setKey("add");
//                    command.printOutput(newDeadline);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("INVALID DEADLINE FORMAT:" + "\nCORRECT DEADLINE FORMAT: \"deadline return book/by Sunday\"");
//                }
//                break;
//            case "event":
//                if (!userInput[1].contains(" /from ") | (!userInput[1].contains(" /to "))) {
//                    throw new NoKeyException();
//            }
//                String[] eventSlashDate = userInput[1].split(" /from | /to ", 3);
//                Tasks newEvent = new Event(eventSlashDate[0], false, eventSlashDate[1], eventSlashDate[2]);
//                TaskList.addToList(newEvent);
//                command.setKey("add");
//                command.printOutput(newEvent);
//                break;
//            case "list":
//                command.printOutput();
//                break;
//            case "delete":
//                try {
//                    Tasks toDelete = TaskList.getTaskList().get(Integer.parseInt(userInput[1]) - 1);
//                    command.setKey("delete");
//                    command.printOutput(toDelete);
//                    TaskList.deleteFromList(Integer.parseInt(userInput[1]) - 1);
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Delete failed. No items in list.");
//                }
//                break;
//            default:
//                System.out.println("Try again!");
//            }
        Ui.sayBye();
    }
}
