import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        String taskName = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Hello! I'm Duke\r\n"
                + "\t What can I do for you?\r\n"
                + "\t____________________________________________________________\r\n");

        while (!taskName.equals("bye")) {
            taskName = sc.next();

            if (!taskName.equals("bye")) {
                int indexTask;
                Task newTask;
                String prompt;
                String description;

                switch (taskName) {
                case "list":
                    System.out.println("\t____________________________________________________________\r\n"
                            + "\t Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t " + (i + 1) + ". " + taskList.get(i).toString());
                    }
                    System.out.println("\n\t____________________________________________________________\r\n");
                    break;

                case "todo":
                    prompt = sc.nextLine();
                    newTask = new Todo(prompt.trim());
                    newTask.addMessage();
                    taskList.add(newTask);
                    break;

                case "deadline":
                    prompt = sc.nextLine();
                    int dividerPosition = prompt.indexOf("/by");
                    description = prompt.substring(0, dividerPosition).trim();
                    String by = prompt.substring(dividerPosition + 3).trim();
                    newTask = new Deadline(description, by);
                    newTask.addMessage();
                    taskList.add(newTask);
                    break;

                case "event":
                    prompt = sc.nextLine();
                    int divider1Position = prompt.indexOf("/from");
                    int divider2Position = prompt.indexOf("/to");
                    description = prompt.substring(0, divider1Position).trim();
                    String from = prompt.substring(divider1Position + 5, divider2Position).trim();
                    String to = prompt.substring(divider2Position + 3).trim();
                    newTask = new Event(description, from, to);
                    newTask.addMessage();
                    taskList.add(newTask);
                    break;

                case "mark":
                    indexTask = sc.nextInt() - 1;
                    taskList.get(indexTask).setStatus(true);
                    taskList.get(indexTask).markMessage();
                    break;

                case "unmark":
                    indexTask = sc.nextInt() - 1;
                    taskList.get(indexTask).setStatus(false);
                    taskList.get(indexTask).markMessage();
                    break;

                }
            }
        }
        System.out.println("\t____________________________________________________________\r\n"
                + "\t Bye. Hope to see you again soon!\r\n"
                + "\t____________________________________________________________");
    }
}
