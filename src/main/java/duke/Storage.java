package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.ArrayList;

public class Storage {
    static final int TASK_INFORMATION_BEGIN_INDEX = 7;
    static final int TASK_TYPE_END_INDEX = 6;
    static final int TASK_TYPE_INDICATOR_INDEX = 1;
    static final int TASK_TYPE_STATUS_INDEX = 4;
    static final String LOADING_FRONT_PARENTHESIS_DELIMITER = " \\(";
    static final String LOADING_EVENT_FROM_DELIMITER = "from: ";
    static final String LOADING_EVENT_TO_DELIMITER = " to: ";

    public static void loadData(String filepath, ArrayList<Task> tasks) {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskInStringFormat = s.nextLine();
                String taskType = taskInStringFormat.substring(0,TASK_TYPE_END_INDEX);
                String taskInformation = taskInStringFormat.substring(TASK_INFORMATION_BEGIN_INDEX);
                switch (taskType.charAt(TASK_TYPE_INDICATOR_INDEX)) {
                case 'T': // example for reference: return book
                    loadToDoTask(tasks, taskInformation, taskType);
                    break;
                case 'D': // example for reference: return book (by: June 6th)
                    loadDeadlineTask(tasks, taskInformation, taskType);
                    break;
                case 'E': // for reference: project meeting (from: Aug 6th 2pm to: 4pm)
                    loadEventTask(tasks, taskInformation, taskType);
                    break;
                default:
                    // System.out.println("OOPS! Something went wrong while converting task data!");
                    Ui.printErrorMessage("conversion");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // System.out.println("File not found");
            Ui.printErrorMessage("file not found");
        }
    }

    public static void loadEventTask(ArrayList<Task> tasks, String taskInformation, String taskType) {
        String taskName;
        taskName = taskInformation.split(LOADING_FRONT_PARENTHESIS_DELIMITER, 2)[0];
        taskInformation = taskInformation.split(LOADING_EVENT_FROM_DELIMITER,2)[1];
        String eventStart = taskInformation.split(LOADING_EVENT_TO_DELIMITER,2)[0];
        String eventEnd = taskInformation.split(LOADING_EVENT_TO_DELIMITER,2)[1].replace(")", "");
        TaskList.addTask(new Event(taskName, eventStart, eventEnd)); // tasks.add(new Event(taskName, eventStart, eventEnd));
        if (taskType.charAt(TASK_TYPE_STATUS_INDEX) == 'X') {
            tasks.get(tasks.size() - 1).setDone(true);
        }

    }

    public static void loadDeadlineTask(ArrayList<Task> tasks, String taskInformation, String taskType) {
        String taskName;
        taskName = taskInformation.split(LOADING_FRONT_PARENTHESIS_DELIMITER, 2)[0];
        String deadlineBy = taskInformation.split(": ", 2)[1].replace(")","");
        TaskList.addTask(new Deadline(taskName, deadlineBy)); // tasks.add(new Deadline(taskName, deadlineBy));
        if (taskType.charAt(TASK_TYPE_STATUS_INDEX) == 'X') {
            tasks.get(tasks.size() - 1).setDone(true);
        }
    }

    public static void loadToDoTask(ArrayList<Task> tasks, String taskInformation, String taskType) {
        String taskName;
        taskName = taskInformation; // for TODO task, taskInformation is the task name
        TaskList.addTask(new ToDo(taskName)); // tasks.add(new ToDo(taskName));
        if (taskType.charAt(TASK_TYPE_STATUS_INDEX) == 'X') {
            tasks.get(tasks.size() - 1).setDone(true);
        }
    }

    public static void saveData(String filepath, ArrayList<Task> tasks) {
        try {
            String tasksDataInStringFormat = convertTasksDataToString(tasks);
            Storage.writeToFile(filepath, tasksDataInStringFormat);
        } catch (IOException e) {
            // System.out.println("OOPS! Something went wrong with saving your data!");
            Ui.printErrorMessage("saving");
        } catch (DukeException e) {
            // System.out.println("OOPS! Something went wrong when converting your data for saving!");
            Ui.printErrorMessage("conversion");
        }
    }

    public static String convertTasksDataToString (ArrayList<Task> tasks) throws DukeException {
        StringJoiner joiner = new StringJoiner("\n");
        try {
            for (Task task: tasks) {
                String taskInformation = task.toString();
                joiner.add(taskInformation);
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }
        return joiner.toString();
    }

    // using overwrite type rather than append type to deal with removal/marking/unmarking
    // code provided by module website
    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }
}
