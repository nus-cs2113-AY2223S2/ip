import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public static final String TASKLIST_EXPORT_PATH = "TaskList.txt";
    public static final String SUCCESS_EXPORT = "Successfully exported TaskList!";
    public static final String EXPORT_ERROR_PREFIX = "Error occurred while writing to ";
    public static void writeToTaskList() {
        File exportTaskList = new File (TASKLIST_EXPORT_PATH);
        try {
            FileWriter writeTaskList = new FileWriter(TASKLIST_EXPORT_PATH);
            int numTasks = TaskList.getNumItems();
            for (int i = 0; i < numTasks; ++i) {
                writeTaskList.write(TaskList.getItem(i).getTask());
                writeTaskList.write(System.lineSeparator());
            }
            writeTaskList.close();
            System.out.println(SUCCESS_EXPORT);
            System.out.println("Written to: " + TASKLIST_EXPORT_PATH);
        } catch (IOException e) {
            System.out.println(EXPORT_ERROR_PREFIX + exportTaskList.getAbsolutePath());
        }
    }
}
