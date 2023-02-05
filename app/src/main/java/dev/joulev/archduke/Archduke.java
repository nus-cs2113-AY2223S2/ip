package dev.joulev.archduke;

import dev.joulev.archduke.commands.Command;
import dev.joulev.archduke.commands.Parser;
import dev.joulev.archduke.exceptions.ArchdukeException;
import dev.joulev.archduke.exceptions.ParserException;
import dev.joulev.archduke.exceptions.UserInputException;
import dev.joulev.archduke.exceptions.ParserException.ParserExceptionCode;
import dev.joulev.archduke.exceptions.UserInputException.UserInputExceptionCode;
import dev.joulev.archduke.io.In;
import dev.joulev.archduke.io.Out;
import dev.joulev.archduke.tasks.Store;
import dev.joulev.archduke.tasks.Task;
import dev.joulev.archduke.tasks.ToDo;
import dev.joulev.archduke.tasks.Deadline;
import dev.joulev.archduke.tasks.Event;

public class Archduke {
    private static In in;
    private static Store store;

    private static void handleCasesWhereSomethingHasGoneHorriblyWrong() {
        System.out.println(
                "Something has gone horribly wrong. Contact @joulev on GitHub immediately.");
    }

    private static void addTask(Task task) throws ArchdukeException {
        store.addTask(task);
        Out.printTaskAddition(task, store.getTaskCount());
    }

    private static void toggleTaskCompleteness(String body) throws ArchdukeException {
        try {
            int index = Integer.parseInt(body) - 1;
            store.toggleTaskCompleteness(index);
            Out.printTaskCompleteness(store, index);
        } catch (NumberFormatException e) {
            throw new UserInputException(UserInputExceptionCode.TOGGLE_INDEX_IS_NOT_A_NUMBER);
        } catch (IndexOutOfBoundsException e) {
            throw new UserInputException(UserInputExceptionCode.TOGGLE_INDEX_IS_OUT_OF_BOUNDS,
                    Integer.toString(store.getTaskCount()));
        }
    }

    public static void main(String[] args) {
        in = new In();
        store = new Store();

        try {
            Out.greet();
        } catch (ArchdukeException e) {
            handleCasesWhereSomethingHasGoneHorriblyWrong();
        }

        while (true) {
            try {
                Command command = Parser.parse(in.readUserInput());
                String type = command.getType();

                switch (type) {
                case "list":
                    Out.printTasks(store);
                    continue;
                case "mark":
                case "unmark":
                    toggleTaskCompleteness(command.getBody());
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
                    Out.bye();
                    return;
                default:
                    throw new ParserException(ParserExceptionCode.UNKNOWN_COMMAND, type);
                }
            } catch (ArchdukeException exception) {
                try {
                    Out.printError(exception.getErrorString());
                } catch (ArchdukeException somethingHasGoneHorriblyWrong) {
                    handleCasesWhereSomethingHasGoneHorriblyWrong();
                }
            }
        }
    }
}
