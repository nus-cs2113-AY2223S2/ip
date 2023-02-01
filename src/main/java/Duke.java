import java.util.Scanner;

public class Duke {

    public static String longLine = "---------------------------------------------";

    private static void printList(Task[] s, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; ++i) {
            String index = Integer.toString(i + 1);
            System.out.println(index + '.' + s[i].toString());
        }
    }

    private static void printCommand(Task[] taskList, int count) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskList[count].toString());
        System.out.println("Now you have " + Integer.toString(count + 1) + " tasks in the list.");
    }

    public static void main(String[] args) {
        System.out.println(longLine);
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(longLine);

        boolean shouldRun = true;
        Task[] taskList = new Task[100];
        int count = 0;
        int index = 0;
        String line;
        String task;
        String input[];

        while (shouldRun) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] cases = line.split(" ", 2);
            System.out.println(longLine);
            switch (cases[0]) {
            case "todo":
                taskList[count] = new Todo(cases[1]);
                printCommand(taskList, count);
                count++;
                break;
            case "deadline":
                input = cases[1].split("/", 2);
                task = input[0].trim();
                String by = input[1].substring(3);
                taskList[count] = new Deadline(task, by);
                printCommand(taskList, count);
                count++;
                break;
            case "event":
                input = cases[1].split("/", 3);
                task = input[0].trim();
                String from = input[1].substring(5).trim();
                String to = input[2].substring(3);
                taskList[count] = new Event(task, from, to);
                printCommand(taskList, count);
                count++;
                break;
            case "list":
                printList(taskList, count);
                break;
            case "mark":
                index = Integer.parseInt(cases[1]) - 1;
                if (taskList[index] == null) {
                    System.out.println("No such task! Try again.");
                    break;
                }
                taskList[index].setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList[index].toString());
                break;
            case "unmark":
                index = Integer.parseInt(cases[1]) - 1;
                if (taskList[index] == null) {
                    System.out.println("No such task! Try again.");
                    break;
                }
                taskList[index].setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(taskList[index].toString());
                break;
            case "bye":
                shouldRun = false;
                break;
            default:
                taskList[count] = new Task(line);
                count++;
                System.out.println("added: " + line);
            }
            if (shouldRun) {
                System.out.println(longLine);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(longLine);
        return;
    }
}
