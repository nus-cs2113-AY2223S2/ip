import commands.Command;
import commands.Parser;
import io.In;
import io.Out;
import tasks.Store;
import tasks.Task;

public class Archduke {
    private static In in;
    private static Store store;

    private static void addTask(String description) {
        store.addTask(description);
        Out.printBox("Added task: %s", description);
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
            case "add":
                addTask(command.getBody());
                continue;
            case "bye":
                Out.bye();
                return;
            default:
                Out.printError(type
                        + " is not a valid command. \"list\", \"mark\", \"unmark\", \"add\" and \"bye\" are valid commands.");
            }
        }
    }
}
