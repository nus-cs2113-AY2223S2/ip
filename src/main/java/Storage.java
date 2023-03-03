import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public static final String TASKLIST_EXPORT_PATH = "data\\TaskList.txt";
    public static final String WRITTEN_TO_PATH_PREFIX = "Written to: ";
    public static final String SUCCESS_EXPORT = "Successfully exported TaskList!";
    public static final String EXPORT_ERROR_PREFIX = "Error occurred while writing to ";
    public static File createFile() {
        return new File (TASKLIST_EXPORT_PATH);
    }
    public static void writeToTaskList() {
        File exportTaskList = createFile();
        try {
            FileWriter writeTaskList = new FileWriter(TASKLIST_EXPORT_PATH);

            int numTasks = TaskList.getNumItems();
            for (int i = 0; i < numTasks; ++i) {
                writeTaskList.write(TaskList.getItem(i).getTask());
            writeTaskList.write(System.lineSeparator());
            }
            writeTaskList.close();
            System.out.println(SUCCESS_EXPORT);
            System.out.println(WRITTEN_TO_PATH_PREFIX + exportTaskList.getCanonicalPath());
        } catch (IOException e) {
            System.out.println(EXPORT_ERROR_PREFIX + exportTaskList.getAbsolutePath());
        }
    }
}
