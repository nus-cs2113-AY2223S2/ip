public class Archduke {
    static void greet() {
        IO.printBoxTopBorder();
        IO.printLogo();
        IO.printf("Hello! I'm Archduke. What do you want to do?");
        IO.printBoxBottomBorder();
    }

    static void bye() {
        IO.printBoxTopBorder();
        IO.printf("Bye. Hope to see you again soon!");
        IO.printBoxBottomBorder();
    }

    static void printError(String err) {
        IO.printBoxTopBorder();
        IO.printf("ERROR: %s", err);
        IO.printBoxBottomBorder();
    }

    static void listTasks(TaskStore store) {
        IO.printBoxTopBorder();
        IO.printf("Here are your tasks:");
        store.listTasks();
        IO.printBoxBottomBorder();
    }

    static void addTask(TaskStore store, String description) {
        store.addTask(description);
        IO.printBoxTopBorder();
        IO.printf("Added task: %s", description);
        IO.printBoxBottomBorder();
    }

    static void markTaskAsCompleted(TaskStore store, int index) {
        Task task = store.getTask(index);
        boolean isSuccessful = task.setAsCompleted();
        IO.printBoxTopBorder();
        if (isSuccessful) {
            IO.printf("Understood. This task has been marked as done.");
        } else {
            printError("This task has already been marked as done.");
        }
        task.printTask(index);
        IO.printBoxBottomBorder();
    }

    static void markTaskAsUncompleted(TaskStore store, int index) {
        Task task = store.getTask(index);
        boolean isSuccessful = task.setAsUncompleted();
        IO.printBoxTopBorder();
        if (isSuccessful) {
            IO.printf("Understood. This task has been marked as not done yet.");
        } else {
            printError("This task has already been marked as not done.");
        }
        task.printTask(index);
        IO.printBoxBottomBorder();
    }

    public static void main(String[] args) {
        IO io = new IO();
        TaskStore store = new TaskStore();

        greet();

        while (true) {
            String command = io.readUserInput();

            switch (command) {
            case "list":
                listTasks(store);
                continue;
            case "bye":
                bye();
                return;
            default:
                if (command.startsWith("mark") || command.startsWith("unmark")) {
                    try {
                        String[] parts = command.split(" ");
                        int index = Integer.parseInt(parts[1]) - 1;
                        if (parts[0].equals("mark")) {
                            markTaskAsCompleted(store, index);
                        } else {
                            markTaskAsUncompleted(store, index);
                        }
                    } catch (NumberFormatException e) {
                        printError("The provided index is not a valid integer.");
                    } catch (IndexOutOfBoundsException e) {
                        printError("The task can not be found.");
                    }
                    continue;
                }

                addTask(store, command);
            }
        }
    }
}
