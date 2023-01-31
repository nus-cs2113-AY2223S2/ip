import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static void addTask(ArrayList<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list\n", tasks.size());
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        ArrayList<Task> tasks = new ArrayList<Task>();

        while (!line.equals("bye")) {
            if (line.length() == 0) {
                line = in.nextLine();
                continue;
            }
            String[] wordList = line.split(" ");
            String command = wordList[0];
            switch (command) {
            case "list":
                if (wordList.length != 1) {
                    System.out.println("Wrong number of arguments for list");
                    break;
                }
                if (tasks.size() > 0) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i += 1) {
                        System.out.printf("1.%s\n", tasks.get(i));
                    }
                } else {
                    System.out.println("There is no tasks");
                }
                break;
            case "unmark":
                if (wordList.length != 2) {
                    System.out.println("Wrong number of arguments for unmark");
                    break;
                }
                try {
                    int index = Integer.parseInt(wordList[1]) - 1;
                    tasks.get(index).setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf("[ ] %s\n", tasks.get(index).getDescription());
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            case "mark":
                if (wordList.length != 2) {
                    System.out.println("Wrong number of arguments for mark");
                    break;
                }
                try {
                    int index = Integer.parseInt(wordList[1]) - 1;
                    tasks.get(index).setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf("[X] %s\n", tasks.get(index).getDescription());

                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            case "deadline":
                try {
                    line = line.substring(9);
                    String description = line.split(" /by ")[0];
                    String by = line.split(" /by ")[1];
                    Deadline deadlineTask = new Deadline(description, by);
                    addTask(tasks, deadlineTask);
                    break;
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            case "todo":
                try {
                    String description = line.substring(5);
                    Todo todoTask = new Todo(description);
                    addTask(tasks, todoTask);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            case "event":
                try {
                    line = line.substring(6);
                    String description = line.split(" /from ")[0];
                    String eventTime = line.split(" /from ")[1];
                    String startTime = eventTime.split(" /to ")[0];
                    String endTime = eventTime.split(" /to ")[1];
                    Event eventTask = new Event(description, startTime, endTime);
                    addTask(tasks, eventTask);
                } catch (Exception exception) {
                    System.out.println(exception);
                }
                break;
            default:
                System.out.println("command not found");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
