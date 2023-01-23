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
        for (String line: lines) {
            System.out.println(line);
        }
        System.out.println(DIVIDER + System.lineSeparator());
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
        String[] inputArray = line.split("\\s+");

        String command = inputArray[0];
        String[] arguments = Arrays.copyOfRange(inputArray, 1, inputArray.length );

        switch(command) {
            case "list":
                printLines(this.taskList.toString());
                break;
            case "mark":
            case "unmark": {
                String successMessage = command.equals("mark") ?  "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";

                // isDone value that user wants to achieve
                boolean intendedDoneValue = command.equals("mark");

                int index = Integer.parseInt(arguments[0]) - 1;

                // Invalid index handler
                if(index < 0 || index > this.taskList.getTasks().length - 1) {
                    printLines("No task at such index!");
                    break;
                }

                Task task = this.taskList.getTasks()[index];
                task.setIsDone(intendedDoneValue);
                printLines(successMessage, task.toString());
                break;
            }

            case "bye":
                printLines("Bye. Hope to see you again soon!");
                return;

            default:
                this.taskList.addTask(line);
                printLines("added: " + line);
        }

        listen();
    }
}
