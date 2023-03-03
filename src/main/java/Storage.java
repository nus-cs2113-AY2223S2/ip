import tasktypes.Deadline;
import tasktypes.Event;
import tasktypes.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String TASKLIST_EXPORT_PATH = "TaskList.txt";
    public static final String WRITTEN_TO_PATH_PREFIX = "Written to: ";
    public static final String SUCCESS_EXPORT = "Successfully exported TaskList!";
    public static final String EXPORT_ERROR_PREFIX = "Error occurred while writing to ";
    public static final String TASKTYPE_READ_ERROR = "Error reading TaskType";
    public static final int DESCRIPTION_INDEX = 9;
    public static final int DEADLINE_INDEX = 9;
    public static final int STARTDATE_INDEX = 6;
    public static final int ENDDATE_INDEX = 4;
    public static final String EXISTING_DATA_NOT_FOUND = "Existing data not found!";

    /**
     * creates a file in a specified export path
     * @return a File object pertaining to the created file
     */
    public static File createFile() {
        return new File(TASKLIST_EXPORT_PATH);
    }

    /**
     * finds an existing tasklist.txt file and reads from it to populate the current instance's tasklist
     * returns an error message if the existing file does not exist
     */
    public static void readFromExistingData() {
        File existingData = new File(TASKLIST_EXPORT_PATH);
        try {
            Scanner s = new Scanner(existingData);
            String itemDescription;
            while (s.hasNext()) {
                String newLine = s.nextLine();
                String TaskType = String.valueOf(newLine.charAt(3));

                switch (TaskType) {
                case (Todo.TYPE_ICON):
                    itemDescription = newLine.substring(DESCRIPTION_INDEX);
                    Todo task = new Todo(itemDescription);
                    TaskList.addItem(task);
                    break;
                case (Deadline.TYPE_ICON):
                    itemDescription = newLine.substring(DESCRIPTION_INDEX);
                    String dueDate = s.nextLine().trim();
                    dueDate = dueDate.substring(DEADLINE_INDEX);
                    Deadline deadline = new Deadline(itemDescription, dueDate);
                    TaskList.addItem(deadline);
                    break;
                case (Event.TYPE_ICON):
                    itemDescription = newLine.substring(DESCRIPTION_INDEX);
                    String startDate = s.nextLine().trim();
                    startDate = startDate.substring(STARTDATE_INDEX);
                    String endDate = s.nextLine().trim();
                    endDate = endDate.substring(ENDDATE_INDEX);
                    Event event = new Event(itemDescription,startDate, endDate);
                    TaskList.addItem(event);
                    break;
                default:
                    System.out.println(TASKTYPE_READ_ERROR);
                    return;
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println(EXISTING_DATA_NOT_FOUND);
        }
    }

    /**
     * exports the current tasklist into a text file at the specified export path
     *
     */
    public static void writeToTaskList() {
        File exportTaskList = createFile();
        try {
            FileWriter writeTaskList = new FileWriter(TASKLIST_EXPORT_PATH);
            int numTasks = TaskList.getNumTasks();
            for (int i = 0; i < numTasks; ++i) {
                writeTaskList.write(i + 1 + ".");
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
