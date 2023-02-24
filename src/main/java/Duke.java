import java.util.Scanner;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import messages.ErrorMessages;
import messages.OperationsMessages;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import exceptions.EmptyInputException;
import exceptions.InvalidCommand;

public class Duke {
    // Constants
    private static final String BOT_NAME = "Duke"; 

    private static Storage storage = new Storage();
    private static Ui ui = new Ui();
    private static TaskList tasklist = new TaskList();
    // private static Set<String> markedItems = new HashSet<String>(MAX_ITEMS);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        printResponse(String.format(OperationsMessages.INITIAL_MSG, BOT_NAME));
        Scanner scanner = new Scanner(System.in);
        try {
            storage.load(tasklist);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println(ErrorMessages.LOAD_FAIL);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                handleInput(line);
            } catch (EmptyInputException e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                printResponse(ErrorMessages.EMPTY_INPUT);
            } catch (InvalidCommand e) {
                printResponse(ErrorMessages.INVALID_COMMAND);
            } catch (ArrayIndexOutOfBoundsException e) {
                printResponse(ErrorMessages.INCORRECT_FIELDS);
            } catch (IOException e) {
                printResponse(ErrorMessages.LOAD_FAIL);
            }
        }
        scanner.close();
    }

    private static void handleInput(String line) throws EmptyInputException, InvalidCommand, IOException {
        String[] lineParts = line.split(" ");
        String command = lineParts[0];
        if (command.equals("list")) {
            printItems();
        } else if (command.equals("save")) {
            storage.save(tasklist);
            printResponse(OperationsMessages.SAVED_MSG);
        } else if (command.equals("load")) {
            storage.load(tasklist);
            printResponse(OperationsMessages.LOADED_MSG);
        } else if (lineParts.length <= 1) {
            // dont move on if less than 2 parts
            throw new EmptyInputException();
        } else if (command.equals("bye")) {
            printResponse(OperationsMessages.BYE_MSG);
        } else if (command.equals("todo")) {
            line = line.substring(command.length() + 1);
            createTodo(line);
        } else if (command.equals("delete") || command.equals("remove")) {
            line = line.substring(command.length() + 1);
            removeItem(Integer.parseInt(lineParts[1]));
        } else if (command.equals("deadline")) {
            line = line.substring(command.length() + 1);
            createDeadline(line);
        } else if (command.equals("event")) {
            line = line.substring(command.length() + 1);
            createEvent(line);
        } else if (command.equals("list")) {
            printItems();
        } else if (command.equals("mark")) {
            // String item = line.substring(4); // if want to mark by name
            markItem(Integer.parseInt(lineParts[1]));
        } else if (command.equals("unmark")) {
            // String item = line.substring(6); // if want to unmark by name
            unmarkItem(Integer.parseInt(lineParts[1]));
        } else { // add item
            throw new InvalidCommand();
        }
    }



    private static void createTodo(String line) {
        Todo item = new Todo(line);
        tasklist.addItem(item);
        printResponse(String.format(OperationsMessages.ADDED_MSG, item.toString(), tasklist.size()));
    }

    private static void createDeadline(String line) { 
        String[] lineParts = line.split(" /by ", 2);
        String itemDescription = lineParts[0];
        String itemDeadline = lineParts[1];
        
        Deadline item = new Deadline(itemDescription, itemDeadline);
        tasklist.addItem(item);
        printResponse(String.format(OperationsMessages.ADDED_MSG, item.toString(), tasklist.size()));
    }

    private static void createEvent(String line) {
        String[] lineParts = line.split(" /from | /to ", 3);
        String itemDescription = lineParts[0];
        String itemFrom = lineParts[1];
        String itemTo = lineParts[2];
        
        Event item = new Event(itemDescription, itemFrom, itemTo);
        tasklist.addItem(item);
    }

    private static void markItem(int itemNo) {
        Todo item = tasklist.get(itemNo);
        item.setDone(true);
        printResponse("    " + OperationsMessages.MARK_MSG + "\n" + "    " + item.toString());
    }

    private static void unmarkItem(int itemNo) {
        Todo item = tasklist.get(itemNo);
        item.setDone(false);
        printResponse("    " + OperationsMessages.UNMARK_MSG + "\n" + "    " + item.toString());
    }

    private static void removeItem(int itemIdx) {
        Todo itemToDelete = tasklist.remove(itemIdx);
        printResponse(String.format(OperationsMessages.DELETED_MSG, itemToDelete.toString(), tasklist.size()));
    }

    private static void printItems() {
        String response = OperationsMessages.SHOW_ITEMS_MSG + "\n";
        for (int i = 1; i <= tasklist.size(); i++) {
            Todo item = tasklist.get(i);
            response += String.format("    %d.%s\n", i, item.toString());
        }
        printResponse(response);
    }

    private static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("%s", response));
        System.out.println("    ____________________________________________________________");
    }
}
