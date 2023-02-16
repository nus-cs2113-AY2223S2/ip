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
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/** Inspiration for Storage class taken from https://github.com/Magmanat/ip */
public class Storage {
    /** File names */
    private static final String FOLDER_NAME = "data";
    private static final String ID_FILENAME = "id.txt";
    private static final String TASK_ORDER_FILENAME = "taskList.txt";
    private static final String SERIAL_NUMBERS_FILENAME = "serialNumbers.txt";
    private static final String TODOS_FILENAME = "toDos.txt";
    private static final String DEADLINES_FILENAME = "deadlines.txt";
    private static final String EVENTS_FILENAME = "events.txt";

    /** Saved Data */
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

    /**
     * Returns a string representing the full path of the file.
     *
     * @param fileName String representing the name of the file.
     * @return A string representing the path of the file.
     */
    private String getFilePath(String fileName) {
        return FOLDER_NAME + "/" + fileName;
    }

    /**
     * Check if the folder that stores the data files is present.
     *
     * @return true if the folder is present, false otherwise.
     */
    private boolean isFolderPresent() {
        File folder = new File(FOLDER_NAME);
        return folder.exists();
    }

    /**
     * Check if the data files are present.
     *
     * @return true if all the data files are present, false otherwise.
     */
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

    /**
     * Check if the data files are empty.
     *
     * @return true if at least 1 of the data file is empty, false otherwise.
     */
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

    /** Create a data folder */
    private void makeFolder() {
        File folder = new File(FOLDER_NAME);
        if (folder.exists()) {
            return;
        }
        folder.mkdir();
    }

    /** Create the ID file */
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

    /** Create the taskOrders file */
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

    /** Create serialNumbers file */
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

    /** Create toDos file */
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

    /** Create deadlines file */
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

    /** Create events file */
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

    /** Loads the value of ID from the ID_FILENAME */
    private void loadID() {
        try {
            Path IDPath = Path.of(getFilePath(ID_FILENAME));
            String IDFileJson = Files.readString(IDPath);
            savedID = Integer.parseInt(IDFileJson);
        } catch (IOException e) {
            handleFileLoadingError();
        }
    }

    /** Loads data from TASK_ORDERS_FILENAME */
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

    /** Loads data from TODOS_FILENAME */
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

    /** Loads data from DEADLINES_FILENAME */
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

    /** Loads data from EVENTS_FILENAME */
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

    /** Loads data from SERIAL_NUMBERS_FILENAME */
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

    /**
     * Loads all data from the data files.
     * @return TaskOrganizer object containing all the data that have been saved.
     * @throws LoadDataException If the function fails to load any of the files.
     */
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

    /**
     * Creates folder and required files to store the data.
     * @return An empty TaskOrganizer object.
     * @throws CreateFileException If the function fails to create any of the files.
     */
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

    /**
     * Checks if there are any previous data saved. If there are previous data it loads the saved data into a
     * TaskOrganizer object. If there are not previous data, it creates a new empty file and returns and empty
     * TaskOrganizer object.
     *
     * @return A TaskOrganizer object to be used in LUKE.
     */
    public TaskOrganizer loadData() {
        boolean isOkToRead = isFolderPresent() && isFilesPresent() && !isEmpty();
        TaskOrganizer loadedData = null;
        if (!isOkToRead) {
            try {
                loadedData = loadNewData();
            } catch (CreateFileException e) {
                handleFileCreationError();
            }
        } else {
            try {
                loadedData = loadPreviousData();
            } catch (LoadDataException e) {
                handleFileLoadingError();
            }
        }
        return loadedData;
    }

    /** Informs the user that there was an error creating a file then exits the program. */
    private void handleFileCreationError() {
        response.printFileCreationError();
        System.exit(-1);
    }

    /** Informs the user that there was an error loading a file then exits the program. */
    private void handleFileLoadingError() {
        response.printFileLoadingError();
        System.exit(-1);
    }

    /** Informs the user that there was an error saving a file then exits the program. */
    public void handleSaveError() {
        response.printFileLoadingError();
        System.exit(-1);
    }

    /** Clear the data files and then loads new data files */
    public void clearFiles() {
        try {
            FileWriter IDFile = new FileWriter(getFilePath(ID_FILENAME));
            FileWriter taskOrderFile = new FileWriter(getFilePath(TASK_ORDER_FILENAME));
            FileWriter serialNumbersFile = new FileWriter(getFilePath(SERIAL_NUMBERS_FILENAME));
            FileWriter toDosFile = new FileWriter(getFilePath(TODOS_FILENAME));
            FileWriter deadlinesFile = new FileWriter(getFilePath(DEADLINES_FILENAME));
            FileWriter eventsFile = new FileWriter(getFilePath(EVENTS_FILENAME));
            IDFile.write("");
            taskOrderFile.write("");
            serialNumbersFile.write("");
            toDosFile.write("");
            deadlinesFile.write("");
            eventsFile.write("");
            loadNewData();
        } catch (IOException e) {
            handleSaveError();
        } catch (CreateFileException e) {
            handleFileCreationError();
        }
    }
}
