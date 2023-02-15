import duke.command.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;


public class Duke {
    /**Constant to store location of the file used for writing/storing of data.*/
    public static final String FILE_LOCATION = "duke.txt";

    /**
     * Executes the "Duke" program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        //Initialisation of ArrayList and taking of inputs.
        String line = "start";
        ArrayList<Task> list = new ArrayList<>();
        int listSize = 0;
        try {
            listSize = readFileData(list);
        } catch (IOException ie) {
            System.out.println("Failed to find or create a file.");
        }

        Scanner input = new Scanner(System.in);
        greetUser();

        //Get commands while user input is not "bye", handles errors due to user input.
        obtainUserInputs(line, list, listSize, input);

        //Transfer final ArrayList back into text file.
        try {
            writeFileData(list);
        } catch (IOException ie) {
            System.out.println("Unable to save tasks into database. Did you create a .txt file?");
        }
    }

    /**
     * Obtains user inputs and push them into another function to process what the user has typed.
     * Stores or deletes tasks that are stores in the ArrayList "list", according to what the user
     * has typed.
     *
     * @param line The single line of string inputted by the user.
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param input The user input typed in through the command prompt.
     */
    private static void obtainUserInputs(String line, ArrayList<Task> list, int listSize, Scanner input) {
        while (!line.equals("bye")) {
            line = input.nextLine();
            try {
                listSize = handleUserInputs(line, list, listSize);
            } catch (DukeException de) {
                handleException(line);
            }
        }
    }

    /**
     * Writes information stored on the ArrayList "list" into the .txt file, effectively saving the information
     * contained in the "list" for future use.
     *
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @throws IOException Exception that is generated due to errors in input or output.
     */
    private static void writeFileData(ArrayList<Task> list) throws IOException{
        FileWriter fileRewriter = new FileWriter(FILE_LOCATION);
        for (Task task : list) {
            String taskType = getTaskType(task);
            fileRewriter.write(taskType + "/" + task.getBooleanValueOfStatus() + "/" + task.getDescription());
            if (taskType.equals("D")) {
                fileRewriter.write("/" + ((Deadline) task).getBy());
            } else if (taskType.equals("E")) {
                fileRewriter.write("/" + ((Event) task).getFrom() + "/" + ((Event) task).getTo());
            }
            fileRewriter.write(System.lineSeparator());
        }
        fileRewriter.close();
    }

    /**
     * Retrieves the class of the object (ToDo, Event, Deadline etc.)
     * Generates a single-letter string to be used for writing to the .txt file.
     *
     * @param task A specific task found in the ArrayList "list".
     * @return A single-letter string representing the different class types (e.g. T,D,E)
     */
    private static String getTaskType(Task task) {
        if (task.getClass().toString().equals("class duke.task.ToDo")) {
            return "T";
        }
        if (task.getClass().toString().equals("class duke.task.Deadline")) {
            return "D";
        }
        return "E";
    }

    /**
     * Reads the strings that are stored in the .txt file and converts them into editable data
     * within the program by storing them on an ArrayList.
     * @param list The ArrayList used to store all the information found in the .txt file.
     * @return Returns the final size of the ArrayList "list" after populating the ArrayList.
     * @throws IOException Exception related to all input and output errors.
     */
    private static int readFileData(ArrayList<Task> list) throws IOException {
        File database = new File(FILE_LOCATION);
        if (database.exists()) {
            Scanner fileReader = new Scanner(database);
            while (fileReader.hasNext()) {
                String fileLine = fileReader.nextLine();
                String[] fileData = fileLine.split("/");
                uploadDataToArrayList(fileData, list);
            }
            lineSeparator();
            System.out.println("File found! Loading file from: " + database.getAbsolutePath());
            System.out.println("You currently have " + list.size() + " tasks in your database.");
            lineSeparator();
        } else {
            printMissingFileError();
            File newFile = new File("docs");
            newFile.mkdirs();
            database.createNewFile();
        }
        return list.size();
    }

    /**
     * Processes what is written in the first string of each line of the .txt file and decides
     * what is to be written into the ArrayList for storage.
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private static void uploadDataToArrayList(String[] fileData, ArrayList<Task> list) {
        switch (fileData[0]) {
        case "T":
            transferToDo(fileData, list);
            break;
        case "D":
            transferDeadline(fileData, list);
            break;
        case "E":
            transferEvent(fileData, list);
            break;
        }
    }

    /**
     * If line found in .txt file is determined to be a task of type "Event", this method
     * will be executed to populate the ArrayList "list".
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private static void transferEvent(String[] fileData, ArrayList<Task> list) {
        Event newEventTask = new Event(fileData[2], fileData[3], fileData[4]);
        if (fileData[1].equals("1")) {
            newEventTask.markDone();
        }
        list.add(newEventTask);
    }

    /**
     * If line found in .txt file is determined to be a task of type "Deadline", this method
     * will be executed to populate the ArrayList "list".
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private static void transferDeadline(String[] fileData, ArrayList<Task> list) {
        Deadline newDeadlineTask = new Deadline (fileData[2], fileData[3]);
        if (fileData[1].equals("1")) {
            newDeadlineTask.markDone();
        }
        list.add(newDeadlineTask);
    }

    /**
     * If line found in .txt file is determined to be a task of type "ToDo", this method
     * will be executed to populate the ArrayList "list".
     *
     * @param fileData An array of strings that have been split into different indexes for arraylist
     *                 initialization.
     * @param list The ArrayList to be populated with data coming from the .txt file.
     */
    private static void transferToDo(String[] fileData, ArrayList<Task> list) {
         ToDo newToDo = new ToDo(fileData[2]);
         if (fileData[1].equals("1")) {
             newToDo.markDone();
         }
         list.add(newToDo);
    }

