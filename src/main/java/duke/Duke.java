package duke;

import duke.exceptions.EmptyMarkException;
import duke.exceptions.EmptyTodoException;
import duke.exceptions.EmptyUnmarkException;
import duke.exceptions.UnknownCommandException;

import java.util.Scanner;

public class Duke {

    public static final String EXIT_PROGRAM = "bye";
    public static final int TASK_ARRAY_SIZE = 100;
    public static final String LOGO = "   _____  .__   _____                   .___\n" +
            "  /  _  \\ |  |_/ ____\\______   ____   __| _/\n" +
            " /  /_\\  \\|  |\\   __\\\\_  __ \\_/ __ \\ / __ |\n" +
            "/    |    \\  |_|  |   |  | \\/\\  ___// /_/ |\n" +
            "\\____|__  /____/__|   |__|    \\___  >____ |\n" +
            "        \\/                        \\/     \\/\n";
    public static final String WELCOME_MESSAGE = "Hello from\n";
    public static final String DIVIDER = "____________________________________________________________\n";
    public static final String GREETINGS = " Hello! I'm Alfred Pennyworth.\n What can I do for you?\n";
    public static final String ENDING = " Bye. Hope to see you again soon!\n";
    public static final String ADDED_TASK = "Got it. I've added this task:\n  ";
    public static final String BY_DELIMITER = "/by ";
    public static final String FROM_DELIMITER = "/from ";
    public static final String TO_DELIMITER = "/to ";
    public static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done: ";
    public static final String UNMARKED_THIS_TASK_AS_DONE = "Okay, I've marked this task as not done yet: ";
    public static final String UNKNOWN_COMAMND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_TODO_MESSAGE = "OOPS!!! The description of a todo cannot be empty.";
    public static final String EMPTY_UNMARK_MESSAGE = "OOPS!!! The index of unmark cannot be empty.";
    public static final String EMPTY_MARK_MESSAGE = "OOPS!!! The index of mark cannot be empty.";
    public static final String MARK_UNMARK_INDEX_IS_NOT_A_NUMBER_MESSAGE = "Mark/Unmark Index is not a number";
    public static final String MARK_UNMARK_INDEX_DOES_NOT_EXIST_MESSAGE = "mark/unmark index does not exist";

    public static void main(String[] args) {

        Duke.logo();
        Duke.greeting();

        Scanner input = new Scanner(System.in);
        String line = "";

        Task[] tasks = new Task[TASK_ARRAY_SIZE];
        line = input.nextLine();
        while (!line.equals(EXIT_PROGRAM)) {
            try {
                Duke.processInput(line, tasks);
            } catch (UnknownCommandException e) {
               System.out.println(UNKNOWN_COMAMND_MESSAGE);
            } catch (EmptyTodoException e){
                System.out.println(EMPTY_TODO_MESSAGE);
            } catch (EmptyMarkException e){
                System.out.println(EMPTY_MARK_MESSAGE);
            } catch (EmptyUnmarkException e) {
                System.out.println(EMPTY_UNMARK_MESSAGE);
            } catch (NumberFormatException e) {
                System.out.println(MARK_UNMARK_INDEX_IS_NOT_A_NUMBER_MESSAGE);
            } catch (NullPointerException e) {
                System.out.println(MARK_UNMARK_INDEX_DOES_NOT_EXIST_MESSAGE);
            }
            line = input.nextLine();
        }

        Duke.ending();
        System.exit(0);

    }

    public static void logo() {
        System.out.println(WELCOME_MESSAGE + LOGO);
    }

    public static void greeting() {
        System.out.println(DIVIDER + GREETINGS + DIVIDER);
    }

    public static void ending() {
        System.out.println(ENDING + DIVIDER);
    }

    public static void printAddTaskMessage(Task t) {
        System.out.println(ADDED_TASK + t + "\nNow you have " + t.getNumberOfTasks() + " tasks in the list.");
    }

    public static void processInput(String line, Task[] tasks) throws UnknownCommandException, EmptyTodoException, EmptyMarkException, EmptyUnmarkException{

        String[] words = line.split(" ", 2);
        String command = words[0];
        int tasksCount = 0;
        if (tasks[0] != null) {
            tasksCount = tasks[0].getNumberOfTasks();
        }
        // words[0] is the command, words[n] is the next few words

        switch (command) {
        case "todo":
            checkIfTodoEmpty(words);
            Task td = new Todo(words[1]);
            tasks[tasksCount] = td;
            printAddTaskMessage(td);
            break;
        case "deadline":
            line = words[1]; // to remove the command
            String[] deadlineDetails = line.split(BY_DELIMITER);
            Task d = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            tasks[tasksCount] = d;
            printAddTaskMessage(d);
            break;
        case "event":
            line = words[1]; // to remove the command
            String[] eventDetails = line.split(FROM_DELIMITER);
            String eventName = eventDetails[0];
            String from = line.substring(line.indexOf(FROM_DELIMITER) + FROM_DELIMITER.length(), line.indexOf(TO_DELIMITER));
            eventDetails = line.split(TO_DELIMITER);
            String to = eventDetails[1];
            Task e = new Event(eventName, from, to);
            tasks[tasksCount] = e;
            printAddTaskMessage(e);
            break;

        case "mark":
            CheckIfMarkEmpty(words);
            int markIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            tasks[markIndex].markAsDone();
            System.out.println(MARKED_THIS_TASK_AS_DONE);
            System.out.println(tasks[markIndex]);
            break;
        case "unmark":
            CheckIfUnmarkEmpty(words);
            int unmarkIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            tasks[unmarkIndex].markAsNotDone();
            System.out.println(UNMARKED_THIS_TASK_AS_DONE);
            System.out.println(tasks[unmarkIndex]);
            break;
        case "list":
            int listIndex = 0;
            for (Task t : tasks) {
                if (t != null) {
                    System.out.println(++listIndex + ". " + t);
                }
            }
            break;

        default:
            throw new UnknownCommandException();
        }
    }

    private static void CheckIfMarkEmpty(String[] words) throws EmptyMarkException {
        if(words.length < 2 || words[1].equals("")){
            throw new EmptyMarkException();
        }
    }

    private static void CheckIfUnmarkEmpty(String[] words) throws EmptyUnmarkException {
        if(words.length < 2 || words[1].equals("")){
            throw new EmptyUnmarkException();
        }
    }

    private static void checkIfTodoEmpty(String[] words) throws EmptyTodoException {
        if(words.length < 2 || words[1].equals("")){
            throw new EmptyTodoException();
        }
    }
}
