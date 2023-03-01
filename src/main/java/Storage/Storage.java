package Storage;

import Tasklist.*;
import UI.UserInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * converts tasks between the entry format (to be saved in the text file) and Object format (to be added to taskArray)
 */
public class Storage {
    private static File file;

    public Storage() {
        file = new File("tasks.txt");
        try {
            if (file.createNewFile()) {
                UserInterface.newFileCreatedMessage(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * example of format for event entries in the file : taskNumber.taskType.taskIsDone.taskName.from.to
     *
     * @throws FileNotFoundException
     */
    public static void loadFileContentsToTaskArray() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] taskDetails = (scanner.nextLine()).split("\\.");
            String taskType = taskDetails[1];
            String taskNumber = taskDetails[0];
            String taskName = taskDetails[3];

            switch (taskType) {
                case "T":
                    ToDo todo = new ToDo(taskName, Integer.parseInt(taskNumber), taskType);
                    todo.isDone = taskDetails[2].equals("[X]");
                    todo.updateTaskDescription();
                    Tasklist.addToTaskArrayList(todo);
                    break;
                case "E":
                    String eventFromString = taskDetails[4];
                    String eventToString = taskDetails[5];

                    Event event = new Event(taskName, Integer.parseInt(taskNumber), eventFromString,
                            eventToString, taskType);
                    event.isDone = taskDetails[2].equals("[X]");
                    event.updateTaskDescription();
                    Tasklist.addToTaskArrayList(event);
                    break;
                case "D":
                    String deadlineByString = taskDetails[4];

                    Deadline deadline = new Deadline(taskName, Integer.parseInt(taskNumber), deadlineByString,
                            taskType);
                    deadline.isDone = taskDetails[2].equals("[X]");
                    deadline.updateTaskDescription();
                    Tasklist.addToTaskArrayList(deadline);
                    break;
            }
        }
    }


    /**
     * this function converts the tasks in the taskArray into entry format (Eg:taskNumber.taskType.taskIsDone.taskName)
     * and writes it to the text file
     */
    public static void save() {
        try {
            if (Tasklist.lastIndex > 0) {
                FileWriter fileWriter = new FileWriter(file);
                for (int index = 0; index < Tasklist.lastIndex; index++) {
                    Tasklist task = Tasklist.get(index);
                    fileWriter.append(task.createEntry());
                }
                fileWriter.close();
            }
            if (Tasklist.lastIndex == 0) {
                FileWriter fw = new FileWriter(file);
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
