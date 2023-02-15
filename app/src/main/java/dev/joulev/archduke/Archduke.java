package dev.joulev.archduke;

import dev.joulev.archduke.commands.Command;
import dev.joulev.archduke.commands.Parser;
import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.ParserException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.ParserException.ParserExceptionCode;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.io.Input;
import dev.joulev.archduke.io.Output;
import dev.joulev.archduke.tasks.TaskStore;
import dev.joulev.archduke.tasks.Task;
import dev.joulev.archduke.tasks.ToDo;
import dev.joulev.archduke.tasks.Deadline;
import dev.joulev.archduke.tasks.Event;

public class Archduke {
    private static Input input;
    private static TaskStore taskStore;

    private static void handleCasesWhereSomethingHasGoneHorriblyWrong() {
        System.out.println(
                "Something has gone horribly wrong. Contact @joulev on GitHub immediately.");
    }

    private static void addTask(Task task) throws ArchdukeException {
        taskStore.addTask(task);
        Output.printTaskAddition(task, taskStore.getTaskCount());
    }

    private static void toggleTaskCompleteness(String body) throws ArchdukeException {
        try {
            int index = Integer.parseInt(body) - 1;
            taskStore.toggleTaskCompleteness(index);
            Output.printTaskCompleteness(taskStore, index);
        } catch (NumberFormatException e) {
            throw new UserInputException(UserInputExceptionCode.INDEX_IS_NOT_A_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new UserInputException(UserInputExceptionCode.INDEX_IS_OUT_OF_BOUNDS,
                    Integer.toString(taskStore.getTaskCount()));
        }
    }

    private static void deleteTask(String body) throws ArchdukeException {
        try {
            int index = Integer.parseInt(body) - 1;
            Task task = taskStore.getTask(index);
            taskStore.deleteTask(index);
            Output.printTaskDeletion(task, taskStore.getTaskCount());
        } catch (NumberFormatException e) {
            throw new UserInputException(UserInputExceptionCode.INDEX_IS_NOT_A_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new UserInputException(UserInputExceptionCode.INDEX_IS_OUT_OF_BOUNDS,
                    Integer.toString(taskStore.getTaskCount()));
        }
    }

    public static void main(String[] args) {
        input = new Input();
        taskStore = new TaskStore();

        try {
            Output.greet();
        } catch (ArchdukeException e) {
            handleCasesWhereSomethingHasGoneHorriblyWrong();
        }

        while (true) {
            try {
                Command command = Parser.parse(input.readUserInput());
                String type = command.getType();

                switch (type) {
                case "list":
                    Output.printTasks(taskStore);
                    continue;
                case "find":
                    Output.printQueriedTasks(taskStore, command.getBody());
                    continue;
                case "mark":
                    // Fallthrough
                case "unmark":
                    toggleTaskCompleteness(command.getBody());
                    continue;
                case "delete":
                    deleteTask(command.getBody());
                    continue;
                case "todo":
                    addTask(new ToDo(command.getBody()));
                    continue;
                case "deadline":
                    addTask(new Deadline(command.getBody(), command.getBy()));
                    continue;
                case "event":
                    addTask(new Event(command.getBody(), command.getFrom(), command.getTo()));
                    continue;
                case "bye":
                    Output.bye();
                    return;
                default:
                    throw new ParserException(ParserExceptionCode.UNKNOWN_COMMAND, type);
                }
            } catch (ArchdukeException exception) {
                try {
                    Output.printError(exception.getErrorString());
                } catch (ArchdukeException somethingHasGoneHorriblyWrong) {
                    handleCasesWhereSomethingHasGoneHorriblyWrong();
                }
            }
        }
    }
}
