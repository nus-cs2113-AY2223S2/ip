import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String input;
        Scanner in = new Scanner(System.in);
        UI.showWelcomeMessage();
        do {
            input = in.nextLine();
            command(input);
        } while (!input.equals("bye"));
    }

    private static void command(String input) {
        String[] commandInput = input.split(" ");

        switch (commandInput[0]) {
        case "todo":
            input = input.replaceFirst("todo", "").trim();
            System.out.println("Got it. I've added this task:");
            System.out.println("  [T][ ] " + input);
            Todo t = new Todo(input, "T");
            tasksList.add(t);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            break;
        case "deadline":
            input = input.replaceFirst("deadline", "").trim();
            int indexOfSlash = input.indexOf("/");
            String taskName = input.substring(0, indexOfSlash);
            String by = input.substring(indexOfSlash + 3);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [D][ ] " + taskName + "(by:" + by + ")");
            Deadline d = new Deadline(taskName, "D", by);
            tasksList.add(d);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            break;
        case "event":
            input = input.replaceFirst("event", "").trim();
            indexOfSlash = input.indexOf("/");
            int lastIndexOfSlash = input.lastIndexOf("/");
            taskName = input.substring(0, indexOfSlash);
            String start = input.substring(indexOfSlash + 5, lastIndexOfSlash);
            String end = input.substring(lastIndexOfSlash + 3);
            System.out.println("Got it. I've added this task:");
            System.out.println("  [E][ ] " + taskName + "(from:" + start + "to:" + end + ")");
            Event e = new Event(taskName, "E", start, end);
            tasksList.add(e);
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            break;
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasksList.size(); i++) {
                System.out.println((i + 1) + "." + tasksList.get(i).toString());
            }
            break;
        case "mark":
            int taskNum = Integer.parseInt(commandInput[1]) - 1;
            System.out.println("Nice! I've marked this task as done:");
            tasksList.get(taskNum).markAsDone();
            System.out.println("  [" + tasksList.get(taskNum).getStatusIcon() + "]" + tasksList.get(taskNum).description);
            break;
        case "unmark":
            taskNum = Integer.parseInt(commandInput[1]) - 1;
            System.out.println("OK, I've marked this task as not done yet:");
            tasksList.get(taskNum).markAsUndone();
            System.out.println("  [" + tasksList.get(taskNum).getStatusIcon() + "]" + tasksList.get(taskNum).description);
            break;
        case "bye":
            UI.showByeMessage();
            break;
        default:
            UI.printMessage("added: " + input);
            Task u = new Task(input, "");
            tasksList.add(u);
        }
    }

}
