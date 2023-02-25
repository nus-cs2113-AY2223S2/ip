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

    public static void writeToFile(String filePath, String textToAdd, Boolean isAppendMode) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, isAppendMode);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

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

    public static boolean isSaveDirectoryPresent(String DIRECTORY_PATH) {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            return true;
        }
        return false;
    }

    public static void createSaveFileDirectory(String DIRECTORY_PATH) {
        File file = new File(DIRECTORY_PATH);
        if (file.mkdir() == true) {
            System.out.println("Directory successfully created at [project_root]/" + DIRECTORY_PATH);

        } else {
            System.out.println("Failed to create directory, data might be lost");
        }
    }

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
