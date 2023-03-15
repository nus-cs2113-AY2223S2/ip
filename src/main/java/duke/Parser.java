package duke;
public class Parser {
    public static Command parse(String command) {

        if (command.startsWith("todo ")) {
                return new AddTodoCommand(command);
        }
        else if (command.startsWith("deadline ")) {
            return new AddDeadlineCommand(command);
        }
        else if (command.startsWith("event ")) {
            return new AddEventCommand(command);
        }
        else if (command.startsWith("mark ")) {
            return new MarkCommand(command);
        }
        else if (command.startsWith("unmark ")) {
            return new UnmarkCommand(command);
        }
        else if (command.startsWith("delete")) {
            return new DeleteCommand(command);
        }
        else if (command.startsWith("list")) {
            return new ListCommand(command);
        }
        else {
            return new ExitCommand();
        }
        }
    }
}
