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

public class SaveManager {

    public static final String PATHNAME = "data.txt";
    public static final int SAVED_DATA_BEGIN_INDEX = 3;

    // Storing format:
    // T0 name
    // D0 name /by time
    // E0 name /from time /to time
    public ArrayList<Task> initialiseData()
            throws IOException, InvalidDeadlineException, InvalidEventException {
        File f = new File(PATHNAME);
        ArrayList<Task> storedTasks = new ArrayList<>();
            if (f.createNewFile()) {
                return storedTasks;
            }
//        } catch (IOException e) {
//            System.out.println("Error occurred when creating new file.");
//        }
//        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                processStoredTask(storedTasks, line);
            }
//        } catch (FileNotFoundException e) {
//            return storedTasks;
//        } catch (InvalidDeadlineException | InvalidEventException e) {
//            System.out.println("Stored data is corrupted.");
//        }

        return storedTasks;
    }

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

    public void saveNewTask (Task newTask) throws IOException {
        FileWriter fileWriter = new FileWriter(PATHNAME, true);
        String toWrite = "";
        String taskState = "0";
        toWrite = newTask.getClassType() + taskState + newTask.getToStore();
        fileWriter.write(toWrite + System.lineSeparator());
        fileWriter.close();
    }

    private static void processStoredTask(ArrayList<Task> storedTasks, String line)
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

    private static void setMarkState(String line, Task task) {
        if (Integer.parseInt(line.substring(1,2)) == 1) {
            task.markAsState(true);
        } else {
            task.markAsState(false);
        }
    }
}
