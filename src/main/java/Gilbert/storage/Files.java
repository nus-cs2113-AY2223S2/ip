package Gilbert.storage;

import Gilbert.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Files {

    /**
     * Saves the current tasklist by writing its contents onto the text file in the
     * specified directory.
     *
     * @param filePath          The file path containing the save file.
     * @param taskList          The list of all the current Gilbert.tasks.
     * @throws IOException      Exception in the event of I/O error.
     */
    public static void writeToFile(String filePath, TaskList taskList) throws IOException {
        String data = "";
        String type;
        String desc;
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(filePath);
        int done;
        for (int i = 0; i < taskList.sizeTaskList(); i++) {
            type = taskList.getTask(i).getType();
            desc = taskList.getTask(i).getDesc();
            switch(type) {
                case "D":
                    Deadline deadline = (Deadline)taskList.getTask(i);
                    done = deadline.isDone() ? 1 :0;
                    break;
                case "T":
                    Todo todo = (Todo)taskList.getTask(i);
                    done = todo.isDone() ? 1 : 0;
                    break;
                default:
                    Event event = (Event)taskList.getTask(i);
                    done = event.isDone() ? 1 : 0;
                    break;
            }
            data += type + " " + done + " " + desc + System.lineSeparator();
        }
        fw.write(data);
        fw.close();
    }

    /**
     * Loads the save file in the specified directory to the tasklist.
     *
     * @param filePath                  The file path containing the save file.
     * @param taskList                  The list of all the current Gilbert.tasks.
     * @throws FileNotFoundException    Exception in the event that the file is not found.
     */
    public static void loadFile(String filePath, TaskList taskList) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Task task = new Task("");
        while (s.hasNext()) {
            String[] data = s.nextLine().split(" ", 3);
            switch (data[0]) {
                case "D" -> task = new Deadline(data[2]);
                case "T" -> task = new Todo(data[2]);
                case "E" -> task = new Event(data[2]);
                default -> {
                }
            }
            if (data[1].equals("1")){
                task.markAsDone();
            }
            taskList.addTask(task);
        }
    }
}
