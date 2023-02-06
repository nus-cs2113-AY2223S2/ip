// import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static String getFirstWord(String s) {
        String[] words = s.split(" ");
        return words[0];
    }

    public static String getSecondWord(String s) {
        int index = s.indexOf(" ");
        String sub = s.substring(index + 1);
        return sub;
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);
        boolean isComplete = false;

        while (!isComplete) {

            String line = in.nextLine();
            String firstWord = getFirstWord(line);
            Task[] taskList = TaskManager.getTaskList();

            switch (firstWord) {
            case "mark":
                TaskManager.markTask(taskList, Integer.parseInt(getSecondWord(line)));
                break;
            case "unmark":
                TaskManager.unmarkTask(taskList, Integer.parseInt(getSecondWord(line)));
                break;
            case "list":
                TaskManager.listTask();
                break;
            case "todo":
                getTodoDetails(line);
                break;
            case "deadline":
                getDeadlineDetails(line);
                break;
            case "event":
                getEventDetails(line);
                break;
            case "bye":
                isComplete = true;
                break;
            default:
                System.out.println("Unrecognized command. Please try again.");
                break;
            }
        }

        in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void getTodoDetails(String line) {
        String todoLine = getSecondWord(line);
        Todo todoTask = new Todo(todoLine);
        TaskManager.addTask(todoTask);
        System.out.println("Got it. I've added this task:\n" + todoTask.describeTask());
        System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }

    private static void getDeadlineDetails(String line) {
        String deadlineLine = getSecondWord(line);
        int index = deadlineLine.indexOf("/");
        String deadlineDescription = deadlineLine.substring(0, index - 1);
        String deadlineDate = deadlineLine.substring(index + 4);
        Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
        TaskManager.addTask(deadlineTask);
        System.out.println("Got it. I've added this task:\n" + deadlineTask.describeTask());
        System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }

    private static void getEventDetails(String line) {
        String eventLine = getSecondWord(line);
        int index2 = eventLine.indexOf("/");
        String eventDescription = eventLine.substring(0, index2 - 1);
        String eventDate = eventLine.substring(index2 + 4);
        int index3 = eventDate.indexOf("/");
        String eventFrom = eventDate.substring(2, index3 - 1);
        String eventTo = eventDate.substring(index3 + 4);
        Event eventTask = new Event(eventDescription, eventFrom, eventTo);
        TaskManager.addTask(eventTask);
        System.out.println("Got it. I've added this task:\n" + eventTask.describeTask());
        System.out.println("\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.\n");
    }
}
