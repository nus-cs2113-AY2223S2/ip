import java.util.Scanner;
import messages.ErrorMessages;
import messages.OperationsMessages;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import java.util.ArrayList;
import exceptions.EmptyInputException;
import exceptions.InvalidCommand;

public class Duke {
    // Messages
    private static final String BOT_NAME = "Duke";    // Constants 
    private static ArrayList<Todo> itemList = new ArrayList<Todo>(0);
    // private static Set<String> markedItems = new HashSet<String>(MAX_ITEMS);
    private static int numItems = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printResponse(String.format(OperationsMessages.INITIAL_MSG, BOT_NAME));
        Scanner scanner = new Scanner(System.in);
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
            }
        }
        scanner.close();
    }

    private static void handleInput(String line) throws EmptyInputException, InvalidCommand {
        String[] lineParts = line.split(" ");
        String command = lineParts[0];
        if (!command.equals("list") && lineParts.length <= 1) {
            throw new EmptyInputException();
        }
        if (command.equals("bye")) {
            printResponse(OperationsMessages.BYE_MSG);
        } else if (command.equals("todo")) {
            line = line.substring(command.length() + 1);
            addItem(new Todo(line));
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

    private static void createDeadline(String line) {
        
        String[] lineParts = line.split(" /by ", 2);
        String itemDescription = lineParts[0];
        String itemDeadline = lineParts[1];

        addItem(new Deadline(itemDescription, itemDeadline));
    }

    private static void createEvent(String line) {
        
        String[] lineParts = line.split(" /from | /to ", 3);
        String itemDescription = lineParts[0];
        String itemFrom = lineParts[1];
        String itemTo = lineParts[2];
        
        addItem(new Event(itemDescription, itemFrom, itemTo));
    }

    private static void markItem(int itemNo) {
        Todo item = itemList.get(itemNo - 1);
        item.setDone(true);
        printResponse("    " + OperationsMessages.MARK_MSG + "\n" + "    " + item.toString());
    }

    private static void unmarkItem(int itemNo) {
        Todo item = itemList.get(itemNo - 1);
        item.setDone(false);
        printResponse("    " + OperationsMessages.UNMARK_MSG + "\n" + "    " + item.toString());
    }

    private static void addItem(Todo item) {
        itemList.add(item);
        printResponse(String.format(OperationsMessages.ADDED_MSG, item.toString(), itemList.size()));
    }

    private static void removeItem(int itemIdx) {
        itemIdx--;
        Todo itemToDelete = itemList.get(itemIdx);
        printResponse(String.format(OperationsMessages.DELETED_MSG, itemToDelete.toString(), itemList.size()));
        itemList.remove(itemIdx);
    }

    private static void printItems() {
        String response = OperationsMessages.SHOW_ITEMS_MSG + "\n";
        for (int i = 0; i < itemList.size(); i++) {
            Todo item = itemList.get(i);
            response += String.format("    %d.%s\n", i+1, item.toString());
        }
        printResponse(response);
    }

    private static void printResponse(String response) {
        System.out.println("    ____________________________________________________________");
        System.out.println(String.format("%s", response));
        System.out.println("    ____________________________________________________________");
    }
}
