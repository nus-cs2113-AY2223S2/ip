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

    private static void toggleTaskCompleteness(int index) {
        Task task = store.getTask(index);
        task.toggleCompleted();
        Out.printTaskCompleteness(store, index);
    }

    public static void main(String[] args) {
        in = new In();
        store = new Store();

        Out.greet();

        while (true) {
            String command = in.readUserInput();

            switch (command) {
            case "list":
                Out.printTasks(store);
                continue;
            case "bye":
                Out.bye();
                return;
            default:
                if (command.startsWith("mark") || command.startsWith("unmark")) {
                    try {
                        String[] parts = command.split(" ");
                        int index = Integer.parseInt(parts[1]) - 1;
                        toggleTaskCompleteness(index);
                    } catch (NumberFormatException e) {
                        Out.printError("The provided index is not a valid integer.");
                    } catch (IndexOutOfBoundsException e) {
                        Out.printError("The task can not be found.");
                    }
                    continue;
                }

                addTask(command);
            }
        }
    }
}
