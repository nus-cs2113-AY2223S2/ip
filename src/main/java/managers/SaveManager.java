package managers;

import errors.InvalidDeadlineException;
import errors.InvalidEventException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static tasks.Deadline.DEADLINE_CLASS;
import static tasks.Event.EVENT_CLASS;
import static tasks.ToDo.TODO_CLASS;

/**
 * Represents a manager for saving the contents of the current list to a txt file,
 * of the name PATHNAME, or reading the content from that file.
 */
public class SaveManager {

    public static final String PATHNAME = "data.txt";
    public static final int SAVED_DATA_BEGIN_INDEX = 3;

    // Storing format:
    // T0 name
    // D0 name /by time
    // E0 name /from time /to time

    /**
     * Reads the contents that are stored into an ArrayList of Tasks.
     *
     * @return An ArrayList containing Tasks from the stored txt document.
     * @throws IOException If failed to create a File.
     * @throws InvalidDeadlineException If a command for constructing Deadline Object is in incorrect format.
     * @throws InvalidEventException If a command for constructing Event Object is in incorrect format.
     */
    public ArrayList<Task> initialiseData()
            throws IOException, InvalidDeadlineException, InvalidEventException {
        File f = new File(PATHNAME);
        ArrayList<Task> storedTasks = new ArrayList<>();
            if (f.createNewFile()) {
                return storedTasks;
            }
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                processStoredTask(storedTasks, line);
            }
        return storedTasks;
    }

    /**
     * Write all the items of the current ArrayList of tasks into a txt file for storage.
     *
     * @param tasks The manager for Tasks where the list of Tasks is stored.
     * @throws IOException If the FileWriter cannot be generated.
     */
    public void saveCurrentState (TaskManager tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(PATHNAME, true);
        FileWriter fileClearer = new FileWriter(PATHNAME);
        fileClearer.write("");
        fileClearer.close();
        String taskState = "";
        for (Task taskItem : tasks.getTasksList()) {
            String toWrite = "";
            if (taskItem.isDone()) {
                taskState = "1";
            } else {
                taskState = "0";
            }
            toWrite = taskItem.getClassType() + taskState + taskItem.getToStore();
            fileWriter.write(toWrite + System.lineSeparator());
        }
        fileWriter.close();
    }

    /**
     * Write an additional line to the document where the data is saved for when a new
     * Task is added.
     * @param newTask The Task that is newly added.
     * @throws IOException If the FileWriter cannot be generated.
     */
    public void saveNewTask (Task newTask) throws IOException {
        FileWriter fileWriter = new FileWriter(PATHNAME, true);
        String toWrite = "";
        String taskState = "0";
        toWrite = newTask.getClassType() + taskState + newTask.getToStore();
        fileWriter.write(toWrite + System.lineSeparator());
        fileWriter.close();
    }

    private void processStoredTask(ArrayList<Task> storedTasks, String line)
            throws InvalidDeadlineException, InvalidEventException {
        switch (line.substring(0,1)) {
        case TODO_CLASS:
            ToDo toDo = new ToDo(line.substring(SAVED_DATA_BEGIN_INDEX));
            setMarkState(line, toDo);
            storedTasks.add(toDo);
            break;
        case DEADLINE_CLASS:
            Deadline deadline = new Deadline(line.substring(SAVED_DATA_BEGIN_INDEX));
            setMarkState(line, deadline);
            storedTasks.add(deadline);
            break;
        case EVENT_CLASS:
            Event event = new Event(line.substring(SAVED_DATA_BEGIN_INDEX));
            setMarkState(line, event);
            storedTasks.add(event);
            break;
        }
    }

    private void setMarkState(String line, Task task) {
        if (Integer.parseInt(line.substring(1,2)) == 1) {
            task.markAsState(true);
        } else {
            task.markAsState(false);
        }
    }
}
