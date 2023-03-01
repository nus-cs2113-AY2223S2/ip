package jonathan;

import command.*;
import task.Deadline;
import task.Event;
import task.Todo;

import java.util.Objects;

public class Parser {
    public static Command parse(String fullCommand) {
        Command command;

        try {
            if (Objects.equals(fullCommand, "bye")) {
                command = new ByeCommand();

            } else if (Objects.equals(fullCommand, "list")) {
                command = new ListCommand();

            } else if (fullCommand.split(" ")[0].equals("mark") || fullCommand.split(" ")[0].equals("unmark")) {
                boolean isDone = fullCommand.split(" ")[0].equals("mark");
                int taskNum = Integer.parseInt(fullCommand.split(" ")[1]);
                if (isDone) {
                    command = new MarkCommand(taskNum);
                } else {
                    command = new UnmarkCommand(taskNum);
                }

            } else if (fullCommand.split(" ")[0].equals("todo")) {

                if (fullCommand.split(" ").length == 1) {
                    throw new StringIndexOutOfBoundsException();
                }

                String description = fullCommand.substring(fullCommand.indexOf(" ") + 1);
                Todo todo = new Todo(description);
                command = new TodoCommand(todo);

            } else if (fullCommand.split(" ")[0].equals("deadline")) {
                String description = fullCommand.substring(fullCommand.indexOf(" ") + 1, fullCommand.indexOf(" /by"));
                String by = fullCommand.substring(fullCommand.indexOf("/by") + 4);
                Deadline deadline = new Deadline(description, by);
                command = new DeadlineCommand(deadline);

            } else if (fullCommand.split(" ")[0].equals("event")) {
                String description = fullCommand.substring(fullCommand.indexOf(" ") + 1, fullCommand.indexOf(" /from"));
                String start = fullCommand.substring(fullCommand.indexOf("/from") + 6, fullCommand.indexOf(" /to"));
                String end = fullCommand.substring(fullCommand.indexOf("/to") + 4);
                Event event = new Event(description, start, end);
                command = new EventCommand(event);

            } else if (fullCommand.split(" ")[0].equals("delete")) {
                int taskNum = Integer.parseInt(fullCommand.split(" ")[1]);
                command = new DeleteCommand(taskNum);

            } else if (fullCommand.split(" ")[0].equals("find")) {
                if (fullCommand.split(" ").length == 1) {
                    throw new StringIndexOutOfBoundsException();
                }

                String keyword = fullCommand.substring(fullCommand.indexOf(" ") + 1);
                command = new FindCommand(keyword);

            } else {
                throw new JonathanException();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            command = new TaskNotFoundCommand();

        } catch (StringIndexOutOfBoundsException e) {
            command = new WrongFormatCommand();

        } catch (JonathanException e) {
            command = new WrongInputCommand();
        }

        return command;
    }
}
