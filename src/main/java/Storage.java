import tasktypes.Deadline;
import tasktypes.Event;
import tasktypes.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String TASKLIST_EXPORT_PATH = "data\\TaskList.txt";
    public static final String WRITTEN_TO_PATH_PREFIX = "Written to: ";
    public static final String SUCCESS_EXPORT = "Successfully exported TaskList!";
    public static final String EXPORT_ERROR_PREFIX = "Error occurred while writing to ";
    public static final String TASKTYPE_READ_ERROR = "Error reading TaskType";
    public static final int DESCRIPTION_INDEX = 9;
    public static final int DEADLINE_INDEX = 9;
    public static final int STARTDATE_INDEX = 6;
    public static final int ENDDATE_INDEX = 4;

    public static File createFile() {
        return new File(TASKLIST_EXPORT_PATH);
    }

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
                    System.out.println("Description is: " + itemDescription);
                    Commands.addTodoTask("T " + itemDescription);
                    break;
                case (Deadline.TYPE_ICON):
                    itemDescription = newLine.substring(DESCRIPTION_INDEX);
                    String deadline = s.nextLine().trim();
                    deadline = deadline.substring(DEADLINE_INDEX);
                    itemDescription = Parser.ParseDeadlineInput(itemDescription, deadline);
                    Commands.addDeadlineTask(itemDescription);
                    break;
                case (Event.TYPE_ICON):
                    itemDescription = newLine.substring(DESCRIPTION_INDEX);
                    String startDate = s.nextLine().trim();
                    startDate = startDate.substring(STARTDATE_INDEX);
                    String endDate = s.nextLine().trim();
                    endDate = endDate.substring(ENDDATE_INDEX);
                    itemDescription = Parser.ParseEventInput("E " + itemDescription,startDate,endDate);
                    Commands.addEventTask(itemDescription);
                    break;
                default:
                    System.out.println(TASKTYPE_READ_ERROR);
                    return;
                }
            }
        } catch (FileNotFoundException f) {
            System.out.println("Existing data not found!");
        }
    }

    public static void writeToTaskList() {
        File exportTaskList = createFile();
        try {
            FileWriter writeTaskList = new FileWriter(TASKLIST_EXPORT_PATH);
            int numTasks = TaskList.getNumItems();
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
