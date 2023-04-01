import allexceptions.deadlineMissingException;
import allexceptions.deleteMissingException;
import allexceptions.todoMissingException;
import allexceptions.wrongCommandException;
import allexceptions.eventMissingException;
import allexceptions.findMissingException;
import alltasks.Deadline;
import alltasks.Event;
import alltasks.Task;
import alltasks.ToDo;


/**
 * This class represents Coffee Bot that accepts users' input commands and process the input commands
 * for further actions required by Coffee Bot
 */
public class Parser {
    private TaskList listItems;

    Parser(TaskList listTasks) {
        this.listItems = listTasks;
    }

    String parseInput(String inputCommand) {
        int index = 0;
        StringBuilder outputMessage = new StringBuilder();
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
                listItems.addTask(todoWord);
                outputMessage.append("Got it. I've added this task:\n");
                outputMessage.append("  " + todoWord + "\n");
                outputMessage.append("Now you have " + listItems.size() + " tasks in the list.");
            } catch (todoMissingException exception) {
                System.out.print("OH NO!!!! The description of a todo cannot be empty.");
            }
            break;
        case "delete":
            try {
                if (firstWordArray.length == 1) {
                    throw new deleteMissingException();
                }
                outputMessage.append("Noted. I've removed this task:\n");
                Task task = listItems.deleteTask(Integer.parseInt(firstWordArray[1]) - 1);
                outputMessage.append(task + "\n");
                outputMessage.append("Now you have " + listItems.size() + " tasks in the list.");
            } catch (deleteMissingException deleteException){
                System.out.print("OH NO!!!! The description of a delete cannot be empty.");
             }
            break;
        case "find":
            try {
                if (firstWordArray.length == 1) {
                    throw new findMissingException();
                }
                outputMessage.append(this.listItems.findTasks(firstWordArray[1]));
            } catch (findMissingException findException) {
                System.out.print("OH NO!!!! The description of a find cannot be empty.");
            }
            break;
        case "deadline":
            try {
                if (firstWordArray.length == 1) {
                    throw new deadlineMissingException();
                }
                String[] getWeekday;
                getWeekday = firstWordArray[1].split("/by", 2);
                Deadline deadlineWord = new Deadline(getWeekday[0], getWeekday[1]);
                listItems.addTask(deadlineWord);
                outputMessage.append("Got it. I've added this task:\n");
                outputMessage.append("  " + deadlineWord + "\n");
                outputMessage.append("Now you have " + listItems.size() + " tasks in the list.");
            } catch (deadlineMissingException deadlineException) {
                System.out.print("OH NO!!!! The description of a deadline cannot be empty.");
            }
            break;
        case "event":
            try {
                if (firstWordArray.length == 1) {
                    throw new eventMissingException();
                }
                String[] getEvent;
                String[] eventFrom;
                String[] eventTo;
                getEvent = firstWordArray[1].split("/", 3);
                eventFrom = getEvent[1].split(" ", 2);
                eventTo = getEvent[2].split(" ", 2);
                Event eventDetails = new Event(getEvent[0], eventFrom[1], eventTo[1]);
                listItems.addTask(eventDetails);
                outputMessage.append("Got it. I've added this task:\n");
                outputMessage.append("  " + eventDetails + "\n");
                outputMessage.append("Now you have " + listItems.size() + " tasks in the list.");
            } catch (eventMissingException eventException) {
                System.out.print("OH NO!!!! The description of an event cannot be empty.");
            }
            break;
        case "mark":
            tokens = inputCommand.split(" ");
            index = Integer.parseInt(tokens[1]);
            listItems.getTask(index - 1).markAsDone();
            outputMessage.append("Nice! I've marked this task as done:\n");
            outputMessage.append(" " + listItems.getTask(index - 1));
            break;
        case "unmark":
            tokens = inputCommand.split(" ");
            index = Integer.parseInt(tokens[1]);
            listItems.getTask(index - 1).markAsNotDone();
            outputMessage.append("OK, I've marked this task as not done yet:\n");
            outputMessage.append(" " + listItems.getTask(index - 1));
            break;
        case "list":
            outputMessage.append(this.listItems.listTasks());
            break;
        default:
            try {
                throw new wrongCommandException();
            } catch (wrongCommandException exception) {
                System.out.print("OH NO!!! My deepest apologies, but I don't understand what that means :(");
            }
        }
        return outputMessage.toString();
    }
}