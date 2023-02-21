import java.util.Scanner;

public class Ui {
    public static final String MISSING_FIND_KEYWORD_STRING = "Missing find keyword.";
    public static final String LINE = "____________________________________________________________";
    public static final String INVALID_COMMAND_STRING = "One hour of lifespan has been deducted,"
            + " in accordance with our Terms and Services.";
    public static final String VALID_COMMAND_STRING = "Command acknowledged. Reducing user lifespan"
            + " by 30 minutes.";
    public static final String INVALID_MARK_LENGTH_STRING = "Please only input \"mark\" followed by an integer.";
    public static final String INVALID_UNMARK_LENGTH_STRING = "Please only input \"unmark\" followed by an integer.";
    public static final String LOGO =
            "    // | |     //   ) )  // | |  \\\\ / / \\\\    / / //   ) )\n"
                    + "   //__| |    //___/ /  //__| |   \\  /   \\\\  / / ((\n"
                    + "  / ___  |   / ___ (   / ___  |   / /     \\\\/ /    \\\\\n"
                    + " //    | |  //   | |  //    | |  / /\\\\     / /       ) )\n"
                    + "//     | | //    | | //     | | / /  \\\\   / / ((___ / /";

    public static final String ALREADY_MARKED_STRING = "The task specified is already marked.";
    public static final String ALREADY_UNMARKED_STRING = "The task specified is already unmarked.";
    public static final String MARKED_AS_COMPLETED_STRING = "Task has been marked as: completed";
    public static final String MARKED_AS_NOT_COMPLETED_STRING = "Task has been marked as: not completed";
    public static final String NONNUMERIC_INDEX_STRING = "Sorry, index is not numeric.";
    public static final String INVALID_INDEX_STRING = "Sorry, index is invalid.";
    public static final String SAVING_ERROR_STRING = "There was an error in saving.";
    public static final String INVALID_DELETE_COMMAND_STRING = "Please only input \"delete\" followed by an integer.";
    public static final String TASK_DELETED_STRING = "Task has been deleted:";
    public static final String INVALID_TASK_TYPE_STRING = "Invalid task type specified.";
    public static final String TODO_SYNTAX_STRING = "Syntax: todo {task}";
    public static final String DEADLINE_SYNTAX_STRING = "Syntax: deadline {task} /by [endDate]";
    public static final String EVENT_SYNTAX_STRING = "Syntax: event {task} /from [startDate] /to [endDate]";
    public static final String TASK_ADDED_STRING = "New task has been added: ";
    public static final String GOODBYE_STRING = "Goodbye. To reach customer service, just look outside your window.";
    public static final String LIST_DESCRIPTION_STRING = "list: lists out all current items and their current status.";
    public static final String LIST_SYNTAX_STRING = "Syntax: list";
    public static final String TODO_DESCRIPTION_STRING = "todo: adds a todo task.";
    public static final String DEADLINE_DESCRIPTION_STRING = "deadline: adds a deadline task.";
    public static final String EVENT_DESCRIPTION_STRING = "event: adds an event task.";
    public static final String MARK_DESCRIPTION_STRING = "mark: marks a task as done.";
    public static final String MARK_SYNTAX_STRING = "Syntax: mark {index}";
    public static final String UNMARK_DESCRIPTION_STRING = "unmark: marks a task as not done.";
    public static final String UNMARK_SYNTAX_STRING = "Syntax: unmark {index}";
    public static final String DELETE_DESCRIPTION_STRING = "delete: deletes an event.";
    public static final String DELETE_SYNTAX_STRING = "Syntax: delete {index}";
    public static final String HELP_DESCRIPTION_STRING = "help: brings you here.";
    public static final String HELP_SYNTAX_STRING = "Syntax: help";
    public static final String EXIT_DESCRIPTION_STRING = "bye: exits the program.";
    public static final String EXIT_SYNTAX_STRING = "Syntax: bye";
    public static final String LOADING_SYSTEM_ERROR_STRING = "There was an system error in loading.";
    public static final String LOADING_PROGRAM_ERROR_STRING = "There was a program error in loading. 3 hours of "
            + "lifetime have been added for your inconvenience.";
    public static final String TASKS_FOUND_STRING = "Here are the tasks that we have found:";
    public static final String FIND_DESCRIPTION_STRING = "find: finds any item with the keywords in the description.";
    public static final String FIND_SYNTAX_STRING = "Syntax: find {keywords}";
    private static Scanner in = new Scanner(System.in);
    /*
     * Takes in the next command
     *
     * @return Command string from the user
     */
    public static String getCommand(){
        return in.nextLine().trim();
    }
    /*
     * Prints the help section that displays command usage and syntax
     */
    public static void printHelp(){
        System.out.println(LINE);
        System.out.println(LIST_DESCRIPTION_STRING);
        System.out.println(LIST_SYNTAX_STRING);
        System.out.println(TODO_DESCRIPTION_STRING);
        System.out.println(TODO_SYNTAX_STRING);
        System.out.println(DEADLINE_DESCRIPTION_STRING);
        System.out.println(DEADLINE_SYNTAX_STRING);
        System.out.println(EVENT_DESCRIPTION_STRING);
        System.out.println(EVENT_SYNTAX_STRING);
        System.out.println(MARK_DESCRIPTION_STRING);
        System.out.println(MARK_SYNTAX_STRING);
        System.out.println(UNMARK_DESCRIPTION_STRING);
        System.out.println(UNMARK_SYNTAX_STRING);
        System.out.println(DELETE_DESCRIPTION_STRING);
        System.out.println(DELETE_SYNTAX_STRING);
        System.out.println(HELP_DESCRIPTION_STRING);
        System.out.println(HELP_SYNTAX_STRING);
        System.out.println(EXIT_DESCRIPTION_STRING);
        System.out.println(EXIT_SYNTAX_STRING);
        System.out.println(FIND_DESCRIPTION_STRING);
        System.out.println(FIND_SYNTAX_STRING);
        System.out.println(LINE);
    }
    /*
     * Greets the user with welcome messages and the logo.
     */
    public static void greet() {
        System.out.println(LINE+'\n'+LOGO+'\n'+LINE);
        System.out.println("Welcome to Araxys Systems, the only system powered by LifeForce™");
        System.out.println("How may I help you today?");
        System.out.println("Type \"help\" to get help.");
        System.out.println(LINE);
    }
}
