import java.io.IOException;
import java.util.ArrayList;

/**
 * deals with making sense of the user command
 */
public class Parser {
    Ui ui;
    Storage storage;
    TaskList taskList;

    static final int SINGLE_ARG = 2;

    /**
     * Constructor for a parser.
     *
     * @param ui       deals with interactions with the user
     * @param storage  deals with loading tasks from the file and saving tasks in the file
     * @param taskList contains the task list of the user
     */
    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Continue receiving user inputs and runs the parser
     * as long as the user does not input "bye"
     * Outputs farewell message if user inputs "bye"
     *
     * @param tasks the list storing the user's tasks
     * @param line  the user input
     */
    public static void run(TaskList tasks, String line) {
        while (!line.equals("bye")) {
            if (line.length() == 0) {
                line = Ui.requestUserInput();
                continue;
            }
            Parser.prepareInput(tasks, line);
            line = Ui.requestUserInput();
        }
        Ui.showFarewellMessage();
    }

    /**
     * Splits the line of user input into a list of words so
     * that the command and arguments can be easily processed.
     * Then process the input
     *
     * @param tasks the list storing the user's tasks
     * @param line  the user input
     */
    public static void prepareInput(TaskList tasks, String line) {
        String[] wordList = line.split(" ");
        String command = wordList[0];
        try {
            Parser.processInput(line, tasks, wordList, command);
        } catch (DukeException | IOException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Adds the task into the list containing the user
     * tasks and prints confirmation message
     *
     * @param tasks the list storing the user's tasks
     * @param task  the task to be added
     */
    public static void addTask(TaskList tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list\n", tasks.size());
    }

    /**
     * Marks the task indicated by the second argument of the user
     * input as done
     *
     * @param tasks    the list storing the user's tasks
     * @param wordList the line of user input stored as an array of strings
     * @throws DukeException Thrown when number of arguments is incorrect
     */
    private static void markTask(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != SINGLE_ARG) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for mark");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            tasks.get(index).setDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Unmarks the task indicated by the second argument of the user
     * input as not done
     *
     * @param tasks    the list storing the user's tasks
     * @param wordList the line of user input stored as an array of strings
     * @throws DukeException Thrown when number of arguments is incorrect
     */

    private static void unmarkTask(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != SINGLE_ARG) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for unmark");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            tasks.get(index).setDone(false);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index));
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Interprets the user input and adds an event task with its
     * corresponding description, start and end time to the task list
     *
     * @param line  the user input
     * @param tasks the list storing the user's tasks
     * @throws DukeException thrown when the event description is empty
     */

    private static void createEvent(String line, TaskList tasks) throws DukeException {
        if (line.length() <= 6 || line.substring(6).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        try {
            line = line.substring(6);
            String description = line.split(" /from ")[0];
            String eventTime = line.split(" /from ")[1];
            String startTime = eventTime.split(" /to ")[0];
            String endTime = eventTime.split(" /to ")[1];
            Event eventTask = new Event(description, startTime, endTime);
            addTask(tasks, eventTask);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Interprets the user input and adds a to-do task with
     * its corresponding description into the task list
     *
     * @param line  the user input
     * @param tasks the list storing the user's tasks
     * @throws DukeException thrown when the to-do description is empty
     */

    private static void createTodo(String line, TaskList tasks) throws DukeException {
        if (line.length() <= 5 || line.substring(5).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = line.substring(5);
        Todo todoTask = new Todo(description);
        addTask(tasks, todoTask);
    }

    /**
     * Interprets the user input and adds a deadline task
     * with its corresponding deadline and
     * description into the task list
     *
     * @param line  the user input
     * @param tasks the list storing the user's tasks
     * @throws DukeException thrown when the deadline description is empty
     */

    private static void createDeadline(String line, TaskList tasks) throws DukeException {
        if (line.length() <= 9 || line.substring(9).isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        try {
            line = line.substring(9);
            String description = line.split(" /by ")[0];
            String by = line.split(" /by ")[1];
            Deadline deadlineTask = new Deadline(description, by);
            addTask(tasks, deadlineTask);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Prints out all the tasks the user currently have in
     * their list
     *
     * @param tasks    the list storing the user's tasks
     * @param wordList the line of user input stored as an array of strings
     * @throws DukeException
     */

    private static void printList(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length > 1) {
            throw new DukeException("☹ OOPS!!! Too much arguments for list");
        }
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i += 1) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i));
            }
        } else {
            System.out.println("There is no tasks");
        }
    }

    /**
     * Deletes the task indicated by the second argument of the user
     *
     * @param tasks    the list storing the user's tasks
     * @param wordList the line of user input stored as an array of strings
     * @throws DukeException thrown when number of arguments is incorrect
     */

    private static void deleteTask(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != SINGLE_ARG) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for delete");
        }
        try {
            int index = Integer.parseInt(wordList[1]) - 1;
            if (index + 1 > tasks.size()) {
                throw new DukeException("☹ OOPS!!! index out of bounds");
            } else {
                System.out.println("Noted. I've removed this task:");
                System.out.println(tasks.get(index));
                tasks.remove(index);
                System.out.printf("Now you have %d tasks in the list\n", tasks.size());
            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    /**
     * Filter the task list to print out tasks corresponding to
     * a keyword input by the user.
     *
     * @param tasks    the list storing the users tasks
     * @param wordList the line of user input stored as an array of strings
     * @throws DukeException thrown when number of arguments is wrong
     */
    private static void findTask(TaskList tasks, String[] wordList) throws DukeException {
        if (wordList.length != SINGLE_ARG) {
            throw new DukeException("☹ OOPS!!! Wrong number of arguments for find");
        }
        String keyword = wordList[1];
        ArrayList<Task> filteredList = tasks.filterByKeyword(keyword);

        if (filteredList.size() > 0) {
            System.out.println("Here are the matching tasks in your list:");

            for (int i = 0; i < filteredList.size(); i += 1) {
                System.out.println(i + 1 + ": " + filteredList.get(i));
            }
        }
    }

    /**
     * interprets the user input and performs the corresponding actions,
     * if possible, according to the command inputted by the user
     *
     * @param line     the user input
     * @param tasks    the list storing the user's tasks
     * @param wordList the line of user input stored as an array of strings
     * @param command  The command to be performed
     * @throws DukeException thrown when the command cannot be interpreted
     * @throws IOException   Thrown when file system encounters an error.
     */
    public static void processInput(String line, TaskList tasks, String[] wordList, String command) throws
            DukeException, IOException {
        switch (command) {
        case "list":
            printList(tasks, wordList);
            break;
        case "unmark":
            unmarkTask(tasks, wordList);
            Storage.save(tasks);
            break;
        case "mark":
            markTask(tasks, wordList);
            Storage.save(tasks);
            break;
        case "deadline":
            createDeadline(line, tasks);
            Storage.save(tasks);
            break;
        case "todo":
            createTodo(line, tasks);
            Storage.save(tasks);
            break;
        case "event":
            createEvent(line, tasks);
            Storage.save(tasks);
            break;
        case "delete":
            deleteTask(tasks, wordList);
            Storage.save(tasks);
            break;
        case "find":
            findTask(tasks, wordList);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
