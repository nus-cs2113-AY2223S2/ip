import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static Storage database = null;
    private static TaskList taskList = null;
    public static Ui ui;
    private static Parser parser;

    public Duke () {
        ui = new Ui();
        ui.showGreetingMessage();
        try {
            taskList = new TaskList();
            database = new Storage(taskList.listOfTasks);
            parser = new Parser();
            readAndRespond(database, taskList);
        } catch (DukeException e) {
            ui.showDukeInputError();
        } catch (IOException e) {
            ui.showDatabaseLoadingError();
        }
        ui.exitMessage();
    }


    public static void main(String[] args) {
        new Duke();
    }


    /**
     * Keeps repeating Duke functions until user enters terminating command
     * @param database Database containing memory of tasks
     * @throws DukeException
     */
    public void readAndRespond(Storage database, TaskList taskList) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            if (input.equals("list")) {
                TaskList.printList(taskList.listOfTasks);
            } else if (input.isBlank()) {
                throw new DukeException();
            } else if (isValidUnmark(input, words)) {
                TaskList.unmarkTask(taskList.listOfTasks, words, ui);
            } else if (isValidMark(input, words)) {
                TaskList.markTask(taskList.listOfTasks, words);
            } else if (IsValidDelete(input, words)) {
                TaskList.removeTask(taskList.listOfTasks, words, ui);
            } else if (input.startsWith("find") && words.length != 1) {
                TaskList.findTask(taskList, words);
            }
            else if (parser.parseTask(input) != null) {
                Task task = parser.parseTask(input);
                taskList.listOfTasks.add(task);
                String[] taskDetail = getNameAndType(task,input).split("###");
                ui.addedMessage(task);
                addTaskToDatabase(database, taskDetail[0], taskDetail[1] );
            } else {
                System.out.println("Invalid command! Start with todo/event/deadline to add!!");
            }
            input = sc.nextLine();
        }
    }

    /**
     * Retrieves the name and type of the task
     * @param task Task that is being evaluated
     * @param input String that contains the user inputs
     * @return Returns the name and type of the task to be added into database
     */
    private static String getNameAndType (Task task, String input) {
        String type = null;
        String name = null;
        if (task instanceof ToDo) {
            type = "T";
            name = input.substring(5);
        } else if (task instanceof Deadline) {
            type = "D";
            name = input.substring(9);
        } else {
            type = "E";
            name = input.substring(6);
        }
        return name + "###" + type;
    }

    /**
     * Appends string to database txt file
     * @param database Database containing the memory of tasks
     * @param name Name to be inserted into memory of tasks
     * @param type Type of task to be inserted into memory of tasks
     */
    private static void addTaskToDatabase(Storage database, String name, String type) {
        try {
            database.appendToFile(name +  " --- " + type + " |[ ]| " + "\n");
        } catch (IOException e) {
            ui.showAddingTaskToDatabaseError();
        }
    }

    private static boolean IsValidDelete(String input, String[] words) {
        return input.startsWith("delete") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidMark(String input, String[] words) {
        return input.contains("mark") && words.length == 2 && isInt(words[1]);
    }

    private static boolean isValidUnmark(String input, String[] words) {
        return input.contains("unmark") && words.length == 2 && isInt(words[1]);
    }

    /**
     * Determines if the string is an integer.
     * @param str Str is the string we are checking
     * @return True if str is an integer, false otherwise
     * @catch NumberFormatException If str cannot be converted into integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

}
