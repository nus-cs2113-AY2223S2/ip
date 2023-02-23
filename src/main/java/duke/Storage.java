package duke;

import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;
import duke.exception.InvalidSaveFile;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Task list
import static duke.task.TaskList.allTasks;

/**
 * Storage class that initialises the task list and updates the save file.
 *
 * Each task is saved as a line in the save file in this format:
 *   [type] | [status] | [description]
 *
 * Followed by:
 *   /by [date]                  for Deadlines or
 *   /from [date] /to [date]     for Events
 */
public class Storage {

    // ints indicating position of terms in each line of the save file
    private static final int TYPE_POS = 0;
    private static final int STATUS_POS = 4;
    private static final int PARAM_POS = 8;
    // chars representing type of Tasks within the save file
    private static final char TXT_TODO_WORD = 'T';
    private static final char TXT_DEADLINE_WORD = 'D';
    private static final char TXT_EVENT_WORD = 'E';

    // Location of save file
    protected static String filePath;

    /**
     * Initialise Storage class, set filePath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Overwrites the existing save file based on the current ArrayList of Tasks allTasks.
     *
     * @throws IOException If something goes wrong during the overwriting process
     */
    public static void update() throws IOException {
        FileWriter overwrite = new FileWriter(filePath);
        for (Task task : allTasks) {
            String desc = task.getDescription();
            String type = task.getType();
            String stat = task.getStatus();
            switch (type) {
            case "todo":
                overwrite.write("T | " + stat + " | " + desc + "\n");
                break;
            case "deadline":
                Deadline tempDeadline = (Deadline) task;
                String by = tempDeadline.getBy(Task.storePattern);
                overwrite.write("D | " + stat + " | " + desc + " /by " + by + "\n");
                break;
            case "event":
                Event tempEvent = (Event) task;
                String from = tempEvent.getFrom(Task.storePattern);
                String to = tempEvent.getTo(Task.storePattern);
                overwrite.write("E | " + stat + " | " + desc + " /from " + from + " /to " + to + "\n");
                break;
            }
        }
        overwrite.close();
    }

    /**
     * Loads data from the save file and initialises it into a new ArrayList.
     * If save file is not found, create a new save file and return an empty ArrayList.
     *
     * @return ArrayList of Tasks (either containing data in save file or empty)
     * @throws IOException If save file is not found, and a new one cannot be created
     */
    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> newAllTasks = new ArrayList<>();
        File save = new File(filePath);
        try {
            newAllTasks = readFileContents(save);
            return newAllTasks;
        } catch (FileNotFoundException e) {
            Ui.printErrorFileNotFound();
            save.createNewFile();
            return newAllTasks;
        }
    }

    /**
     * Using a Scanner, read each line of the save file and initialise them as Tasks in a new ArrayList.
     *
     * @param save Save file
     * @return ArrayList of Tasks based on data written in the save file
     * @throws FileNotFoundException If the save file cannot be found at filePath
     */
    private static ArrayList<Task> readFileContents(File save) throws FileNotFoundException {
        Scanner s = new Scanner(save);
        ArrayList<Task> newArrayList = new ArrayList<>();
        int counter = 0;
        while (s.hasNext()) {
            try {
                newArrayList.add(newTask(s.nextLine()));
                counter++;
            } catch (InvalidSaveFile e) {
                Ui.printInvalidSaveFile(counter, filePath);
            }
        }
        return newArrayList;
    }

    /**
     * Takes a line from the save file, interprets it, and returns it as a new Task.
     *
     * @param text Line from the save file
     * @return Corresponding Task class based on data from text
     * @throws InvalidSaveFile If any line in the input data is not of the right format
     */
    private static Task newTask(String text) throws InvalidSaveFile {
        char type = getType(text);
        Boolean isDone = isStatusDone(text);
        String param = getParam(text);
        switch (type) {
        case TXT_TODO_WORD:
            return newToDo(isDone, param);
        case TXT_DEADLINE_WORD:
            return newDeadline(isDone, param);
        case TXT_EVENT_WORD:
            return newEvent(isDone, param);
        default:
            throw new InvalidSaveFile();
        }
    }

    private static char getType(String text) {
        return text.charAt(TYPE_POS);
    }

    private static Boolean isStatusDone(String text) {
        if (text.charAt(STATUS_POS) == 'X') {
            return true;
        }
        return false;
    }

    private static String getParam(String text) {
        return text.substring(PARAM_POS);
    }

    private static ToDo newToDo(Boolean isDone, String param) {
        ToDo newToDo = new ToDo(param);
        newToDo.setDone(isDone);
        return newToDo;
    }

    private static Deadline newDeadline(Boolean isDone, String param) throws InvalidSaveFile {
        final String[] paramAndBy;
        try {
            paramAndBy = Parser.parseDeadline(param);
        } catch (InvalidDeadline e) {
            throw new InvalidSaveFile();
        }
        Deadline newDeadline = new Deadline(paramAndBy[0], paramAndBy[1]);
        newDeadline.setDone(isDone);
        return newDeadline;
    }

    private static Event newEvent(Boolean isDone, String param) throws InvalidSaveFile {
        final String[] paramAndFromTo;
        try {
            paramAndFromTo = Parser.parseEvent(param);
        } catch (InvalidEvent e) {
            throw new InvalidSaveFile();
        }
        Event newEvent = new Event(paramAndFromTo[0], paramAndFromTo[1], paramAndFromTo[2]);
        newEvent.setDone(isDone);
        return newEvent;
    }

}
