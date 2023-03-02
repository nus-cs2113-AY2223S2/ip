package sage.utility;

import sage.tasktypes.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * A class that contains storage and file related I/O methods
 */
public class Storage {

    private static final String PROJECT_FILE_DIR = "./data/sage.txt";
    private static final Integer TASKTYPEINDEX = 1;
    private static final Integer TASKCOMPLETIONINDEX = 4;
    private static final Integer TASKFROMLENGTH = 6;
    private static final Integer TASKTOLENGTH = 4;

    private static final Integer TASKBYLENGTH = 2;

    private static final Integer TASKDESCENTRYINDEX = 7;

    /**
     * Recovers and parse any stored data in ./data/sage.txt to taskList
     *
     * @param taskList contains information and methods about all the tasks currently in the list
     */
    public void recoverData(TaskList taskList) {
        File f = new File(PROJECT_FILE_DIR);
        if (f.exists()) {
            Display UI = new Display();
            UI.printResumeSession();
            try {
                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    writeToApp(line, taskList);
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred when trying to read/access the data file");
            }
        }
    }

    /**
     * Writes a given line recovered from previous session data to the application
     *
     * @param line     contains information about a recovered task from the previous session
     * @param taskList contains information and methods about all the tasks currently in the list
     */
    public void writeToApp(String line, TaskList taskList) {
        char taskType = line.charAt(TASKTYPEINDEX);
        String taskDescription = "";
        boolean isCompleted = false;
        char s = line.charAt(TASKCOMPLETIONINDEX);

        if (s == 'X') {
            isCompleted = true;
        }
        if (taskType == 'T') {
            taskDescription = line.substring(TASKDESCENTRYINDEX);
            taskList.addTask(taskDescription, isCompleted, true);
        } else {
            String taskPeriod = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
            taskDescription = line.substring(7, line.indexOf('(') - 1) + " ";
            if (taskType == 'D') {
                String taskBy = taskPeriod.substring(taskPeriod.indexOf(':') + TASKBYLENGTH);
                taskList.addTask(taskDescription, taskBy, isCompleted, true);
            } else {
                String taskFrom = taskPeriod.substring(taskPeriod.indexOf("from: ") + TASKFROMLENGTH,
                        taskPeriod.indexOf("to: ") - 1);
                String taskTo = taskPeriod.substring(taskPeriod.indexOf("to: ") + TASKTOLENGTH);
                taskList.addTask(taskDescription, taskFrom, taskTo, isCompleted, true);
            }
        }
    }

    /**
     * Updates the data file in ./data/sage.txt with the latest list of tasks
     *
     * @param list list of tasks information in the application
     */

    public void updateFile(ArrayList<Task> list) {
        File f = new File(PROJECT_FILE_DIR);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(PROJECT_FILE_DIR);
            for (Task taskobj : list) {
                fw.write(taskobj + " \r\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Info: Error Updating Changes to Data File, Please refresh App!");
        }
    }
}
