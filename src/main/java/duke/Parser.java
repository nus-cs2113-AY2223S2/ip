package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.ExitCommand;
import duke.command.IllegalCommand;
import duke.command.ListCommand;
import duke.command.MarkAndUnmarkCommand;
import duke.exception.EmptyCommandException;
import duke.exception.IllegalCommandException;

public class Parser {

    public static Command parse(String fullCommand, TaskList tasks, Storage database) {
        String firstWord = fullCommand.split(" ")[0];
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
            return MarkOrUnmarkHandler(fullCommand, tasks, database);
        } else if (firstWord.equals("todo")) {
            return todoTaskHandler(fullCommand);
        } else if (firstWord.equals("deadline")) {
            return deadlineTaskHandler(fullCommand);
        } else if (firstWord.equals("event")) {
            return eventTaskHandler(fullCommand);
        } else if (firstWord.equals("delete")) {
            return deleteTaskHandler(fullCommand, tasks);
        } else {
            return new IllegalCommand();
        }
    }

    private static Command MarkOrUnmarkHandler(String command, TaskList tasks, Storage database) {
        Command markingCommand = null;
        try {
            markingCommand = prepareMarkAndUnmarkTask(command, tasks, database);
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        }
        return markingCommand;
    }

    private static Command prepareMarkAndUnmarkTask(String command, TaskList tasks, Storage database) throws IllegalCommandException {
        String[] words = command.split(" ");
        if (words.length != 2) {
            throw new IllegalCommandException();
        }
        int indexOfMarking = getIndex(words[1]);
        if (!isValidIndex(indexOfMarking, tasks)) {
            throw new IllegalCommandException();
        }
        return new MarkAndUnmarkCommand(command, words, indexOfMarking);
    }

    private static Command todoTaskHandler(String command) {
        Command todoCommand = null;
        try {
            todoCommand = prepareTodoTask(command);
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("todo");
        }
        return todoCommand;
    }

    private static Command prepareTodoTask(String command) throws EmptyCommandException {
        String todo = command.replace("todo", "").trim();
        if (todo.isEmpty()) {
            throw new EmptyCommandException();
        }
        return new AddTodoCommand(todo);
    }

    private static Command deadlineTaskHandler(String command) {
        Command deadlineCommand = null;
        try {
            deadlineCommand = prepareDeadlineTask(command);
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("deadline");
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        }
        return deadlineCommand;
    }

    private static Command prepareDeadlineTask(String command) throws EmptyCommandException, IllegalCommandException {
        command = command.replace("deadline", "").trim();
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = command.split(" /by ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        return new AddDeadlineCommand(stringSplit);
    }

    private static Command eventTaskHandler(String command) {
        Command eventCommand = null;
        try {
            eventCommand = prepareEventTask(command);
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        } catch (EmptyCommandException e) {
            Ui.emptyCommandMessage("event");
        }
        return eventCommand;
    }

    private static Command prepareEventTask(String command) throws IllegalCommandException, EmptyCommandException {
        command = command.replace("event", "").trim();
        if (command.isEmpty()) {
            throw new EmptyCommandException();
        }
        String[] stringSplit = command.split(" /from ");
        if (isInvalidString(stringSplit)) {
            throw new IllegalCommandException();
        }
        String[] startToEndTime = stringSplit[1].split(" /to ");
        if (isInvalidString(startToEndTime)) {
            throw new IllegalCommandException();
        }
        return new AddEventCommand(stringSplit[0], startToEndTime[0], startToEndTime[1]);
    }

    private static Command deleteTaskHandler(String command, TaskList tasks) {
        Command deleteCommand = null;
        try {
            deleteCommand = deleteTask(command, tasks);
        } catch (IllegalCommandException e) {
            Ui.illegalCommandMessage();
        }
        return deleteCommand;
    }

    private static Command deleteTask(String command, TaskList tasks) throws IllegalCommandException {
        String[] words = command.trim().split(" ");
        if (isInvalidString(words)) {
            throw new IllegalCommandException();
        }
        int deleteIndex = getIndex(words[1]);
        if (!isValidIndex(deleteIndex, tasks)) {
            throw new IllegalCommandException();
        }
        return new DeleteTaskCommand(deleteIndex);
    }
    private static boolean isValidIndex(int indexOfMarking, TaskList tasks) {
        if (indexOfMarking < 0 || indexOfMarking > (tasks.getTaskCount() - 1)) {
            return false;
        }
        return true;
    }

    private static boolean isInvalidString(String[] stringSplit) {
        return stringSplit.length != 2;
    }

    private static int getIndex(String strNum) {
        // Referenced from https://www.baeldung.com/java-check-string-number
        int index = 0;
        if (strNum == null) {
            return -1;
        }
        try {
            index = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return -1;
        }
        index--;
        return index; // Returns 0-index of parsing Integer or -1 if string is not a number or empty
    }

}
