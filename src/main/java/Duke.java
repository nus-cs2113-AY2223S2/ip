import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void greeting() {
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
    }
    public static void printLine(){
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        greeting();
        boolean isByeEnter = false;
        ArrayList<Task> taskNameList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        while (!isByeEnter) {
            String taskName = userInput.nextLine();
            Task task = new Task(taskName);
            String[] command = taskName.split("\\s+");
            Event event = new Event(taskName);
            Todo toDo = new Todo(taskName);
            Deadline deadLine = new Deadline(taskName);
            switch (command[0]) {

            case "list":
                printLine();
                System.out.print(task.guideline());
                for (int i = 0; i < taskNameList.size(); i++) {
                    System.out.print("     " + (i + 1) + "."
                            + taskNameList.get(i).getState().trim() + System.lineSeparator());
                }
                printLine();
                break;

            case "mark":
                printLine();
                int indexTask = Integer.parseInt(command[1]);
                taskNameList.get(indexTask - 1).mark();
                // new Task(isComplete);
                System.out.print("     Nice! I've marked this task as done:"
                        + System.lineSeparator()
                        + taskNameList.get(indexTask - 1).getState());
                printLine();
                break;

            case "unmark":
                printLine();
                int indexOfTask = Integer.parseInt(command[1]);
                taskNameList.get(indexOfTask - 1).unMark();
                System.out.print("     OK, I've marked this task as not done yet:"
                        + System.lineSeparator()
                        + taskNameList.get(indexOfTask - 1).getState());
                printLine();
                break;

            case "todo":
                printLine();
                taskNameList.add(toDo);
                String outputForTodo = toDo.guideline() + toDo.getState() + task.numberOfTask(taskNameList.size());
                System.out.println(outputForTodo);
                printLine();
                break;

            case "deadline":
                printLine();
                taskNameList.add(deadLine);
                String outputForDeadline = deadLine.guideline() + deadLine.getState() + task.numberOfTask(taskNameList.size());
                System.out.println(outputForDeadline);
                printLine();
                break;

            case "event":
                printLine();
                taskNameList.add(event);
                String outputForEvent = event.guideline() + event.getState() + task.numberOfTask(taskNameList.size());
                System.out.println(outputForEvent);
                printLine();
                break;

            case "Bye":
                printLine();
                System.out.println("     Bye. Hope to see you again soon!");
                isByeEnter = true;
                printLine();
                break;

            default:
                printLine();
                System.out.println("     oops, cannot find amtch instruction, please type again");
                printLine();
                break;

            }
        }

    }
}

