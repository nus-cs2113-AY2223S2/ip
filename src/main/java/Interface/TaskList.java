package Interface;

import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Getter for ArrayList of tasks
     *
     * @return ArrayList of tasks of task type
     */
    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists out full descriptions of all tasks in list of tasks
     */
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i += 1){
            System.out.println((i + 1) + ". " + tasks.get(i).fullDescription());
        }
    }

    /**
     * Parses the command for a task's specific type and adds it the list of tasks accordingly
     *
     * @param command user input to add a task
     * @throws DukeException thrown as a warning message if input is incorrect
     */
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
                throw new DukeException("OOPS! Use case: deadline X /by yyyy-mm-dd (e.g. 2019-10-15)");
            }
            addDeadline(arrayOfWords);
            break;
        case "event":
            if(!command.contains("/from") || !command.contains("/to")) {
                throw new DukeException("OOPS! Use case: event X /from Y /to Z");
            }
            addEvent(arrayOfWords);
            break;
        case "find":
            if(arrayOfWords.length != 2) {
                throw new DukeException("OOPS! Use case: find KEYWORD");
            }
            findTask(arrayOfWords[1]);
            break;
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
        Storage.writeToFile();
    }

    /**
     * Adds event into list of tasks
     * @param arrayOfWords String array of event input
     */
    private static void addEvent(String[] arrayOfWords) {
        String[] arrayOfEvent = arrayOfWords[1].split("/from");
        String eventDescription = arrayOfEvent[0];
        String eventStart = arrayOfEvent[1].split("/to", 2)[0].trim();
        String eventEnd = arrayOfEvent[1].split("/to", 2)[1].trim();
        tasks.add(new Event(eventDescription, eventStart, eventEnd));
        System.out.print("added new event: ");
        System.out.println(eventDescription + "from " + eventStart + " to " + eventEnd);
    }

    /**
     * Adds deadline into list of tasks
     * @param arrayOfWords String array of deadline input
     */
    private static void addDeadline(String[] arrayOfWords) {
        try {
            String[] arrayOfDeadline = arrayOfWords[1].split("/by");
            String deadlineDescription = arrayOfDeadline[0].trim();
            String deadlineDue = arrayOfDeadline[1].trim();
            LocalDate dueDate = LocalDate.parse(deadlineDue);
            deadlineDue = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            tasks.add(new Deadline(deadlineDescription, dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))));
            System.out.print("added new deadline: ");
            System.out.println(deadlineDescription + " due " + deadlineDue);
        } catch (DateTimeParseException e) {
            System.out.println("OOPS! Use case: deadline X /by yyyy-mm-dd (e.g. 2019-10-15)");
        }
    }

    /**
     * Adds todo into list of tasks
     * @param arrayOfWords String array of todo input
     */
    private static void addTodo(String[] arrayOfWords) {
        String todoDescription = arrayOfWords[1].trim();
        tasks.add(new Todo(todoDescription));
        System.out.print("added new todo: ");
        System.out.println(todoDescription);
    }

    /**
     * Convert user input to the task id that is to be marked and sets the task to done
     *
     * @param command user input to mark a task
     * @throws DukeException thrown as a warning message if input is incorrect
     */
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

    /**
     * Convert user input to the task id that is to be unmarked and sets the task to not done
     *
     * @param command user input to unmark a task
     * @throws DukeException thrown as a warning message if input is incorrect
     */
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

    /**
     * Convert user input to the task id that is to be deleted and removes the entry from the
     * list of tasks
     *
     * @param command user input to delete as task
     * @throws DukeException thrown as a warning message if input is incorrect
     */
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

    /**
     * Outputs list of tasks that have descriptions that match the user's keyword onto command line
     *
     * @param keyword user input as a keyword for the task
     * @throws DukeException thrown as a warning message if input is incorrect
     */
    public static void findTask(String keyword) throws DukeException {
        try {
            keyword = keyword.trim().toLowerCase();
            String finalKeyword = keyword;
            ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                    .filter(t -> t.getDescription().trim().toLowerCase().contains(finalKeyword))
                    .collect(toList());
            System.out.println("Here are the matching tasks in your list: ");
            int i = 1;
            for(Task t : filteredList) {
                System.out.println((i) + ". " + t.fullDescription());
                i += 1;
            }
        } catch(Exception error) {
            throw new DukeException("Error searching for a match");
        }
    }
}
