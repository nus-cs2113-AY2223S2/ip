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
    public static final String FILE_NOT_FOUND_MESSAGE = "File Not Found";

    private Ui ui;
    private Parser parser;
    private TaskList taskList;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        taskList = new TaskList();
    }

    public void run() {
        ui.showWelcomeMessage();
        load(OUTPUT_FILE);
        starting();
        ui.showEndingMessage();
        System.exit(0);
    }

    public static void main(String[] args) {

        new Duke().run();

    }

    private void starting() {
        Scanner input = new Scanner(System.in);
        String line = "";

        line = input.nextLine();
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
            } catch (IOException e) {
                System.out.println(IOEXCEPTION_ERROR_MESSAGE);
            }
            line = input.nextLine();
        }
    }


    public void processInput(String line) throws UnknownCommandException, IOException, EmptyCommandException {

        String[] words = line.split(" ", 2);
        String command = words[0];
        // words[0] is the command, words[n] is the next few words
        checkIfCommandEmpty(words);
        switch (command) {
        case TODO_COMMAND:
            Todo td = taskList.createTodo(words);
            ui.printAddTaskMessage(td);
            save(OUTPUT_FILE);
            break;
        case DEADLINE_COMMAND:
            Deadline d = taskList.createDeadline(words);
            ui.printAddTaskMessage(d);
            save(OUTPUT_FILE);
            break;
        case EVENT_COMMAND:
            Event e = taskList.createEvent(words);
            ui.printAddTaskMessage(e);
            save(OUTPUT_FILE);
            break;
        case DELETE_COMMAND:
            deleteTask(words);
            save(OUTPUT_FILE);
            break;
        case MARK_COMMAND:
            int markIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            taskList.markIndexAsDone(markIndex);
            ui.printMarkedMessage(taskList.getTask(markIndex));
            save(OUTPUT_FILE);
            break;
        case UNMARK_COMMAND:
            int unmarkIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
            taskList.unmarkIndexAsDone(unmarkIndex);
            ui.printUnmarkedMessage(taskList.getTask(unmarkIndex));
            save(OUTPUT_FILE);
            break;
        case LIST_COMMAND:
            taskList.printList();
            break;

        default:
            throw new UnknownCommandException();
        }

    }

    private void deleteTask(String[] words) {
        int deleteIndex = Integer.parseInt(words[1]) - 1; // 0 indexing
        String taskDescription = String.valueOf(taskList.getTask(deleteIndex));
        int taskLeft = taskList.getTask(0).getNumberOfTasks() - 1;
        taskList.deleteTask(deleteIndex);
        ui.printDeleteTaskMessage(taskDescription, taskLeft);
    }

    private static void checkIfCommandEmpty(String[] words) throws EmptyCommandException {
        if (words[0].equals(LIST_COMMAND)) {
            return;
        }
        if (words.length < 2 || words[1].equals("")) {
            throw new EmptyCommandException();
        }
    }


    private void writeTasksToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> tasks = taskList.getTaskList();
        for (Task t : tasks) {
            if (t != null) {
                fw.write(t.saveFormat() + System.lineSeparator());
            }
        }

        fw.close();
    }

    private void save(String filename) throws IOException {
        try {
            writeTasksToFile(filename);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void load(String filename) {
        File f = new File(filename);

        if (f.exists()) {
            try {
                loadFileContents(f);
            } catch (FileNotFoundException e) {
                System.out.println(FILE_NOT_FOUND_MESSAGE);
            }
        }
    }

    private void loadFileContents(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            processFileContents(s.nextLine());
        }
    }

    private void processFileContents(String line) {
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

    private void loadDeadline(String[] words, boolean isDone) {
        Deadline deadline = new Deadline(words[2], words[3]);
        if (isDone) {
            deadline.markAsDone();
        }
        taskList.addDeadline(deadline);
    }

    private void loadEvent(String[] words, boolean isDone) {
        Event event = new Event(words[2], words[3], words[4]);
        if (isDone) {
            event.markAsDone();
        }
        taskList.addEvent(event);
    }

    private void loadTodo(String[] words, boolean isDone) {
        Todo todo = new Todo(words[2]);
        if (isDone) {
            todo.markAsDone();
        }
        taskList.addTodo(todo);
    }


}
