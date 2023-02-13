package Tasks;
import Exceptions.DukeException;
import UI.Conversation;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
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
            String todoDescription = arrayOfWords[1].trim();
            tasks.add(new Todo(todoDescription));
            break;
        case "deadline":
            if(!command.contains("/by")) {
                throw new DukeException("OOPS! Use case: deadline X /by Y");
            }
            String[] arrayOfDeadline = arrayOfWords[1].split("/by");
            String deadlineDescription = arrayOfDeadline[0].trim();
            String deadlineDue = arrayOfDeadline[1].trim();
            tasks.add(new Deadline(deadlineDescription, deadlineDue));
            break;
        case "event":
            if(!command.contains("/from") || !command.contains("/to")) {
                throw new DukeException("OOPS! Use case: event X /from Y /to Z");
            }
            String[] arrayOfEvent = arrayOfWords[1].split("/from");
            String eventDescription = arrayOfEvent[0];
            String eventStart = arrayOfEvent[1].split("/to", 2)[0].trim();
            String eventEnd = arrayOfEvent[1].split("/to", 2)[1].trim();
            tasks.add(new Event(eventDescription, eventStart, eventEnd));
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.print("added: ");
        Conversation.copy(command);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }
    public static void mark(String command) throws DukeException {
        try {
            String[] arrOfCommand = command.split(" ");
            int taskNumber = Integer.parseInt(arrOfCommand[1]) - 1;
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber).fullDescription());
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
        } catch(Exception error) {
            throw new DukeException("Use case: delete ITEM_NUMBER");
        }
    }
    public static void runDuke(boolean isContinue) {
        while(isContinue) {
            String command = Conversation.readCommand();
            String firstKeyword = command.split(" ")[0];
            switch (firstKeyword) {
            case "list":
                TaskList.listTasks();
                break;
            case "mark":
                try {
                    TaskList.mark(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "unmark":
                try {
                    TaskList.unmark(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    TaskList.delete(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "bye":
                Conversation.farewell();
                isContinue = false;
                break;
            default:
                try {
                    TaskList.addToList(command);
                } catch (DukeException error) {
                    System.out.println(error.getMessage());
                }
                break;
            }
        }
    }
}
