import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hi, I'm bob");
        System.out.println("What's up");
        String word = reader.nextLine();

        while (!word.equals("bye")) {

            if (word.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks!");
                } else {
                    getList(tasks);
                }
                word = reader.nextLine();

            //mark, unmark, or add
            } else {
                int taskDescriptionIndex = word.indexOf(" ");
                String taskType = word.substring(0, taskDescriptionIndex);
                String taskDescription = word.substring(taskDescriptionIndex+1);

                switch (taskType) {
                case "delete":
                    deleteTask(taskDescription, tasks);
                    word = reader.nextLine();
                    break;

                case "mark":
                    markTask(taskDescription, tasks);
                    word = reader.nextLine();
                    break;

                case "unmark":
                    unmarkTask(taskDescription, tasks);
                    word = reader.nextLine();
                    break;

                case "todo":
                    createTodo(tasks, taskDescription);
                    word = reader.nextLine();
                    break;

                case "deadline":
                    createDeadline(tasks, taskDescription);
                    word = reader.nextLine();
                    break;

                case "event":
                    createEvent(tasks, taskDescription);
                    word = reader.nextLine();
                    break;

                default:
                    System.out.println("That's an invalid task :(");
                    word = reader.nextLine();
                }

            }
        }
        System.out.println("Bye");
    }

    private static void createEvent(ArrayList<Task> tasks, String taskDescription) {
        int eventStartIndex = taskDescription.indexOf("/");
        int eventEndIndex = taskDescription.lastIndexOf("/");

        String eventDescription = taskDescription.substring(0, eventStartIndex);
        //+6 to eventStartIndex to skip past "/from "
        String eventStart = taskDescription.substring(eventStartIndex+6, eventEndIndex);
        //+4 to eventEndIndex tp skip past "/by "
        String eventEnd = taskDescription.substring(eventEndIndex+4);

        tasks.add(new Event(eventDescription, eventStart, eventEnd));
        System.out.println("Created event: " + eventDescription);
    }

    private static void createDeadline(ArrayList<Task> tasks, String taskDescription) {
        int deadlineByIndex = taskDescription.indexOf("/");

        String deadlineDescription = taskDescription.substring(0, deadlineByIndex);
        String deadlineBy = taskDescription.substring(deadlineByIndex+4);

        tasks.add(new Deadline(deadlineDescription, deadlineBy));
        System.out.println("Created deadline: " + deadlineDescription);
    }

    private static void createTodo(ArrayList<Task> tasks, String taskDescription) {
        tasks.add(new Todo(taskDescription));
        System.out.println("Created todo: " + taskDescription);
    }

    private static void getList (ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i+1);
            String taskType = tasks.get(i).getType();

            switch (taskType) {
            case "todo":
                System.out.print("[T][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description + "\n");
                break;

            case "deadline":
                System.out.print("[D][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
                System.out.print(" (by: " + tasks.get(i).getTimings() + ")\n");
                break;

            case "event":
                System.out.print("[E][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).description);
                String[] timings = tasks.get(i).getTimings().split("/");
                System.out.print(" (from: " + timings[0] + " to: " + timings[1] + ")\n");
                break;
            }
        }
    }


    private static void markTask (String taskDescription, ArrayList<Task> tasks) {
        int taskNo = Integer.parseInt(taskDescription)-1;
        tasks.get(taskNo).markAsDone();
        System.out.println("This task is done: " + "[" + tasks.get(taskNo).getStatusIcon() + "] "
                + tasks.get(taskNo).description);
    }

    private static void unmarkTask(String taskDescription, ArrayList<Task> tasks) {
        int taskNo = Integer.parseInt(taskDescription)-1;
        tasks.get(taskNo).unmarkAsDone();
        System.out.println("This task is not done: " + "[" + tasks.get(taskNo).getStatusIcon() + "] "
                + tasks.get(taskNo).description);
    }

    private static void deleteTask(String taskDescription, ArrayList<Task> tasks) {
        if (tasks.size() > 0) {
            int taskNo = Integer.parseInt(taskDescription) - 1;

            System.out.println("Got it! This task will be removed:");
            String taskType = tasks.get(taskNo).getType();
            switch (taskType) {
            case "todo":
                System.out.print(".[T][" + tasks.get(taskNo).getStatusIcon() + "] " + tasks.get(taskNo).description + "\n");
                break;

            case "deadline":
                System.out.print(".[D][" + tasks.get(taskNo).getStatusIcon() + "] " + tasks.get(taskNo).description);
                System.out.print(" (by: " + tasks.get(taskNo).getTimings() + ")\n");
                break;

            case "event":
                System.out.print(".[E][" + tasks.get(taskNo).getStatusIcon() + "] " + tasks.get(taskNo).description);
                String[] timings = tasks.get(taskNo).getTimings().split("/");
                System.out.print(" (from: " + timings[0] + " to: " + timings[1] + ")\n");
                break;
            }

            tasks.remove(taskNo);
            System.out.println("Number of tasks left: " + tasks.size());
        } else {
            System.out.println("There are no tasks in the list to remove!");
        }
    }
}
