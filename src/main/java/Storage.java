import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Duke ChatBot storage
 */
public class Storage {
    private String filePath;
    private String folderPath;

    public Storage(String folderPath, String filePath) {
        this.folderPath = folderPath;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    /**
     * Returns an ArrayList that acts as the list of task that the user has saved previously.
     * If it's a first time, returns empty list.
     *
     * @return List of tasks previously saved, else return empty list.
     * @throws IOException If file is not found.
     */
    public ArrayList<Task> initialiseDuke()  throws IOException {
        ArrayList<Task> savedTaskList = new ArrayList<>();
        new File(getFolderPath()).mkdir();
        File dukeDataFile = new File(getFilePath());
        new File(getFilePath()).createNewFile();
        try {
            loadSavedData(dukeDataFile, savedTaskList);
        } catch (IOException e) {
            System.out.println(e);
        }
        return savedTaskList;
    }

    /**
     * Puts all the user's previously saved tasks into an ArrayList.
     *
     * @param dukeDataFile The file which contains the user's saved tasks.
     * @param savedTaskList List where all the user's saved task will be added to.
     * @throws IOException If file is not found.
     */
    public void loadSavedData(File dukeDataFile, ArrayList<Task> savedTaskList) throws IOException {
        Scanner dukeData = new Scanner(dukeDataFile);
        int numberOfSavedTasks = 0;
        while (dukeData.hasNextLine()) {
            try {
                Parser.loadSavedTasks(dukeData.nextLine(), savedTaskList);
            } catch (InvalidTaskTypeException e) {
                System.out.println(e);
            }
            numberOfSavedTasks += 1;
        }
        UI.printLoadSavedDataMessage(numberOfSavedTasks);
    }

    /**
     * Saves task that user has inputted in the current session into local file
     *
     * @param listOfTasks
     * @throws IOException
     */
    public void saveTasks(Task task) throws IOException {
        String content = "";
        content += formatContentToBeSaved(task);
        FileWriter fileToWriteTo = new FileWriter(getFilePath());
        fileToWriteTo.write(content);
        fileToWriteTo.close();
    }

    /**
     * Formats the content into a user input so that the bot is able to initialise it next time.
     *
     * @param task The current task to be saved.
     * @return The current task details in the proper format
     */
    public static String formatContentToBeSaved (Task task) {
        String content = "";
        String type;
        String completed;
        boolean isCompleted = task.getStatus();
        if (isCompleted) {
            completed = "1";
        } else {
            completed = "0";
        }
        if (task instanceof Todo) {
            type = "T";
            content = type + "/" + completed + "/" + task.getName();
        } else if (task instanceof Deadline) {
            type = "D";
            content = type + "/" + completed + "/" + task.getName() + "/" + ((Deadline) task).getBy();
        } else {
            type = "E";
            content = type + "/" + completed + "/" + task.getName() + "/" + ((Event) task).getStart()
                    + "/" + ((Event) task).getEnd();
        }
        return content + "\n";
    }
}
