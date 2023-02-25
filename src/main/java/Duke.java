import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
public class Duke {
    public static void main(String[] args) {
        new Duke("DUKE/Duke.txt");
    }
    private Memory memory;
    private IO io;
    private TaskList tasks;
    public Duke(String filepath) {
        memory = new Memory(filepath);
        io = new IO();
        tasks = new TaskList();
        Scanner in = new Scanner(System.in);
        io.printWelcome();
        try {
            storeTasksInList(memory.loadFile());
        } catch (IOException e) {
            io.showLoadingError();
        }
        while (true) {
            String userInput = in.nextLine();
            processUserInput(userInput, true);
        }
    }
    public void storeTasksInList(ArrayList<String> inputCommands) throws IOException {
        for (String s : inputCommands) {
            processUserInput(s, false);
        }
    }
    public String[] splitActionAndDescription(String userInput) {
        String[] actionAndDescription = userInput.split(" ", 2);
        return actionAndDescription.length == 2 ? actionAndDescription : new String[]{actionAndDescription[0], ""};
    }
    private void processUserInput(String userInput, boolean fileDoesExist) {
        String[] actionAndDescription = splitActionAndDescription(userInput);
        String action = actionAndDescription[0];
        String description = actionAndDescription[1];
        try {
            switch (action) {
            case "bye":
                io.printBye();
                break;
            case "list":
                executeListAction(fileDoesExist);
                break;
            case "mark":
                executeMarkAction(description, fileDoesExist);
                if (fileDoesExist) {
                    memory.writeToFile();
                }
                break;
            case "unmark":
                executeUnmarkAction(description, fileDoesExist);
                if (fileDoesExist) {
                    memory.writeToFile();
                }
                break;
            case "todo":
                executeTodoAction(description, fileDoesExist);
                if (fileDoesExist) {
                    memory.appendToFile(userInput);
                }
                break;
            case "event":
                executeEventAction(description, fileDoesExist);
                if (fileDoesExist) {
                    memory.appendToFile(userInput);
                }
                break;
            case "deadline":
                executeDeadlineAction(description, fileDoesExist);
                if (fileDoesExist) {
                    memory.appendToFile(userInput);
                }
                break;
            case "delete":
                executeDeleteAction(description);
                break;
            default:
                io.showInvalidCommand();
            }
        } catch (IOException e) {
            io.showCannotEditFile();
        }
    }
    private void executeListAction(boolean fileDoesExist) {
        if (fileDoesExist) {
            io.printList(tasks.getTasks());
        }
    }
    private void executeMarkAction(String description, boolean fileDoesExist) {
        int index = Integer.parseInt(description) - 1;
        tasks.markAtIndex(index);
        if (fileDoesExist) {
            io.printMarked(tasks.getTasks(), index);
        }
    }
    private void executeUnmarkAction(String description, boolean fileDoesExist) {
        int index = Integer.parseInt(description) - 1;
        tasks.unmarkAtIndex(Integer.parseInt(description) - 1);
        if (fileDoesExist) {
           io.printUnmarked(tasks.getTasks(), index);
        }
    }
    private void executeTodoAction(String description, boolean fileDoesExist) {
        try {
            tasks.addTodoToList(description);
            if (fileDoesExist) {
                io.printAcknowledgement(tasks.getTasks());
            }
        } catch (Exception e) {
            io.showInsufficientTodo();
        }
    }
    private void executeEventAction(String description, boolean fileDoesExist) {
        if (fileDoesExist) {
            try {
                String[] indexArr = splitDescriptionEvent(description);
                String name = indexArr[0].trim();
                String fromDate = indexArr[1].substring(5).trim();
                String toDate = indexArr[2].substring(3).trim();
                tasks.addEventToList(name, fromDate, toDate);
                if (fileDoesExist) {
                    io.printAcknowledgement(tasks.getTasks());
                }
            } catch (DukeException e) {
                io.showInsufficientEvent();
            }
        }
    }
    private static String[] splitDescriptionEvent(String description) throws DukeException {
        String[] indexArr = description.split("/", 3);
        if (indexArr.length < 3){
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }
    private void executeDeadlineAction(String description, boolean fileDoesExist) {
        try {
            String[] indexArr = splitDescriptionDeadline(description);
            String name = indexArr[0].trim();
            String byDate = indexArr[1].substring(3).trim();
            tasks.addDeadlineToList(name, byDate);
            if (fileDoesExist) {
                io.printAcknowledgement(tasks.getTasks());
            }
        } catch(DukeException e) {
            io.showInsufficientDeadline();
        }
    }
    private static String[] splitDescriptionDeadline(String description) throws DukeException {
        String[] indexArr = description.split("/", 2);
        if (indexArr.length < 2) {
            throw new DukeException(new IllegalArgumentException());
        }
        return indexArr;
    }
    private void executeDeleteAction(String description) {
        Integer index = Integer.parseInt(description) - 1;
        String deletedTask = tasks.deleteTaskAtIndex(index);
        io.printDeleted(tasks.getTasks(), deletedTask);
    }
}