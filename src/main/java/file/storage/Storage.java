package file.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.Deadline;
import utility.Ui;

/**
 * Represents the Storage class, where it is used to deal with file and storage matters.
 * It sets up the locally stored file first.
 * It will check for the file in the default save path. If it does not exist, it will create the file and directory.
 * If the local copy exists, it will load it and be incorporated into this session (DukeSession).
 * After the user exits, it will also save the file to the default save path.
 */
public class Storage {
    private static final String DEFAULT_SAVE_PATH = "data/savedList.txt";
    private static final String DEFAULT_DIRECTORY_NAME = "data";
    private static final String FILE_CORRUPTED_MESSAGE = "File has been corrupted";
    private static final String ERROR_MESSAGE = "Something went wrong: ";
    private static final String DEFAULT_TODO_SYMBOL = "t";
    private static final String DEFAULT_DEADLINE_SYMBOL = "d";
    private static final String DEFAULT_EVENT_SYMBOL = "e";
    private static final String DEFAULT_MARKED_TASK_SYMBOL = "X";

    private static File directoryAndFileChecker() throws IOException {
        File f = new File(DEFAULT_SAVE_PATH);
        File directory = new File(DEFAULT_DIRECTORY_NAME);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
        return f;
    }

    /**
     * It will call the method directoryAndFileChecker to ensure that all file and directory conflicts are resolved.
     * After that, it reads the file and parses the data it loaded.
     * Other setup methods are called to load different tasks and ensure that this session is same as the saved
     * local text file.
     *
     * @param actions This contains the arraylist of Tasks.
     */
    public static void setUpFile(ArrayList<Task> actions) {
        try {
            File f = directoryAndFileChecker();
            String line;
            String[] decisions;
            Scanner in = new Scanner(f);
            while (in.hasNext()) {
                line = in.nextLine();
                decisions = line.split(Ui.DEFAULT_FLAG_SEPARATOR);
                switch (decisions[0]) {
                case DEFAULT_TODO_SYMBOL:
                    setUpToDo(decisions, actions);
                    break;
                case DEFAULT_DEADLINE_SYMBOL:
                    setUpDeadline(decisions, actions);
                    break;
                case DEFAULT_EVENT_SYMBOL:
                    setUpEvent(decisions, actions);
                    break;
                default:
                    Ui.print(FILE_CORRUPTED_MESSAGE);
                }
            }
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
    }

    /**
     * Saves any recorded information of this current DukeSession.
     * The arraylist in DukeSession is saved into a default save path in a text file.
     *
     * @param actions This contains the arraylist of Tasks.
     */
    public static void saveFile(ArrayList<Task> actions) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_SAVE_PATH);
            for (Task i : actions) {
                fw.write(i.getSaveCommand() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
        }
    }

    private static void setUpToDo(String[] decisions, ArrayList<Task> actions) {
        Task toBeAdded = new Todo(decisions[2]);
        if (decisions[1].equals(DEFAULT_MARKED_TASK_SYMBOL)) {
            toBeAdded.mark();
        }
        actions.add(toBeAdded);
    }

    private static void setUpDeadline(String[] decisions, ArrayList<Task> actions) {
        Task toBeAdded = new Deadline(decisions[2], decisions[3]);
        if (decisions[1].equals(DEFAULT_MARKED_TASK_SYMBOL)) {
            toBeAdded.mark();
        }
        actions.add(toBeAdded);
    }

    private static void setUpEvent(String[] decisions, ArrayList<Task> actions) {
        Task toBeAdded = new Event(decisions[2], decisions[3], decisions[4]);
        if (decisions[1].equals(DEFAULT_MARKED_TASK_SYMBOL)) {
            toBeAdded.mark();
        }
        actions.add(toBeAdded);
    }
}
