package duke;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.UnknownCommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String EXIT_PROGRAM = "bye";
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
    public static final String BY_DELIMITER = " /by ";
    public static final String FROM_DELIMITER = " /from ";
    public static final String TO_DELIMITER = " /to ";
    public static final String MARKED_THIS_TASK_AS_DONE = "Nice! I've marked this task as done: ";
    public static final String UNMARKED_THIS_TASK_AS_DONE = "Okay, I've marked this task as not done yet: ";
    public static final String UNKNOWN_COMAMND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String DELETED_THIS_TASK = "Noted. I've removed this task:";
    public static final String MARK_UNMARK_INDEX_IS_NOT_A_NUMBER_MESSAGE = "mark/unmark index is not a number";
    public static final String MARK_UNMARK_INDEX_DOES_NOT_EXIST_MESSAGE = "mark/unmark index does not exist";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String LIST_COMMAND = "list";
    public static final String DELETE_COMMAND = "delete";
    public static final String EMPTY_COMMAND_MESSAGE = "Command is empty!";
    public static final String OUTPUT_FILE = "outputfile.txt";
    public static final String IOEXCEPTION_ERROR_MESSAGE = "IOException Error";
    public static final String FILE_NOT_FOUND_MESSAGE = "File Not Found";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        Duke.logo();
        Duke.greeting();
        Duke.load(OUTPUT_FILE);
        Duke.starting();
        Duke.ending();

    }

    private static void starting() {
        Scanner input = new Scanner(System.in);
        String line = "";

        line = input.nextLine();
        while (!line.equals(EXIT_PROGRAM)) {
            try {
                Duke.processInput(line);
            } catch (UnknownCommandException e) {
                System.out.println(UNKNOWN_COMAMND_MESSAGE);
            } catch (EmptyCommandException e) {
                System.out.println(EMPTY_COMMAND_MESSAGE);
            } catch (NumberFormatException e) {
                System.out.println(MARK_UNMARK_INDEX_IS_NOT_A_NUMBER_MESSAGE);
            } catch (NullPointerException e) {
                System.out.println(MARK_UNMARK_INDEX_DOES_NOT_EXIST_MESSAGE);
            } catch (IOException e) {
                System.out.println(IOEXCEPTION_ERROR_MESSAGE);
            }
            line = input.nextLine();
        }
    }

    public static void logo() {
        System.out.println(WELCOME_MESSAGE + LOGO);
    }

    public static void greeting() {
        System.out.println(DIVIDER + GREETINGS + DIVIDER);
    }

    public static void ending() {
        System.out.println(ENDING + DIVIDER);
        System.exit(0);
    }

    public static void printAddTaskMessage(Task t) {
        System.out.println(ADDED_TASK + t + "\nNow you have " + t.getNumberOfTasks() + " tasks in the list.");
    }

    public static void processInput(String line) throws UnknownCommandException, IOException, EmptyCommandException {

        String[] words = line.split(" ", 2);
        String command = words[0];
        // words[0] is the command, words[n] is the next few words
        checkIfCommandEmpty(words);
        switch (command) {
        case TODO_COMMAND:
            Todo td = getTodo(words);
            tasks.add(td);
            printAddTaskMessage(td);
            save(OUTPUT_FILE);
            break;
        case DEADLINE_COMMAND:
            Deadline d = getDeadline(words);
            tasks.add(d);
            printAddTaskMessage(d);
            save(OUTPUT_FILE);
            break;
        case EVENT_COMMAND:
            Event e = getEvent(words);
            tasks.add(e);
            printAddTaskMessage(e);
            save(OUTPUT_FILE);
            break;
        case DELETE_COMMAND:
            deleteTask(words);
            save(OUTPUT_FILE);
            break;
        case MARK_COMMAND:

            int markIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            tasks.get(markIndex).markAsDone();
            System.out.println(MARKED_THIS_TASK_AS_DONE + "\n" + tasks.get(markIndex));
            save(OUTPUT_FILE);
            break;
        case UNMARK_COMMAND:
            int unmarkIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            tasks.get(unmarkIndex).markAsNotDone();
            System.out.println(UNMARKED_THIS_TASK_AS_DONE + "\n" + tasks.get(unmarkIndex));
            save(OUTPUT_FILE);
            break;
        case LIST_COMMAND:
            printList();
            break;

        default:
            throw new UnknownCommandException();
        }

    }

    private static void deleteTask(String[] words) {
        int deleteIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
        String taskDescription = String.valueOf(tasks.get(deleteIndex));
        Task toDelete = tasks.get(deleteIndex);
        toDelete.remove();
        tasks.remove(deleteIndex);
        System.out.println(DELETED_THIS_TASK + "\n" + taskDescription + "\nNow you have " + tasks.get(0).getNumberOfTasks() + " tasks in the list.");
    }

    private static void checkIfCommandEmpty(String[] words) throws EmptyCommandException {
        if (words[0].equals(LIST_COMMAND)) {
            return;
        }
        if (words.length < 2 || words[1].equals("")) {
            throw new EmptyCommandException();
        }
    }

    private static Todo getTodo(String[] words) {
        Todo td = new Todo(words[1]);
        return td;
    }

    private static void printList() {
        int listIndex = 0;
        for (Task t : tasks) {
            if (t != null) {
                System.out.println(++listIndex + ". " + t);
            }
        }
    }


    private static void writeTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : tasks) {
            if (t != null) {
                fw.write(t.saveFormat() + System.lineSeparator());
            }
        }

        fw.close();
    }

    private static void save(String filename) throws IOException {
        try {
            writeTasksToFile(filename);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void load(String filename) {
        File f = new File(filename);

        if (f.exists()) {
            try {
                loadFileContents(f);
            } catch (FileNotFoundException e) {
                System.out.println(FILE_NOT_FOUND_MESSAGE);
            }
        }
    }

    private static void loadFileContents(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            processFileContents(s.nextLine());
        }
    }

    private static void processFileContents(String line){
        String[] words = line.split("\\|");
        String type = words[0];
        boolean isDone = false;
        if (Integer.valueOf(words[1]) == 1) {
            isDone = true;
        }
        switch (type) {
        case "T":
            loadTodo(words, isDone);
            break;
        case "E":
            loadEvent(words, isDone);
            break;
        case "D":
            loadDeadline(words, isDone);
            break;
        default:
            // unknown char error
        }
    }

    private static void loadDeadline(String[] words, boolean isDone) {
        Deadline deadline = new Deadline(words[2], words[3]);
        if(isDone){
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    private static void loadEvent(String[] words, boolean isDone) {
        Event event = new Event(words[2], words[3], words[4]);
        if(isDone){
            event.markAsDone();
        }
        tasks.add(event);
    }

    private static void loadTodo(String[] words, boolean isDone) {
        Todo todo = new Todo(words[2]);
        if(isDone){
            todo.markAsDone();
        }
        tasks.add(todo);
    }

    private static Deadline getDeadline(String[] words) {
        String line;
        line = words[1]; // to remove the command
        String[] deadlineDetails = line.split(BY_DELIMITER);
        Deadline d = new Deadline(deadlineDetails[0], deadlineDetails[1]);
        return d;
    }

    private static Event getEvent(String[] words) {
        String line;
        line = words[1]; // to remove the command
        String[] eventDetails = line.split(FROM_DELIMITER);
        String eventName = eventDetails[0];
        String from = line.substring(line.indexOf(FROM_DELIMITER) + FROM_DELIMITER.length(), line.indexOf(TO_DELIMITER));
        eventDetails = line.split(TO_DELIMITER);
        String to = eventDetails[1];
        Event e = new Event(eventName, from, to);
        return e;
    }

}
