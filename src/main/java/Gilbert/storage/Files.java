package Gilbert.storage;

import Gilbert.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Files {

    /**
     * Saves the current tasklist by writing its contents onto the text file in the
     * specified directory.
     *
     * @param taskList          The list of all the current Gilbert.tasks.
     * @throws IOException      Exception in the event of I/O error.
     */
    public static void writeToFile(TaskList taskList) throws IOException {
        String data = "";
        String type;
        String desc;
        String dir = System.getProperty("user.dir");
        Path filePath = Paths.get(dir, "data", "gilbert.txt");
        File file = new File(filePath.toString());
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file);
        int done;
        for (int i = 0; i < taskList.sizeTaskList(); i++) {
            type = taskList.getTask(i).getType();
            desc = taskList.getTask(i).getDesc();
            switch (type) {
                case "D":
                    Deadline deadline = (Deadline) taskList.getTask(i);
                    desc = deadline.getFormat();
                    done = deadline.isDone() ? 1 : 0;
                    break;
                case "T":
                    Todo todo = (Todo) taskList.getTask(i);
                    done = todo.isDone() ? 1 : 0;
                    break;
                default:
                    Event event = (Event) taskList.getTask(i);
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
     * @param taskList                  The list of all the current Gilbert.tasks.
     * @throws FileNotFoundException    Exception in the event that the file is not found.
     */
    public static void loadFile(TaskList taskList) throws FileNotFoundException {
        String dir = System.getProperty("user.dir");
        Path filePath = Paths.get(dir, "data", "gilbert.txt");
        File f = new File(filePath.toString());
        Scanner s = new Scanner(f);
        Task task = new Task("");
        while (s.hasNext()) {
            String[] data = s.nextLine().split(" ", 3);
            switch (data[0]) {
                case "D":
                    task = new Deadline(data[2]);
                    break;
                case "T":
                    task = new Todo(data[2]);
                    break;
                case "E":
                    task = new Event(data[2]);
                    break;
                default:
            }
            if (data[1].equals("1")){
                task.markAsDone();
            }
            taskList.addTask(task);
        }
    }
}
