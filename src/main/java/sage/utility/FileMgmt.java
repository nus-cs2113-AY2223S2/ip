package sage.utility;

import sage.tasktypes.Deadline;
import sage.tasktypes.Task;
import sage.tasktypes.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMgmt {

    private static final String PROJECT_FILE_DIR = "./data/sage.txt";

    public void recoverData(TaskList taskList) {
        File f = new File(PROJECT_FILE_DIR);
        if (f.exists()) {
            Display UI = new Display();
            UI.printResumeSession();
            try {
                Scanner scanner = new Scanner(f);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    char taskType = line.charAt(1);
                    String taskDescription = "";
                    String taskTime = "";
                    boolean isCompleted = false;
                    char s = line.charAt(4);
                    if (s == 'X') {
                        isCompleted = true;
                    }
                    if (taskType == 'T') {
                        taskDescription = line.substring(7);
                        taskList.addTask(taskDescription, isCompleted, true);
                    } else {
                        String taskPeriod = line.substring(line.indexOf('(') + 1, line.indexOf(')'));
                        taskDescription = line.substring(7, line.indexOf('(') - 1) + " ";
                        if (taskType == 'D') {
                            String taskBy = taskPeriod.substring(taskPeriod.indexOf(":") + 2);
                            taskList.addTask(taskDescription, taskBy, isCompleted, true);
                        } else {
                            String taskFrom = taskPeriod.substring(taskPeriod.indexOf("from: ") + 6, taskPeriod.indexOf("to: ") - 1);
                            String taskTo = taskPeriod.substring(taskPeriod.indexOf("to: ") + 4);
                            taskList.addTask(taskDescription, taskFrom, taskTo, isCompleted, true);
                        }
                    }
                }

                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateFile(ArrayList<Task> list) {
        File f = new File(PROJECT_FILE_DIR);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(PROJECT_FILE_DIR);
            for (Task taskobj : list) {
                fw.write(String.valueOf(taskobj) + " \r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
