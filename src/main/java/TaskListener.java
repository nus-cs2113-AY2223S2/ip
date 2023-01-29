import java.util.Arrays;
import java.util.Scanner;

public class TaskListener {
    static final String DIVIDER = "--------------------------------------------------------------------";
    private TaskList taskList;

    public TaskListener(int expectedTasksCount) {
        this.taskList = new TaskList(expectedTasksCount);
    }

    private static void printLines(String... lines) {
        System.out.println(DIVIDER);
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println(DIVIDER + System.lineSeparator());
    }

    private void printAddedTask(Task task) {
        String firstSentence = "Got it. I've added this task:";
        String secondSentence = "Now you have " + taskList.getTasksCount() + " tasks in the list.";
        printLines(firstSentence + System.lineSeparator() + task + System.lineSeparator() + secondSentence);
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

    public void listen() {
        Scanner in = new Scanner(System.in);

        String line = in.nextLine();
        String[] inputArray = line.split("\\s+", 2);

        String command = inputArray[0];
        String arguments = inputArray.length > 1 ? inputArray[1] : "";

        switch (command) {
            case "list": {
                printLines(this.taskList.toString());
                break;
            }
            case "mark":
            case "unmark": {
                String successMessage = command.equals("mark") ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";

                // isDone value that user wants to achieve
                boolean intendedDoneValue = command.equals("mark");

                String indexArgument = arguments.substring(0);
                int index = Integer.parseInt(indexArgument) - 1;

                // Invalid index handler
                if (index < 0 || index > this.taskList.getTasks().length - 1) {
                    printLines("No task at such index!");
                    break;
                }

                Task task = this.taskList.getTasks()[index];
                task.setIsDone(intendedDoneValue);
                printLines(successMessage, task.toString());
                break;
            }

            case "todo": {
                String name = arguments;
                Todo todo = new Todo(name, false);
                this.taskList.addTask(todo);
                printAddedTask(todo);
                break;
            }

            case "deadline": {
                int byCommandIndex = arguments.indexOf("/by");
                if (byCommandIndex < 0) {
                    printLines("Please specify the /by command");
                    break;
                }
                String name = arguments.substring(0, byCommandIndex).trim();
                String byArgumentValue = arguments.substring(byCommandIndex)
                        .substring("/by".length()).trim();

                if(name.isEmpty()) {
                    printLines("Please provide name argument");
                    break;
                }

                if(byArgumentValue.isEmpty()) {
                    printLines("Please provide /by argument");
                    break;
                }
                Deadline deadline = new Deadline(name, false, byArgumentValue);
                this.taskList.addTask(deadline);
                printAddedTask(deadline);
                break;
            }

            case "event": {
                int fromCommandIndex = arguments.indexOf("/from");
                int toCommandIndex = arguments.indexOf("/to");

                if (fromCommandIndex < 0) {
                    printLines("Please specify the /from command");
                    break;
                }
                if (toCommandIndex < 0) {
                    printLines("Please specify the /to command");
                    break;
                }

                String name = arguments.substring(0, fromCommandIndex).trim();

                String fromArgumentValue = arguments.substring(fromCommandIndex, toCommandIndex)
                                        .substring("/from".length()).trim();
                String toArgumentValue = arguments.substring(toCommandIndex)
                                        .substring("/to".length()).trim();

                if(name.isEmpty()) {
                    printLines("Please provide the name argument");
                    break;
                }

                if(fromArgumentValue.isEmpty()) {
                    printLines("Please provide the /from argument");
                    break;
                }

                if(toArgumentValue.isEmpty()) {
                    printLines("Please provide the /to argument");
                    break;
                }

                Event event = new Event(name, false, fromArgumentValue, toArgumentValue);
                this.taskList.addTask(event);
                printAddedTask(event);
                break;
            }

            case "bye":
                printLines("Bye. Hope to see you again soon!");
                return;

            case "add":
                String name = arguments;
                this.taskList.addTask(new Task(name, false));
                printLines("added: " + name);
                break;

            default:
                printLines("No such command exists!");
        }

        listen();
    }
}
