import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for loading data from file that was previously saved and to save new data into the file.
 *
 */

public class Storage {
    //Commands
    static final String TODO_ICON = "T";
    static final String DEADLINE_ICON = "D";
    static final String EVENT_ICON = "E";
    static final String DEADLINE_BY = "/by";
    static final String EVENT_START = "/from";
    static final String EVENT_END = "/to";

    /**
     * Reads each task stored in the user's local disk and processes it to correctly set its type and done status.
     * The tasks read are saved into a data file.
     * @param taskList A list of the tasks retrieved from the user's local disk.
     */
    public static void updateFile(TaskList taskList) {
        for (Task currentTask : taskList.list) {
            String statusNum = currentTask.getStatusNum();
            String taskDesc = currentTask.getTask();
            String taskType = currentTask.getTaskIcon();

            switch (taskType) {
            case TODO_ICON:
                try {
                    writeToFile(taskType + ":" + statusNum + ":"
                            + taskDesc);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DEADLINE_ICON:
                String deadlineBy = currentTask.getDeadlineBy();
                try {
                    writeToFile(taskType + ":" + statusNum + ":"
                            + taskDesc + DEADLINE_BY + deadlineBy);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT_ICON:
                String eventStart = currentTask.getEventStart();
                String eventEnd = currentTask.getEventEnd();
                try {
                    writeToFile(taskType + ":" + statusNum + ":"
                            + taskDesc + EVENT_START + eventStart
                            + EVENT_END + eventEnd);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                System.out.println("Nothing to update!");
                break;
            }
        }
    }

    /**
     * Loads the user's saved task list from the file path. If the file cannot be found,
     * a file with the corresponding name is created.
     *
     * @param taskList the empty task list created when the program initiates.
     * @param filePath the path leading to the data file.
     * @throws FileNotFoundException If file cannot be found during execution of loadFile
     */
    public static void loadFile(TaskList taskList, String filePath) throws FileNotFoundException {
        //generates the text file input to new list each time user opens program.
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String newLine = s.nextLine();
            String[] newInput = newLine.split(":");
            String taskType = newInput[0];
            String taskMark = newInput[1];
            String taskName = newInput[2];

            switch (taskType) {
            case TODO_ICON:
                taskList.addTask(new Todo(taskName));
                break;
            case DEADLINE_ICON:
                String[] deadlineSeparator = taskName.split(DEADLINE_BY);
                String deadlineTask = deadlineSeparator[0];
                String deadlineBy = deadlineSeparator[1];
                taskList.addTask(new Deadline(deadlineTask, deadlineBy));
                break;
            case EVENT_ICON:
                String[] eventSeparator = taskName.split(EVENT_START);
                String eventTask = eventSeparator[0];
                String eventDuration = eventSeparator[1];
                String[] eventDurationSeparator = eventDuration.split(EVENT_END);
                String eventStart = eventDurationSeparator[0];
                String eventEnd = eventDurationSeparator[1];
                taskList.addTask(new Event(eventTask,eventStart,eventEnd));
                break;
            default:
                System.out.println("Error!");
                break;
            }
            if (taskMark.equals("1")) {
                taskList.getTask(taskList.getSize() - 1).markAsDone();
            }
        }
    }

    /**
     * Method to clear file contents
     */
    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        fw.write("");
        fw.close();
    }

    /**
     * Method to write file contents
     *
     * @param textToAdd text that is to be added to the file
     */
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("duke.txt", true);
        fw.write(textToAdd+System.lineSeparator());
        fw.close();
    }
}
