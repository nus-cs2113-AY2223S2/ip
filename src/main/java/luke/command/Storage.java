package luke.command;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import luke.exception.CreateFileException;
import luke.exception.LoadDataException;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class Storage {
    /** File names */
    private static final String FOLDER_NAME = "./data";
    private static final String ID_FILENAME = "id.txt";
    private static final String TASK_ORDER_FILENAME = "taskList.txt";
    private static final String SERIAL_NUMBERS_FILENAME = "serialNumbers.txt";
    private static final String TODOS_FILENAME = "toDos.txt";
    private static final String DEADLINES_FILENAME = "deadlines.txt";
    private static final String EVENTS_FILENAME = "events.txt";

    private int savedID;
    private HashMap<Integer, Task> savedTaskOrder;
    private HashMap<Integer, ToDo> savedToDos;
    private HashMap<Integer, Deadline> savedDeadlines;
    private HashMap<Integer, Event> savedEvents;
    private HashMap<Integer, Integer> savedSerialNumbers;

    private Response response;

    public Storage() {
       response = new Response();
    }

    private String getFilePath(String fileName) {
        return FOLDER_NAME + "/" + fileName;
    }

    private boolean isFolderPresent() {
        File folder = new File(FOLDER_NAME);
        return folder.exists();
    }
    private boolean isFilesPresent() {
        File IDFile = new File(getFilePath(ID_FILENAME));
        File taskOrderFile = new File(getFilePath(TASK_ORDER_FILENAME));
        File serialNumbersFile = new File(getFilePath(SERIAL_NUMBERS_FILENAME));
        File toDosFile = new File(getFilePath(TODOS_FILENAME));
        File deadlinesFile = new File(getFilePath(DEADLINES_FILENAME));
        File eventsFile = new File(getFilePath(EVENTS_FILENAME));
        return IDFile.exists() && taskOrderFile.exists() && serialNumbersFile.exists()
                && toDosFile.exists() && deadlinesFile.exists() && eventsFile.exists();
    }

    private boolean isEmpty() {
        File IDFile = new File(getFilePath(ID_FILENAME));
        File taskOrderFile = new File(getFilePath(TASK_ORDER_FILENAME));
        File serialNumbersFile = new File(getFilePath(SERIAL_NUMBERS_FILENAME));
        File toDosFile = new File(getFilePath(TODOS_FILENAME));
        File deadlinesFile = new File(getFilePath(DEADLINES_FILENAME));
        File eventsFile = new File(getFilePath(EVENTS_FILENAME));
        return (IDFile.length() == 0 || taskOrderFile.length() == 0 || serialNumbersFile.length() == 0
                || toDosFile.length() == 0 || deadlinesFile.length() == 0 || eventsFile.length() == 0);
    }

    private void makeFolder() {
        File folder = new File(FOLDER_NAME);
        if (folder.exists()) {
            return;
        }
        folder.mkdir();
    }

    private void makeIDFile() {
        File IDFile = new File(getFilePath(ID_FILENAME));
        if (IDFile.exists()) {
            return;
        }
        try {
            IDFile.createNewFile();
        }
        catch (IOException e) {
            handleFileCreationError();
        }
    }

    private void makeTaskOrdersFile() {
        File taskOrderFile = new File(getFilePath(TASK_ORDER_FILENAME));
        if (taskOrderFile.exists()) {
            return;
        }
        try {
            taskOrderFile.createNewFile();
        }
        catch (IOException e) {
            handleFileCreationError();
        }
    }

    private void makeToDosFile() {
        File toDosFile = new File(getFilePath(TODOS_FILENAME));
        if (toDosFile.exists()) {
            return;
        }
        try {
            toDosFile.createNewFile();
        }
        catch (IOException e) {
            handleFileCreationError();
        }
    }

    private void makeDeadlinesFile() {
        File deadlinesFile = new File(getFilePath(DEADLINES_FILENAME));
        if (deadlinesFile.exists()) {
            return;
        }
        try {
            deadlinesFile.createNewFile();
        }
        catch (IOException e) {
            handleFileCreationError();
        }
    }

    private void makeSerialNumbersFile() {
        File serialNumbersFile = new File(getFilePath(SERIAL_NUMBERS_FILENAME));
        if (serialNumbersFile.exists()) {
            return;
        }
        try {
            serialNumbersFile.createNewFile();
        }
        catch (IOException e) {
            handleFileCreationError();
        }
    }

    private void makeEventsFile() {
        File eventsFile = new File(getFilePath(EVENTS_FILENAME));
        if (eventsFile.exists()) {
            return;
        }
        try {
            eventsFile.createNewFile();
        }
        catch (IOException e) {
            handleFileCreationError();
        }
    }

    private void loadID() {
        try {
            Path IDPath = Path.of(getFilePath(ID_FILENAME));
            String IDFileJson = Files.readString(IDPath);
            savedID = Integer.parseInt(IDFileJson);
        } catch (IOException e) {
            handleFileLoadingError();
        }
    }

    private void loadTaskOrders() {
        try {
            Type taskOrdersType = new TypeToken<HashMap<Integer, Task>>() {}.getType();
            Path taskOrderPath = Path.of(getFilePath(TASK_ORDER_FILENAME));
            String taskOrderJson = Files.readString(taskOrderPath);
            savedTaskOrder = new Gson().fromJson(taskOrderJson, taskOrdersType);
        } catch (IOException e) {
            handleFileLoadingError();
        }
    }

    private void loadToDos() {
        try {
            Type toDosType = new TypeToken<HashMap<Integer, ToDo>>(){}.getType();
            Path toDosPath = Path.of(getFilePath(TODOS_FILENAME));
            String toDosJson = Files.readString(toDosPath);
            savedToDos = new Gson().fromJson(toDosJson, toDosType);
        } catch (IOException e) {
           handleFileLoadingError();
        }
    }

    private void loadDeadlines() {
        try {
            Type deadlinesType = new TypeToken<HashMap<Integer, Deadline>>(){}.getType();
            Path deadlinesPath= Path.of(getFilePath(DEADLINES_FILENAME));
            String deadlinesJson = Files.readString(deadlinesPath);
            savedDeadlines = new Gson().fromJson(deadlinesJson, deadlinesType);
        } catch (IOException e) {
            handleFileLoadingError();
        }
    }

    private void loadEvents() {
        try {
            Type eventsType = new TypeToken<HashMap<Integer, Event>>(){}.getType();
            Path eventsPath = Path.of(getFilePath(EVENTS_FILENAME));
            String eventsJson = Files.readString(eventsPath);
            savedEvents = new Gson().fromJson(eventsJson, eventsType);
        } catch (IOException e) {
            handleFileCreationError();
        }
    }
    private void loadSerialNumbers() {
        try {
            Type serialNumbersType = new TypeToken<HashMap<Integer, Integer>>(){}.getType();
            Path serialNumbersPath = Path.of(getFilePath(SERIAL_NUMBERS_FILENAME));
            String serialNumbersJson = Files.readString(serialNumbersPath);
            savedSerialNumbers = new Gson().fromJson(serialNumbersJson, serialNumbersType);
        } catch (IOException e) {
            handleFileLoadingError();
        }
    }

    /** Loads in the taskID and tasks hashmap that is stored in the txt files */
    private TaskOrganizer loadPreviousData() throws LoadDataException {
        loadID();
        loadSerialNumbers();
        loadTaskOrders();
        loadToDos();
        loadDeadlines();
        loadEvents();
        return new TaskOrganizer(savedID, savedTaskOrder, savedToDos, savedDeadlines, savedEvents,
                savedSerialNumbers);
    }

    private TaskOrganizer loadNewData() throws CreateFileException {
        makeFolder();
        makeIDFile();
        makeSerialNumbersFile();
        makeTaskOrdersFile();
        makeToDosFile();
        makeDeadlinesFile();
        makeEventsFile();
        return new TaskOrganizer();
    }

    public TaskOrganizer loadData() {
        try {
            boolean isOkToRead = isFolderPresent() && isFilesPresent() && isEmpty();
            if (!isOkToRead) {
                return loadNewData();
            }
            return loadPreviousData();
        } catch (LoadDataException e) {
            handleFileLoadingError();
        } catch (CreateFileException e) {
            handleFileCreationError();
        }
        return new TaskOrganizer();
    }

    private void handleFileCreationError() {
        response.printFileCreationError();
        System.exit(-1);
    }

    private void handleFileLoadingError() {
        response.printFileLoadingError();
        System.exit(-1);
    }

    public void handleSaveError() {
        response.printFileLoadingError();
        System.exit(-1);
    }
}