    /**
     * Returns the number of tasks found in the list, after handling the commands given by the
     * user in the form of a line of string.
     *
     * @param line     The single line of string inputted by the user.
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @return Returns the current size of the list populated by tasks created by the user.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static int handleUserInputs(String line, ArrayList<Task> list, int listSize) throws DukeException {
        String[] command = line.split(" ");
        //Check the first word in the line of strings (list, mark, unmark, event, etc.)
        switch (command[0]) {
        case "list":
            printList(list, listSize, command);
            break;
        case "mark":
            markAsDone(list, listSize, command);
            break;
        case "unmark":
            markAsUndone(list, listSize, command);
            break;
        case "delete":
            listSize = printNewlyRemovedTask(list, listSize, command);
            break;
        case "todo":
            if (command.length == 1) {
                throw new DukeException();
            }
            list.add(new ToDo(line.substring(line.indexOf(' ') + 1)));
            listSize = printNewlyAddedTask(list, listSize);
            break;
        case "deadline":
            createNewDeadline(line, list);
            listSize = printNewlyAddedTask(list, listSize);
            break;
        case "event":
            createNewEvent(line, list);
            listSize = printNewlyAddedTask(list, listSize);
            break;
        case "bye":
            goodbyeMessage(command);
            break;
        default:
            throw new DukeException();
        }
        return listSize;
    }

    /**
     * Prints the "list" array, which contains a series of user-created tasks. The tasks will be printed
     * in entry order, starting from the task that was entered into the list first.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void printList(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 1) {
            lineSeparator();
            for (int increment = 0; increment < listSize; increment += 1) {
                System.out.println((increment + 1) + ". " + list.get(increment).toString());
            }
            lineSeparator();
        } else {
            throw new DukeException();
        }
    }

    /**
     * Creates a new "deadline" task in the "list" array by determining the position of the "/by
     * string in the entire string, in order to retrieve the strings representing the name/description
     * of the "deadline" task and the due date of the task.
     *
     * @param line     The single line of string inputted by the user.
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void createNewDeadline(String line, ArrayList<Task> list) throws DukeException {
        int byIndex = line.indexOf("/by");
        if (byIndex == -1) {
            throw new DukeException();
        }
        String deadline = line.substring(line.indexOf("deadline") + 9, byIndex - 1);
        list.add(new Deadline(deadline, line.substring(byIndex + 4)));
    }

    /**
     * Creates a new "event" task in the "list" array by determining the position of the "/from" and "/to"
     * strings in the entire string, in order to retrieve the strings representing the name/description (event)
     * of the event, the starting date (startDate) and ending date (endDate).
     *
     * @param line     The single line of string inputted by the user.
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void createNewEvent(String line, ArrayList<Task> list) throws DukeException {
        int fromIndex = line.indexOf("/from");
        int toIndex = line.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new DukeException();
        }
        String event;
        String startDate;
        String endDate;
        if (fromIndex < toIndex) {
            //If user typed "/from" before "/to".
            event = line.substring(line.indexOf("event") + 5, fromIndex - 1);
            startDate = line.substring(fromIndex + 6, toIndex - 1);
            endDate = line.substring(toIndex + 4);
        } else {
            //If user typed "/to" before "/from".
            event = line.substring(line.indexOf("event") + 5, toIndex - 1);
            startDate = line.substring(toIndex + 4, fromIndex - 1);
            endDate = line.substring(fromIndex + 6);
        }
        list.add(new Event(event, startDate, endDate));
    }

    /**
     * Prints a series of strings to inform the user that the task has been added to the list of tasks, while
     * incrementing the "listSize" variable used to track the total tasks in the list by one.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @return Returns the new total number of tasks found in the list.
     */
    private static int printNewlyAddedTask(ArrayList<Task> list, int listSize) {
        lineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.get(listSize));
        listSize += 1;
        System.out.println("Now you have " + listSize + " tasks in the list.");
        lineSeparator();
        return listSize;
    }

    /**
     * Removes one task from the list of tasks based on the index number of the task inputted by the user, and
     * informs the user of the removal of the selected task from the list.
     *
     * @param list The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command The string array containing all individual strings separated by a space (" ") character in
     *                the user inputted string.
     * @return Returns the size of the "list" ArrayList after the removal of one task from the list.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static int printNewlyRemovedTask(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                lineSeparator();
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + list.get(taskNumber - 1).toString());
                listSize -= 1;
                System.out.println("Now you have " + listSize + " tasks in the list.");
                lineSeparator();
                list.remove(taskNumber - 1);
            } else {
                //If task number given exceeds total tasks.
                throw new DukeException();
            }
        } else {
            throw new DukeException();
        }
        return listSize;
    }

    /**
     * Changes the completion status of the user-created task to done, and prints a series of strings
     * to inform the user about the update to the task completion status.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void markAsDone(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            //If user wants to mark task as done and second input after "mark" is a valid number.
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                list.get(taskNumber - 1).markDone();
                lineSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + list.get(taskNumber - 1).toString());
                lineSeparator();
            } else {
                //If task number given exceeds total tasks.
                throw new DukeException();
            }
        } else {
            //If user types non-integer inputs after "mark".
            throw new DukeException();
        }
    }

    /**
     * Changes the completion status of the user-created task to NOT done, and prints a series of strings
     * to inform the user about the update to the task completion status.
     *
     * @param list     The ArrayList containing all information about existing tasks and their completion status.
     * @param listSize The current number of tasks populating the "list" array.
     * @param command  The string array containing all individual strings separated by a space (" ") character in
     *                 the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void markAsUndone(ArrayList<Task> list, int listSize, String[] command) throws DukeException {
        if (command.length == 2 && isNumeric(command[1])) {
            //If user wants to mark task as not done and second input after "unmark" is a valid number.
            int taskNumber = Integer.parseInt(command[1]);
            if (taskNumber > 0 && taskNumber <= listSize) {
                //If valid task number is given.
                list.get(taskNumber - 1).unmarkDone();
                lineSeparator();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + list.get(taskNumber - 1).toString());
                lineSeparator();
            } else {
                //If task number given exceeds total tasks in the list.
                throw new DukeException();
            }
        } else {
            //If user types non-integer inputs after "unmark".
            throw new DukeException();
        }
    }

    /**
     * Prints a string to inform the user that the program is terminating.
     *
     * @param command The string array containing all individual strings separated by a space (" ") character in
     *                the user inputted string.
     * @throws DukeException The custom defined exception relating to all errors specific to the Duke program.
     */
    private static void goodbyeMessage(String[] command) throws DukeException {
        if (command.length == 1) {
            lineSeparator();
            System.out.println("Bye. Hope to see you again soon!");
            lineSeparator();
        } else {
            //If 'bye' is not the only thing typed.
            throw new DukeException();
        }
    }

    /**
     * Prints a series of error message strings depending on the wrong or partially wrong input by the user.
     *
     * @param line The single line of string inputted by the user.
     */
    private static void handleException(String line) {
        String[] command = line.split(" ");
        switch (command[0]) {
        case "list":
            lineSeparator();
            System.out.println("OOPS!!! A 'list' command cannot contain a second word!");
            lineSeparator();
            break;
        case "todo":
            lineSeparator();
            System.out.println("OOPS!!! A description of a 'todo' cannot be empty. Please follow this format below:");
            System.out.println("    todo <INSERT TASK NAME>");
            lineSeparator();
            break;
        case "event":
            lineSeparator();
            System.out.println("OOPS!!! Did you declare your 'event' properly? Please follow this format below:");
            System.out.println("    event <INSERT TASK NAME> /from <INSERT START DATE> /to <INSERT END DATE>");
            lineSeparator();
            break;
        case "deadline":
            lineSeparator();
            System.out.println("OOPS!!! Did you declare your 'deadline' properly? Please follow this format below:");
            System.out.println("    deadline <INSERT TASK NAME> /by <INSERT END DATE>");
            lineSeparator();
            break;
        case "mark":
        case "unmark":
        case "delete":
            printInvalidInputError();
            break;
        default:
            printInvalidCommandError();
            break;
        }
    }

    /**
     * Prints a string to inform the user about an invalid command entered as the input.
     */
    private static void printInvalidCommandError() {
        lineSeparator();
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        lineSeparator();
    }

    /**
     * Prints a string to inform the user to retype a valid task number.
     */
    private static void printInvalidInputError() {
        lineSeparator();
        System.out.println("OOPS!!! Please key in a valid task number!");
        lineSeparator();
    }

    /**
     * Prints a string to inform the user that file used to retrieve and store data does not
     * exist, and that the program will attempt to create a file for this exact purpose.
     */
    private static void printMissingFileError() {
        lineSeparator();
        System.out.println("File cannot be found! Creating new file for data storage...");
        lineSeparator();
    }

    /**
     * Generates strings to greet the user and welcome them to the program.
     */
    private static void greetUser() {
        lineSeparator();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        lineSeparator();
    }

    /**
     * Prints a long string of lines ("___") to separate the outputs.
     */
    public static void lineSeparator() {
        System.out.println("___________________________________________________________________________________");
    }

    /**
     * Returns false if the second string in the input cannot be converted into a numeric type, true if it
     * can be converted.
     *
     * @param strNum The second string found in the user input.
     * @return Boolean for whether string can be converted into a numeric type (int, double etc.).
     */
    //@@author ngkaiwen123-reused
    //Reused from https://www.baeldung.com/java-check-string-number
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    //@@author
}
