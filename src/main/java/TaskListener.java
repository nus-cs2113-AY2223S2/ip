import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TaskListener {
    static final String DIVIDER = "--------------------------------------------------------------------";
    private TasksList tasksList;

    private TasksLoader tasksLoader;
    public TaskListener(TasksList tasksList, TasksLoader tasksLoader) {
        this.tasksList = tasksList;
        this.tasksLoader = tasksLoader;
    }

    public static void greet() {
        String logo =
                        "   _____ __  ____________  __    ____  ________ __\n" +
                        "  / ___// / / / ____/ __ \\/ /   / __ \\/ ____/ //_/\n" +
                        "  \\__ \\/ /_/ / __/ / /_/ / /   / / / / /   / ,<   \n" +
                        " ___/ / __  / /___/ _, _/ /___/ /_/ / /___/ /| |  \n" +
                        "/____/_/ /_/_____/_/ |_/_____/\\____/\\____/_/ |_|  ";


        printLines("Hello from", logo);

        printLines("Hello! I'm SHERLOCK", "What can I do for you?");
    }

    public static void printLines(String... lines) {
        System.out.println(DIVIDER);
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println(DIVIDER + System.lineSeparator());
    }
    private void printAddedTask(Task task) {
        String tasksWord = tasksList.getTasksCount() == 1 ? " task" : " tasks";
        final String FIRST_SENTENCE = "Got it. I've added this task:";
        final String SECOND_SENTENCE = "Now you have " + tasksList.getTasksCount() + tasksWord + " in the list.";
        printLines(FIRST_SENTENCE + System.lineSeparator() + task + System.lineSeparator() + SECOND_SENTENCE);
    }

    private void addGenericTask(String name) {
        if (name.isEmpty()) {
            printLines("Please provide task description");
            return;
        }
        this.tasksList.addTask(new Task(name, false));

        printLines("added: " + name);

        tasksLoader.writeToFile(tasksList);
    }

    private void createTodo(String arguments) {
        String name = arguments;
        if (name.isEmpty()) {
            printLines("Please provide todo description");
            return;
        }
        Todo todo = new Todo(name, false);
        this.tasksList.addTask(todo);
        printAddedTask(todo);

        tasksLoader.writeToFile(tasksList);
    }

    private void createDeadline(String arguments) {
        int byCommandIndex = arguments.indexOf("/by");
        if (byCommandIndex < 0) {
            printLines("Please specify the /by command");
            return;
        }
        String name = arguments.substring(0, byCommandIndex).trim();
        String byArgumentValue = arguments.substring(byCommandIndex).substring("/by".length()).trim();

        if (name.isEmpty()) {
            printLines("Please provide name argument");
            return;
        }

        if (byArgumentValue.isEmpty()) {
            printLines("Please provide /by argument");
            return;
        }
        Deadline deadline = new Deadline(name, false, byArgumentValue);
        this.tasksList.addTask(deadline);

        printAddedTask(deadline);

        tasksLoader.writeToFile(tasksList);
    }

    private void createEvent(String arguments) {
        {
            int fromCommandIndex = arguments.indexOf("/from");
            int toCommandIndex = arguments.indexOf("/to");

            if (fromCommandIndex < 0) {
                printLines("Please specify the /from command");
                return;
            }
            if (toCommandIndex < 0) {
                printLines("Please specify the /to command");
                return;
            }

            String name = arguments.substring(0, fromCommandIndex).trim();

            String fromArgumentValue = arguments.substring(fromCommandIndex, toCommandIndex)
                    .substring("/from".length()).trim();

            String toArgumentValue = arguments.substring(toCommandIndex)
                    .substring("/to".length()).trim();

            if (name.isEmpty()) {
                printLines("Please provide the name argument");
                return;
            }

            if (fromArgumentValue.isEmpty()) {
                printLines("Please provide the /from argument");
                return;
            }

            if (toArgumentValue.isEmpty()) {
                printLines("Please provide the /to argument");
                return;
            }

            Event event = new Event(name, false, fromArgumentValue, toArgumentValue);
            this.tasksList.addTask(event);

            printAddedTask(event);

            tasksLoader.writeToFile(tasksList);
        }
    }

    private void modifyDoneValue(boolean isDone, int taskIndex) {
        String successMessage = isDone ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";

        try {
            Task task = this.tasksList.getTasks().get(taskIndex);
            task.setIsDone(isDone);

            printLines(successMessage, task.toString());

            tasksLoader.writeToFile(tasksList);
        } catch (IndexOutOfBoundsException e) {
            printLines("No task at such index!");
        }
    }

    private void removeTask(int taskIndex) {
        try {
            Task removedTask = this.tasksList.removeTask(taskIndex);

            String tasksWord = tasksList.getTasksCount() == 1 ? " task " : " tasks ";

            String successMessage =  "Noted. I've removed this task:" +
                    System.lineSeparator() +
                    removedTask +
                    System.lineSeparator() +
                    "Now you have " + tasksList.getTasksCount() + tasksWord + "in the list.";

           printLines(successMessage);
        } catch (IndexOutOfBoundsException e) {
            printLines("No task at such index!");
        }
    }
    public void listen() {
        Scanner in = new Scanner(System.in);

        String line = in.nextLine();
        String[] inputArray = line.split("\\s+", 2);

        String command = inputArray[0];
        String arguments = inputArray.length > 1 ? inputArray[1] : "";

        switch (command) {

        case "add":
            addGenericTask(arguments);
            break;

        case "todo":
            createTodo(arguments);
            break;

        case "deadline":
            createDeadline(arguments);
            break;

        case "event":
            createEvent(arguments);
            break;

        case "list": {
            printLines(this.tasksList.toString());
            break;
        }

        case "mark":
        case "unmark": {
            // isDone value that user wants to achieve
            boolean intendedDoneValue = command.equals("mark");

            try {
                int taskIndex = Integer.parseInt(arguments) - 1;

                modifyDoneValue(intendedDoneValue, taskIndex);
            } catch (NumberFormatException e) {
                printLines("Please provide a valid index");
            }
            break;
        }

        case "delete": {
            try {
                int taskIndex = Integer.parseInt(arguments) - 1;
                removeTask(taskIndex);
            } catch (NumberFormatException e) {
                printLines("Please provide a valid index");
            }
            break;
        }

        case "bye":
            printLines("Bye. Hope to see you again soon!");
            return;

        default:
            printLines("No such command exists!");
        }
        listen();
    }


}
