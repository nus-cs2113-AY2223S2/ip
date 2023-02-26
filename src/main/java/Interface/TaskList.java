package Interface;
import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static ArrayList<Task> getTasks() {
        return tasks;
    }
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i += 1){
            System.out.println((i + 1) + ". " + tasks.get(i).fullDescription());
        }
    }
    public static void addToList(String command) throws DukeException {
        String[] arrayOfWords = command.split(" ", 2);
        String commandWord = arrayOfWords[0];
        switch(commandWord) {
        case "todo":
            if(arrayOfWords.length < 2) {
                throw new DukeException("OOPS! Use case: todo TASK");
            }
            addTodo(arrayOfWords);
            break;
        case "deadline":
            if(!command.contains("/by")) {
                throw new DukeException("OOPS! Use case: deadline X /by Y");
            }
            addDeadline(arrayOfWords);
            break;
        case "event":
            if(!command.contains("/from") || !command.contains("/to")) {
                throw new DukeException("OOPS! Use case: event X /from Y /to Z");
            }
            addEvent(arrayOfWords);

            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.print("added: ");
        Ui.copy(command);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        Storage.writeToFile();
    }
    private static void addEvent(String[] arrayOfWords) {
        String[] arrayOfEvent = arrayOfWords[1].split("/from");
        String eventDescription = arrayOfEvent[0];
        String eventStart = arrayOfEvent[1].split("/to", 2)[0].trim();
        String eventEnd = arrayOfEvent[1].split("/to", 2)[1].trim();
        tasks.add(new Event(eventDescription, eventStart, eventEnd));
    }
    private static void addDeadline(String[] arrayOfWords) {
        String[] arrayOfDeadline = arrayOfWords[1].split("/by");
        String deadlineDescription = arrayOfDeadline[0].trim();
        String deadlineDue = arrayOfDeadline[1].trim();
        tasks.add(new Deadline(deadlineDescription, deadlineDue));
    }
    private static void addTodo(String[] arrayOfWords) {
        String todoDescription = arrayOfWords[1].trim();
        tasks.add(new Todo(todoDescription));
    }
    public static void mark(String command) throws DukeException {
        try {
            String[] arrOfCommand = command.split(" ");
            int taskNumber = Integer.parseInt(arrOfCommand[1]) - 1;
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber).fullDescription());
            Storage.writeToFile();
        } catch(Exception error) {
            throw new DukeException("Use case: mark ITEM_NUMBER");
        }
    }
    public static void unmark(String command) throws DukeException {
        try {
            String[] arrOfCommand = command.split(" ");
            int taskNumber = Integer.parseInt(arrOfCommand[1]) - 1;
            tasks.get(taskNumber).markAsNotDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber).fullDescription());
            Storage.writeToFile();
        } catch(Exception error) {
            throw new DukeException("Use case: unmark ITEM_NUMBER");
        }
    }
    public static void delete(String command) throws DukeException {
        try {
            String[] arrOfCommand = command.split(" ");
            int taskNumber = Integer.parseInt(arrOfCommand[1]) - 1;
            String removalDescription = tasks.get(taskNumber).fullDescription();
            tasks.remove(tasks.get(taskNumber));
            System.out.println("Noted. I've removed this task:");
            System.out.println(removalDescription);
            System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
            Storage.writeToFile();
        } catch(Exception error) {
            throw new DukeException("Use case: delete ITEM_NUMBER");
        }
    }
}
