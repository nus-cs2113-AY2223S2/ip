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
        while (dukeData.hasNextLine()) {
            try {
                Parser.checkTaskType(dukeData.nextLine(), savedTaskList);
                UI.printHorizontalLine();
            } catch (InvalidTaskTypeException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Saves task that user has inputted in the current session into local file
     *
     * @param listOfTasks
     * @throws IOException
     */
    public void saveTasks(ArrayList<Task> listOfTasks) throws IOException {
        String content = "";
        for (Task task : listOfTasks) {
            content += formatContentToBeSaved(task);
        }
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
        if (task instanceof Todo) {
            type = "todo";
            content = type + " " + task.getName();
        } else if (task instanceof Deadline) {
            type = "deadline";
            content = type + " " + task.getName() + " /by " + ((Deadline) task).getBy();
        } else {
            type = "event";
            content = type + " " + task.getName() + " /from " + ((Event) task).getStart()
                    + " /to " + ((Event) task).getEnd();
        }
        return content + "\n";
    }
}
