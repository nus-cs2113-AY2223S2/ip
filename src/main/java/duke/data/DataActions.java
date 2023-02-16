package duke.data;

import duke.ui.ErrorMessages;
import duke.ui.Symbols;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataActions {
    public static void importData(File dataFile, ArrayList<Task> tasks) {
        try {
            Scanner reader = new Scanner(dataFile);
            while (reader.hasNext()) {
                String task = reader.nextLine();
                DataActions.determineTaskType(task, tasks);
            }
        } catch (FileNotFoundException e) {
            System.out.print(ErrorMessages.FILE_NOT_FOUND_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
    }

    public static void determineTaskType(String task, ArrayList<Task> tasks) {
        String[] taskInfo = task.split(Symbols.DATA_DELIMITER); // what if user task name includes " / "
        if (taskInfo[0].equals(Symbols.TODO)) {
            tasks.add(Task.totalTasks, new Todo(taskInfo[2]));
        } else if (taskInfo[0].equals(Symbols.DEADLINE)) {
            // deadline
            tasks.add(Task.totalTasks, new Deadline(taskInfo[2], taskInfo[3]));
        } else {
            // event
            String[] timeInterval = taskInfo[3].split(Symbols.DATA_EVENT_DATE_DELIMITER);
            tasks.add(Task.totalTasks, new Event(taskInfo[2], timeInterval[0], timeInterval[1]));
        }
        if (taskInfo[1].equals(Symbols.DATA_MARK)) {
            tasks.get(Task.totalTasks).markAsDone();
        }
        Task.incrementTotalTasks();
    }

    public static void updateSavedData(ArrayList<Task> tasks) {
        try {
            FileWriter fileData = new FileWriter(FileNames.FILE_PATH.NAME);
            for (int i = 0; i < Task.totalTasks; i += 1) {
                String taskData = tasks.get(i).getSavedData();
                fileData.write(taskData + Ui.NEW_LINE);
            }
            fileData.close();
        } catch (IOException e) {
            System.out.println(ErrorMessages.IO_EXCEPTION_MESSAGE.STANDARD_OUTPUT);
        }
    }
}
