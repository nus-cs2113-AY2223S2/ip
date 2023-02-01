import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i += 1){
            System.out.println((i + 1) + ". " + tasks.get(i).fullDescription());
        }
    }
    public static void addToList(String command) {
        String[] arrayOfWords = command.split(" ", 2);
        switch(arrayOfWords[0]) {
        case "todo":
            tasks.add(new Todo(arrayOfWords[1]));
            break;
        case "deadline":
            String[] arrayOfDeadline = arrayOfWords[1].split("/by");
            tasks.add(new Deadline(arrayOfDeadline[0].trim(), arrayOfDeadline[1].trim()));
            break;
        case "event":
            String[] arrayOfEvent = arrayOfWords[1].split("/from");
            String eventDescription = arrayOfEvent[0];
            String eventStart = arrayOfEvent[1].split("/to", 2)[0].trim();
            String eventEnd = arrayOfEvent[1].split("/to", 2)[1].trim();
            tasks.add(new Event(eventDescription, eventStart, eventEnd));
            break;
        default:
            tasks.add(new Task(command));
        }
        System.out.print("added: ");
        Conversation.copy(command);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }
    public static void mark(String command) {
        String[] arrOfCommand = command.split(" ");
        try {
            Integer.parseInt(arrOfCommand[1]);
        } catch(Exception error) {
            TaskList.addToList(command);
            return;
        }
        int taskNumber = Integer.parseInt(arrOfCommand[1]) - 1;
        tasks.get(taskNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber).fullDescription());
    }
    public static void unmark(String command) {
        String[] arrOfCommand = command.split(" ");
        try {
            Integer.parseInt(arrOfCommand[1]);
        } catch(Exception error) {
            TaskList.addToList(command);
            return;
        }
        int taskNumber = Integer.parseInt(arrOfCommand[1]) - 1;
        tasks.get(taskNumber).markAsNotDone();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber).fullDescription());
    }
}
