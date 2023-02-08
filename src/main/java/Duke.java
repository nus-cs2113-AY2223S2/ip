import java.util.Objects;
import java.util.Scanner;
public class Duke {

    static final int MAX_TASKS = 100;

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int counter = 0;
        System.out.println("Hi, I'm bob");
        System.out.println("What's up");
        String word = reader.nextLine();

        while (!word.equals("bye")) {

            if (word.equals("list")) {
                if (counter == 0) {
                    System.out.println("No tasks!");
                } else {
                    getList(tasks, counter);
                }
                word = reader.nextLine();

            //mark, unmark, or add
            } else {
                try {
                    int taskDescriptionIndex = word.indexOf(" ");
                    String taskType = word.substring(0, taskDescriptionIndex);
                    String taskDescription = word.substring(taskDescriptionIndex+1);

                    if (taskType.isBlank() || taskDescription.isBlank()) {
                        System.out.println("Task description cannot be blank :(");
                        word = reader.nextLine();

                    } else {
                        switch (taskType) {
                        case "mark":
                            markTask(taskDescription, tasks);
                            word = reader.nextLine();
                            break;

                        case "unmark":
                            unmarkTask(taskDescription, tasks);
                            word = reader.nextLine();
                            break;

                        case "todo":
                            createTodo(tasks, counter, taskDescription);
                            counter++;
                            word = reader.nextLine();
                            break;

                        case "deadline":
                            createDeadline(tasks, counter, taskDescription);
                            counter++;
                            word = reader.nextLine();
                            break;

                        case "event":
                            createEvent(tasks, counter, taskDescription);
                            counter++;
                            word = reader.nextLine();
                            break;

                        default:
                            System.out.println("That's an invalid task :(");
                            word = reader.nextLine();
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("That's an invalid task :(");
                    word = reader.nextLine();
                }
            }
        }
        System.out.println("Bye");
    }

    private static void createEvent(Task[] tasks, int counter, String taskDescription) {
        int eventStartIndex = taskDescription.indexOf("/");
        int eventEndIndex = taskDescription.lastIndexOf("/");

        String eventDescription = taskDescription.substring(0, eventStartIndex);
        //+6 to eventStartIndex to skip past "/from "
        String eventStart = taskDescription.substring(eventStartIndex+6, eventEndIndex);
        //+4 to eventEndIndex tp skip past "/by "
        String eventEnd = taskDescription.substring(eventEndIndex+4);

        tasks[counter] = new Event(eventDescription, eventStart, eventEnd);
        System.out.println("Created event: " + eventDescription);
    }

    private static void createDeadline(Task[] tasks, int counter, String taskDescription) {
        int deadlineByIndex = taskDescription.indexOf("/");

        String deadlineDescription = taskDescription.substring(0, deadlineByIndex);
        String deadlineBy = taskDescription.substring(deadlineByIndex+4);

        tasks[counter] = new Deadline(deadlineDescription, deadlineBy);
        System.out.println("Created deadline: " + deadlineDescription);
    }

    private static void createTodo(Task[] tasks, int counter, String taskDescription) {
        tasks[counter] = new Todo(taskDescription);
        System.out.println("Created todo: " + taskDescription);
    }

    private static void getList (Task[] tasks, int counter) {
        for (int i = 0; i < counter; i++) {
            System.out.print(i+1);
            String taskType = tasks[i].getType();

            switch (taskType) {
            case "todo":
                System.out.print(".[T][" + tasks[i].getStatusIcon() + "] " + tasks[i].description + "\n");
                break;

            case "deadline":
                System.out.print(".[D][" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                System.out.print(" (by: " + tasks[i].getTimings() + ")\n");
                break;

            case "event":
                System.out.print(".[E][" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                String[] timings = tasks[i].getTimings().split("/");
                System.out.print(" (from: " + timings[0] + " to: " + timings[1] + ")\n");
                break;
            }
        }
    }


    private static void markTask (String taskDescription, Task[] tasks) {
        int taskNo = Integer.parseInt(taskDescription)-1;
        tasks[taskNo].markAsDone();
        System.out.println("This task is done: " + "[" + tasks[taskNo].getStatusIcon() + "] "
                + tasks[taskNo].description);
    }

    private static void unmarkTask(String taskDescription, Task[] tasks) {
        int taskNo = Integer.parseInt(taskDescription)-1;
        tasks[taskNo].unmarkAsDone();
        System.out.println("This task is not done: " + "[" + tasks[taskNo].getStatusIcon() + "] "
                + tasks[taskNo].description);
    }
}
