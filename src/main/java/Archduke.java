import commands.Command;
import commands.Parser;
import exceptions.ArchdukeException;
import exceptions.ParserException;
import exceptions.ParserException.ParserExceptionCode;
import io.In;
import io.Out;
import tasks.Store;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

public class Archduke {
    private static In in;
    private static Store store;

    private static void addTask(Task task) {
        store.addTask(task);
        Out.printTaskAddition(task, store.getTaskCount());
    }

    private static void toggleTaskCompleteness(String body) {
        try {
            int index = Integer.parseInt(body) - 1;
            Task task = store.getTask(index);
            task.toggleCompleted();
            Out.printTaskCompleteness(store, index);
        } catch (NumberFormatException e) {
            Out.printError("The provided index is not a valid integer.");
        } catch (IndexOutOfBoundsException e) {
            Out.printError("The task can not be found.");
        }
    }

    public static void main(String[] args) {
        in = new In();
        store = new Store();

        Out.greet();

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
                exception.printError();
            }
        }
    }
}
