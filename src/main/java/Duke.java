import allexceptions.todoMissingException;
import allexceptions.wrongCommandException;
import alltasks.Deadline;
import alltasks.Event;
import alltasks.Task;
import alltasks.ToDo;
import alltasks.Storage;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class represents Coffee Bot that helps users to keep track of their personal schedules.
 * Coffee Bot takes in commands by users to keep track of Todos, Deadlines and events tasks.
 */
public class Duke {
    // testing the merge-pr branch
    // testing the merge pull request
    // comments
    public static void main(String[] arguments) {
        ArrayList<Task> listItems = new ArrayList<>();
        int index = 0;
        System.out.println("Hi there! My name is Coffee");
        System.out.println("How can I help you today? :)");
        Task[] taskArray;
        taskArray = new Task[110];
        boolean isRun = true;
        //Solution below adapted and reused from Student Oh Yi Xiu Wilson
        // with modifications made by Wilson Lee Jun Wei
        Storage storage = new Storage(listItems);
        listItems.addAll(storage.get_Tasks_From_File());
        //@@ Student Oh Yi Xiu Wilson
        while (isRun) {
            Scanner command = new Scanner(System.in);
            String inputCommand = command.nextLine();
            String[] firstWordArray;
            firstWordArray = inputCommand.split(" ", 2);
            String firstWord = firstWordArray[0];
            String[] tokens;

            switch (firstWord) {
            case "todo": // e.g. todo borrow book
                try {
                    if (firstWordArray.length == 1) {
                        throw new todoMissingException();
                    }
                    ToDo todoWord = new ToDo(firstWordArray[1]);
                    listItems.add(todoWord);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todoWord);
                    System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                    } catch (todoMissingException exception){
                     System.out.println("OH NO!!!! The description of a todo cannot be empty.");
                }
                break;
            case "delete":
                System.out.println("Noted. I've removed this task:");
                Task task = listItems.remove(Integer.parseInt(firstWordArray[1]) - 1);
                System.out.println(task);
                System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                break;
            case "find":
                System.out.println("Here are the matching keywords in your list:");
                for (int i = 0; i < listItems.size(); i++) {
                    if (listItems.get(i).getDescription().contains(firstWordArray[1])) {
                        System.out.print(i + 1);
                        System.out.print(". ");
                        System.out.println(listItems.get(i));
                    }
                }
                break;
            case "deadline":
                String[] getWeekday;
                getWeekday = firstWordArray[1].split("/by", 2);
                Deadline deadlineWord = new Deadline(getWeekday[0], getWeekday[1]);
                listItems.add(deadlineWord);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadlineWord + " (by:" + getWeekday[1] + ")");
                System.out.println("Now you have " + listItems.size() + " tasks in the list.");
                listItems.add(new Task(inputCommand));
                break;
            case "event":
                String[] getMeeting;
                getMeeting = firstWordArray[1].split("/", 3);
                String[] meetingFrom;
                String[] meetingTo;
                meetingFrom = getMeeting[1].split(" ", 2);
                meetingTo = getMeeting[2].split(" ", 2);
                Event meetingType = new Event(getMeeting[0], meetingFrom[1], meetingTo[1]);
                listItems.add(meetingType);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + meetingType);
                System.out.println("Now you have " + listItems.size()+ " tasks in the list.");
                listItems.add(new Task(inputCommand));
                break;
            case "mark":
                tokens = inputCommand.split(" ");
                index = Integer.parseInt(tokens[1]);
                listItems.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + listItems.get(index - 1));
                break;
            case "unmark":
                tokens = inputCommand.split(" ");
                index = Integer.parseInt(tokens[1]);
                listItems.get(index - 1).markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + listItems.get(index - 1));
                break;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < listItems.size(); i = i + 1) {
                    Task item = listItems.get(i);
                    System.out.println((i + 1) + ". " + item);
                }
                break;
            case "bye":
                System.out.println("I look forward to seeing you again! Goodbye!");
                isRun = false;
                return;
            default:
                try {
                    throw new wrongCommandException();
                } catch (wrongCommandException exception) {
                    System.out.println("OH NO!!! My deepest apologies, but I don't understand what that means :(");
                }
            }
            //Solution below adapted and reused from Student Oh Yi Xiu Wilson
            // with modifications made by Wilson Lee Jun Wei
            storage.writeTasksToFile();
            //@@ Student Oh Yi Xiu Wilson
        }
    }
}
