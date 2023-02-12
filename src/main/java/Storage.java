import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<Task> initialiseDuke()  throws IOException {
        ArrayList<Task> savedList = new ArrayList<>();
        new File(getFolderPath()).mkdir();
        File dukeDataFile = new File(getFilePath());
        new File(getFilePath()).createNewFile();
        try {
            loadSavedData(dukeDataFile, savedList);
        } catch (IOException e) {
            System.out.println(e);
        }
        return savedList;
    }

    public void loadSavedData(File dukeDataFile, ArrayList<Task> savedList) throws IOException {
        Scanner dukeData = new Scanner(dukeDataFile);
        while (dukeData.hasNextLine()) {
            try {
                Parser.checkTaskType(dukeData.nextLine(), savedList);
                UI.printHorizontalLine();
            } catch (InvalidTaskTypeException e) {
                System.out.println(e);
            }
        }
    }

    public void saveTasks(ArrayList<Task> listOfTasks) throws IOException {
        String content = "";
        for (Task task : listOfTasks) {
            content += formatContentToBeSaved(task);
        }
        FileWriter fileToWriteTo = new FileWriter(getFilePath());
        fileToWriteTo.write(content);
        fileToWriteTo.close();
    }

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
