import java.util.Scanner;

public class Duke {
    private static final TaskList tasks = new TaskList();

    public static void main(String[] args) {
        IO.printGreeting();

        // Input variables initialised.
        Scanner myScanner = new Scanner(System.in);
        String userInput;

        // The main loop, which ends when user says Bye
        while (true) {
            System.out.print("> ");
            userInput = myScanner.nextLine();

            // Execute command and print out the feedback string.
            String feedback = executeCommand(userInput.toLowerCase());
            System.out.println(feedback);

            IO.printHLine();
        }
    }

    /**
     * Performs each input's methods as long as it exists, if not return error
     * @param inputLine Input directly from command-line
     * @return Feedback string or error string
     */
    public static String executeCommand(String inputLine) {
        final String[] commandTypeAndArgs = IO.splitCommandAndArgs(inputLine);
        final String command = commandTypeAndArgs[0];
        final String commandArgs = commandTypeAndArgs[1];

        // Check command against the set list of commands.
        // If it doesn't exist, default is invalid
        switch(command) {
        case IO.COMMAND_HELP:
            return IO.MESSAGE_HELP;
        case IO.COMMAND_LIST:
            if (tasks.getNumberOfTasks() < 1) {
                return IO.ERROR_TASKS_EMPTY;
            }
            return TaskList.getTaskListString();
        case IO.COMMAND_MARK: // Fallthrough
        case IO.COMMAND_UNMARK:
            return TaskList.executeMarkUnmark(command, commandArgs);
        case IO.COMMAND_TASK_TODO:
            return handleAddTaskTodo(commandArgs);
        case IO.COMMAND_TASK_DEADLINE:
            return handleAddTaskDeadline(commandArgs);
        case IO.COMMAND_TASK_EVENT:
            return handleAddTaskEvent(commandArgs);
        case IO.COMMAND_BYE:
            IO.printExitMessage();
            System.exit(0);
            // Fallthrough (If somehow cannot exit? LOL)
        default:
            return IO.ERROR_MESSAGE_INVALID_COMMAND;
        }
    }

    /**
     * ==============================================================
     * Below handling Tasks (Validation and addTask)
     * ==============================================================
     */
    private static String handleAddTaskTodo(String commandArgs) {
        try {
            Todo newTask = new Todo(IO.processTaskTodo(commandArgs), TaskList.getNextTaskNumber());
            tasks.addTask(newTask);
            return IO.feedbackTaskAdded(newTask);
        } catch (DukeException e) {
            return IO.ERROR_MESSAGE_ARGUMENT_MISSING;
        }
    }

    private static String handleAddTaskDeadline(String commandArgs) {
        try {
            String[] deadlineArgs = IO.processTaskDeadline(commandArgs);
            Deadline newTask =
                    new Deadline(deadlineArgs[0], TaskList.getNextTaskNumber(), deadlineArgs[1]);
            tasks.addTask(newTask);
            return IO.feedbackTaskAdded(newTask);
        } catch (DukeException e) {
            return IO.ERROR_MESSAGE_ARGUMENT_NUMBER;
        }
    }

    private static String handleAddTaskEvent(String commandArgs) {
        try {
            String[] eventArgs = IO.processTaskEvent(commandArgs);
            Event newTask =
                    new Event(eventArgs[0], TaskList.getNextTaskNumber(),
                            eventArgs[1], eventArgs[2]);
            tasks.addTask(newTask);
            return IO.feedbackTaskAdded(newTask);
        } catch (DukeException e) {
            return IO.ERROR_MESSAGE_ARGUMENT_NUMBER;
        }
    }
}
