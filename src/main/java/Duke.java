import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import duke.models.Deadline;
import duke.models.Event;
import duke.models.Item;
import duke.models.Remind;

import duke.utils.Command;
import duke.utils.Message;
import duke.utils.RuntimeTypeAdapterFactory;

import duke.exceptions.DukeException;

public class Duke {
    private static ArrayList<Item> items = new ArrayList<Item>(); // List of items

    public static void main(String[] args) {
        try {
            getFile();
        } catch (Exception err) {
            System.out.println(err.getMessage());
            exitProgram();
        }
        
        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String input = in.nextLine();
            System.out.println(Message.LINE);

            try {
                Command command = getCommand(input);
                String parameters = getParameters(input);

                switch(command) {
                case LIST:
                    printList();
                    break;
                case MARK:
                    markItem(parameters, true);
                    break;
                case UNMARK:
                    markItem(parameters, false);
                    break;
                case REMIND:
                    addRemind(parameters);
                    break;
                case DEADLINE:
                    addDeadline(parameters);
                    break;
                case EVENT:
                    addEvent(parameters);
                    break;
                case EXIT:
                    in.close();
                    exportItems();
                    exitProgram();
                    break;
                default:
                    throw new DukeException(Message.ERROR_INVALID_COMMAND.toString());
                }
            } catch (Exception err) {
                System.out.println(err.getMessage());
            } finally {
                System.out.println(Message.LINE);
            }
        }
    }

    /**
     * Retrieve the command from the user's entered input.
     * Invalid command will return an error.
     * 
     * @param input the user inputted text
     * @return command specified by the user
     * @throws DukeException when an invalid command is entered
     */
    private static Command getCommand(String input) throws DukeException {
        try {
            String[] splitInput = input.split(" ", 2);
            Command command = Command.valueOf(splitInput[0].toUpperCase());
            return command;
        } catch (IllegalArgumentException err) {
            throw new DukeException(Message.ERROR_INVALID_COMMAND.toString());
        }
    }

    /**
     * Retrieve the parameters from the user's entered input.
     * If there is no parameters specified, return empty string.
     * 
     * @param input the user inputted text
     * @return parameters specified by the user
     */
    private static String getParameters(String input) {
        if (input.contains(" ")) {
            return input.split(" ", 2)[1];
        }

        return "";
    }

    /**
     * Checks for empty/whitespace parameters input by the user.
     * Throws an IllegalArgumentException if check fails.
     * 
     * @param input the user inputted text
     * @return parameters specified by the user
     * @throws IllegalArgumentException when check fails
     */
    private static void areValidParameters(String[] parameters) throws IllegalArgumentException {
        for (String parameter : parameters) {
            if (parameter.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Prints the list of items.
     */
    private static void printList() {
        System.out.println(Message.INFO_LIST);
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i+1) + ". " + item);
        }
    }

    /**
     * Marks an item in the list as done/not done based on the item no.
     * If item no. is cannot be converted to int or invalid, returns an error.
     * 
     * @param parameters Find the item no. specified
     * @param isMark to mark/unmark the item in the list
     * @throws DukeException when parseInt fails or User tries to access element that is out of bounds
     */
    private static void markItem(String parameters, boolean isMark) throws DukeException {
        try {
            int num = Integer.parseInt(parameters);
            Item item = items.get(num - 1);
            item.setStatus(isMark);

            System.out.println(isMark ? Message.INFO_MARK : Message.INFO_UNMARK);
            System.out.println(item);
        } catch (NumberFormatException err) {
            throw new DukeException(Message.ERROR_MARK_INVALID_PARAMETER.toString());
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(Message.ERROR_MARK_OUT_OF_BOUNDS.toString());
        }
    }

    /**
     * Adds a Todo item into the list.
     * 
     * @param parameters Gets the item description
     * @throws DukeException when areValidParameters check fails
     */
    private static void addRemind(String parameters) throws Exception {
        try {
            areValidParameters(new String[] {parameters});

            Item newTodo = new Remind(parameters);
            items.add(newTodo);

            printAddItemMessage(newTodo);
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_REMIND_MISSING_PARAMETER.toString());
        }
    }

    /**
     * Adds a Deadline item into the list.
     * 
     * @param parameters Gets the description and deadline of the item.
     * @throws DukeException when there are missing parameters or areValidParameters check fails
     */
    private static void addDeadline(String parameters) throws Exception {
        try {
            String[] attributes = parameters.split(" /by ", 2);
            String description = attributes[0];
            String by = attributes[1];

            areValidParameters(new String[] {description, by});

            Item newDeadline = new Deadline(description, by);
            items.add(newDeadline);

            printAddItemMessage(newDeadline);
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_DEADLINE_MISSING_PARAMETER.toString());
        }
    }

    /**
     * Adds a Event item into the list.
     * 
     * @param parameters Gets the description and from/to of the item.
     * @throws DukeException when there are missing parameters or areValidParameters check fails
     */
    private static void addEvent(String parameters) throws Exception {
        try {
            String[] attributes = parameters.split(" /from ", 2);
            String description = attributes[0];
            
            attributes = attributes[1].split(" /to ", 2);
            String from = attributes[0];
            String to = attributes[1];

            areValidParameters(new String[] {description, from, to});

            Item newEvent = new Event(description, from, to);
            items.add(newEvent);

            printAddItemMessage(newEvent);
        } catch (Exception err) {
            throw new DukeException(Message.ERROR_EVENT_MISSING_PARAMETER.toString());
        }
    }

    /**
     * Prints the welcome message when application is launched.
     */
    private static void printWelcomeMessage() {
        System.out.println(Message.LINE);
        System.out.println(Message.INFO_WELCOME);
        System.out.println(Message.LINE);
    }

    /**
     * Prints the message after an item is added.
     * 
     * @param item Prints the description and status of the item
     */
    private static void printAddItemMessage(Item item) {
        System.out.println(Message.INFO_ITEM_ADD);
        System.out.println(item);
        System.out.printf(Message.INFO_ITEM_COUNT.toString(), items.size());
    }

    /**
     * Check if the file exists and returns the File object. 
     * 
     */
    private static void getFile() throws DukeException {
        File file = new File("items.txt");
        if (file.exists()) {
            importItems(file);
        }
    }

    /**
     * Imports the file and put into the list
     * 
     * @param file File object that retrieves the file location
     */
    private static void importItems(File file) throws DukeException {
        try(FileReader fileReader = new FileReader(file)) {
            RuntimeTypeAdapterFactory<Item> itemAdapterFactory = RuntimeTypeAdapterFactory.of(Item.class, "type")
                .registerSubtype(Remind.class)
                .registerSubtype(Deadline.class)
                .registerSubtype(Event.class);

            Gson gson = new GsonBuilder().registerTypeAdapterFactory(itemAdapterFactory).create();
            items = gson.fromJson(fileReader, new TypeToken<ArrayList<Item>>(){}.getType());
            fileReader.close();
        } catch (IOException err) {
            throw new DukeException(Message.ERROR_FILE_IO.toString());
        }
    }

    /**
     * Exports the list into a file
     * 
     */
    private static void exportItems() {
        try {
            Gson gson = new Gson();
            FileWriter fw = new FileWriter("items.txt");
            String jsonString = gson.toJson(items);
            fw.write(jsonString);
            fw.flush();
            fw.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Prints a bye message and exits the application.
     */
    private static void exitProgram() {
        System.out.println(Message.INFO_EXIT);
        System.exit(0);
    }
}
