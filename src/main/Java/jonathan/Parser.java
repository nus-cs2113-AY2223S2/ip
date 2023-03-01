package jonathan;

import command.*;
import task.Deadline;
import task.Event;
import task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Model a class to handle fullCommand, and produce in nicer/understandable format.
 */
public class Parser {
    /**
     * Method to parse the raw command.
     * @param fullCommand the raw command.
     * @return the Command class to be executed.
     * @throws JonathanException for error handling.
     */
    public static Command parse(String fullCommand) throws JonathanException {
        Command command = new ByeCommand();

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
                String byString = fullCommand.substring(fullCommand.indexOf("/by") + 4);
                LocalDateTime by = getDateAndTimeFormat(byString);
                Deadline deadline = new Deadline(description, by);
                command = new DeadlineCommand(deadline);


            } else if (fullCommand.split(" ")[0].equals("event")) {
                String description = fullCommand.substring(fullCommand.indexOf(" ") + 1, fullCommand.indexOf(" /from"));
                String startString = fullCommand.substring(fullCommand.indexOf("/from") + 6, fullCommand.indexOf(" /to"));
                String endString = fullCommand.substring(fullCommand.indexOf("/to") + 4);
                LocalDateTime start = getDateAndTimeFormat(startString);
                LocalDateTime end = getDateAndTimeFormat(endString);
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
                throw new JonathanException("Wrong input, please type it correctly!");
            }

        } catch (StringIndexOutOfBoundsException e) {
            command = new WrongFormatCommand();

        }

        return command;
    }

    /**
     * Method to convert the String into LocalDateTime.
     * @param substring string to be converted.
     * @return LocalDateTime.
     * @throws JonathanException when the string is not following the format.
     */
    private static LocalDateTime getDateAndTimeFormat(String substring) throws JonathanException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(substring, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new JonathanException("Please input the date and time in the correct format!");
        }
    }
}
