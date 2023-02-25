/**
 * Represents the parser that will handle all inputs commands from the user
 */
public class Parser {
    private final String input;

    public Parser(String input) {
        this.input = input;
    }

    /**
     * Splits the command from the given user input
     *
     * @return the command to be executed
     */
    private String getCommand() {
        String[] temp = input.split(" ", 2);
        return temp[0];
    }

    /**
     * Handles all types of commands given by the user
     *
     * @param tasks task list containing all current tasks
     * @param ui    UI object to print bye statement when user exits programme
     */
    public void handleCommand(TaskList tasks, UI ui) {
        String command = getCommand();
        Command curCommand;
        switch (command) {
        case "bye":
            ui.printByeStatement();
            Duke.isEnd = true;
            break;
        case "list":
            curCommand = new ListCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "mark":
            curCommand = new MarkCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "unmark":
            curCommand = new UnmarkCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "delete":
            curCommand = new DeleteCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "todo":
            curCommand = new ToDoCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "deadline":
            curCommand = new DeadlineCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "event":
            curCommand = new EventCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        case "find":
            curCommand = new FindCommand();
            curCommand.runCommand(input, tasks, ui);
            break;
        default:
            Messages.unknownCommandMessage();
        }
    }

}
