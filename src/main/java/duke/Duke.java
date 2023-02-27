package duke;

import duke.exceptions.EmptyCommandException;
import duke.exceptions.UnknownCommandException;

import java.io.IOException;

public class Duke {

    public static final String EXIT_PROGRAM = "bye";
    public static final String UNKNOWN_COMAMND_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
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
    public static final String INDEX_DOES_NOT_EXIST_MESSAGE = "Index does not exist";

    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     *
     * Creates the Duke class by initializing the other classes
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        taskList = new TaskList();
    }

    private void run() {
        ui.showWelcomeMessage();
        taskList = storage.load(OUTPUT_FILE);
        starting();
        ui.showEndingMessage();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void starting() {
        String line = parser.getInput();
        while (!line.equals(EXIT_PROGRAM)) {
            try {
                processInput(line);
            } catch (UnknownCommandException e) {
                System.out.println(UNKNOWN_COMAMND_MESSAGE);
            } catch (EmptyCommandException e) {
                System.out.println(EMPTY_COMMAND_MESSAGE);
            } catch (NumberFormatException e) {
                System.out.println(MARK_UNMARK_INDEX_IS_NOT_A_NUMBER_MESSAGE);
            } catch (NullPointerException e) {
                System.out.println(MARK_UNMARK_INDEX_DOES_NOT_EXIST_MESSAGE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(INDEX_DOES_NOT_EXIST_MESSAGE);
            } catch (IOException e) {
                System.out.println(IOEXCEPTION_ERROR_MESSAGE);
            }
            line = parser.getInput();
        }
    }


    private void processInput(String line) throws UnknownCommandException, IOException, EmptyCommandException {
        String[] words = parser.splitCommandsIntoWords(line);
        String command = words[0]; // words[0] is the command, words[n] is the next few words
        switch (command) {
        case TODO_COMMAND:
            Todo td = taskList.createTodo(words);
            ui.printAddTaskMessage(td);
            storage.save(taskList, OUTPUT_FILE);
            break;
        case DEADLINE_COMMAND:
            Deadline d = taskList.createDeadline(words);
            ui.printAddTaskMessage(d);
            storage.save(taskList, OUTPUT_FILE);
            break;
        case EVENT_COMMAND:
            Event e = taskList.createEvent(words);
            ui.printAddTaskMessage(e);
            storage.save(taskList, OUTPUT_FILE);
            break;
        case DELETE_COMMAND:
            deleteTask(words);
            storage.save(taskList, OUTPUT_FILE);
            break;
        case MARK_COMMAND:
            int markIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            taskList.markIndexAsDone(markIndex);
            ui.printMarkedMessage(taskList.getTask(markIndex));
            storage.save(taskList, OUTPUT_FILE);
            break;
        case UNMARK_COMMAND:
            int unmarkIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            taskList.unmarkIndexAsDone(unmarkIndex);
            ui.printUnmarkedMessage(taskList.getTask(unmarkIndex));
            storage.save(taskList, OUTPUT_FILE);
            break;
        case LIST_COMMAND:
            taskList.printList();
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Deletes a task from the TaskList and prints out the task that was deleted
     *
     * @param words original string that the user typed in
     */
    private void deleteTask(String[] words) {
        int deleteIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
        String taskDescription = String.valueOf(taskList.getTask(deleteIndex));
        int taskLeft = taskList.getTask(0).getNumberOfTasks() - 1;
        taskList.deleteTask(deleteIndex);
        ui.printDeleteTaskMessage(taskDescription, taskLeft);
    }

}
