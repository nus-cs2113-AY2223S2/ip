import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    final static String FILE_PATH = "data/duke.txt";
    final static String DIRECTORY_PATH = "data";
    final static int TASK_TYPE_INDEX = 0;
    final static int TASK_NAME_INDEX = 1;
    final static int TASK_IS_DONE_INDEX = 2;
    final static int TASK_DEADLINE_INDEX = 3;
    final static int TASK_EVENT_START_TIME_INDEX = 3;
    final static int TASK_EVENT_END_TIME_INDEX = 4;
    final static int EMPTY_FILE = 0;

    /**
     * Saves the userTasks in taskList to a given directory and file.
     * Each task is saved according to the following format
     * [TaskType],TaskName,Boolean (whether task is done),date information.
     *
     * @param taskList TaskList object containing the user tasks.
     * @throws IOException If directory path or file path is not present, and program fails
     * to create a new directory or file path.
     */
    public static void saveExistingTasksToFile(TaskList taskList) throws IOException {
        ArrayList<Task> userTasks = taskList.getUserTasksArrayList();
        String saveFilePath = FILE_PATH;
        if (userTasks.size() == EMPTY_FILE) { // fixs the bug that the last task in the file cannot be deleted
            writeToFile(saveFilePath, "", false);
        }
        for (int i = 0; i < userTasks.size(); i++) {
            Task currentTask = userTasks.get(i);
            Boolean isAppendMode = true;
            if (i == 0) {
                isAppendMode = false;
            }
            if (currentTask instanceof Todo) {
                writeToFile(saveFilePath, "T , " + currentTask.getTaskName() + " , " + currentTask.getisDone() + System.lineSeparator(), isAppendMode);
            } else if (currentTask instanceof Deadline) {
                writeToFile(saveFilePath, "D , " + currentTask.getTaskName() + " , " + currentTask.getisDone() + " , " + ((Deadline) currentTask).deadline +
                        System.lineSeparator(), isAppendMode);
            } else { // current task is instance of event
                writeToFile(saveFilePath, "E , " + currentTask.getTaskName() + " , " + currentTask.getisDone() + " , " + ((Event) currentTask).startTime +
                        " , " + ((Event) currentTask).endTime + System.lineSeparator(), isAppendMode);
            }
        }
    }

    /**
     * Writes given string to a given file location.
     *
     * @param filePath file path of save file relative to project location.
     * @param textToAdd string of text to be added to save file.
     * @param isAppendMode Boolean variable to use to indicate whether appendmode for FileWriter is used to overwrite existing data.
     * @throws IOException If file does not exist in the given file path, or FileWriter fails to write to given save file.
     */
    public static void writeToFile(String filePath, String textToAdd, Boolean isAppendMode) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, isAppendMode);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    /**
     * Retrieve existing user tasks in the form of a TaskList object from a given save file.
     * Reads and processes user tasks from save file according to the following format:
     * [TaskType],TaskName,Boolean (whether task is done),date information.
     *
     * @param filePath file path of save file relative to project location.
     * @return TaskList object containing userTasks.
     * @throws FileNotFoundException If file does not exist at the given file path.
     */
    public static TaskList retrieveExistingTasksFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> userTasks = new ArrayList<>();
        TaskList taskList = new TaskList(userTasks);
        while (scanner.hasNext()) {
            String Line = scanner.nextLine();
            String[] taskInformationWords = Line.split(",");
            String taskType = taskInformationWords[TASK_TYPE_INDEX].trim();
            if (taskType.equals("T")) {
                loadTodoTaskFromSaveFile(taskList, taskInformationWords);
            } else if (taskType.equals("D")) {
                loadDeadlineTaskFromSaveFile(taskList, taskInformationWords);
            } else if (taskType.equals("E")) {
                loadEventTaskFromSaveFile(taskList, taskInformationWords);
            }
        }
        return taskList;
    }

    /**
     * Processes and returns Event task type from a given array of string.
     *
     * @param taskList TaskList object containing the user tasks.
     * @param taskInformationWords Array of String containing Event task information.
     */
    public static void loadEventTaskFromSaveFile(TaskList taskList, String[] taskInformationWords) {
        String taskName = taskInformationWords[TASK_NAME_INDEX].trim();
        Boolean isDone = false;
        if (taskInformationWords[TASK_IS_DONE_INDEX].trim().equals("true")) {
            isDone = true;
        }
        String taskEventStartTime = taskInformationWords[TASK_EVENT_START_TIME_INDEX].trim();
        String taskEventEndTime = taskInformationWords[TASK_EVENT_END_TIME_INDEX].trim();
        Event newEventTask = new Event(taskName, taskEventStartTime, taskEventEndTime);
        newEventTask.setisDone(isDone);
        taskList.addUserTask(newEventTask);
    }

    /**
     * Processes and returns Deadline task type from a given array of string.
     *
     * @param taskList TaskList object containing the user tasks.
     * @param taskInformationWords Array of String containing Deadline task information.
     */
    public static void loadDeadlineTaskFromSaveFile(TaskList taskList, String[] taskInformationWords) {
        String taskName = taskInformationWords[TASK_NAME_INDEX].trim();
        Boolean isDone = false;
        if (taskInformationWords[TASK_IS_DONE_INDEX].trim().equals("true")) {
            isDone = true;
        }
        String taskDeadline = taskInformationWords[TASK_DEADLINE_INDEX].trim();
        Deadline newDeadlineTask = new Deadline(taskName, taskDeadline);
        newDeadlineTask.setisDone(isDone);
        taskList.addUserTask(newDeadlineTask);
    }

    /**
     * Processes and returns Todo task type from a given array of string.
     *
     * @param taskList TaskList object containing the user tasks.
     * @param taskInformationWords Array of String containing Todo task information.
     */
    public static void loadTodoTaskFromSaveFile(TaskList taskList, String[] taskInformationWords) {
        String taskName = taskInformationWords[TASK_NAME_INDEX].trim();
        Boolean isDone = false;
        if (taskInformationWords[TASK_IS_DONE_INDEX].trim().equals("true")) {
            isDone = true;
        }
        Todo newTodoTask = new Todo(taskName);
        newTodoTask.setisDone(isDone);
        taskList.addUserTask(newTodoTask);
    }

    /**
     * Checks whether a given save directory exists.
     *
     * @param DIRECTORY_PATH path of directory containing save file.
     * @return Returns true if directory exists in the given directory path, False otherwise.
     */
    public static boolean isSaveDirectoryPresent(String DIRECTORY_PATH) {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            return true;
        }
        return false;
    }

    /**
     * Creates directory at a given directory path.
     *
     * @param DIRECTORY_PATH path of directory containing save file.
     */
    public static void createSaveFileDirectory(String DIRECTORY_PATH) {
        File file = new File(DIRECTORY_PATH);
        if (file.mkdir() == true) {
            System.out.println("Directory successfully created at [project_root]/" + DIRECTORY_PATH);

        } else {
            System.out.println("Failed to create directory, data might be lost");
        }
    }

    /**
     * Saves user tasks from taskList into the save file at the directory path and file path.
     *
     * @param DIRECTORY_PATH path of directory containing save file.
     * @param FILE_PATH file path of save file relative to project location.
     * @param taskList TaskList object containing the user tasks.
     * @throws IOException If directory path or file path is not present, and program fails
     * to create a new directory or file path.
     */
    public static void saveData(String DIRECTORY_PATH, String FILE_PATH, TaskList taskList) throws IOException {
        if (isSaveDirectoryPresent(DIRECTORY_PATH) == false) {
            createSaveFileDirectory(DIRECTORY_PATH);
            if (isSaveDirectoryPresent(DIRECTORY_PATH) == false) {
                throw new IOException();
            }
        }
        saveExistingTasksToFile(taskList);
    }
}
