package duke.storage;

import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.file.TaskList;
import duke.outputs.Messages;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;

/**
* Format of tasks in txt file:
* For todo task:
* T|(status)|taskDescription
* For deadline task:
* D|(status)|taskDescription|by
* For event task:
* E|(status)|taskDescription|startTime|endTime
*
* where status can be marked or unmarked (1 / 0 )
*/

/**
 * Storage that handles the conversion of data from text file to jar file , and vice versa
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage class
     *
     * @param filePath the path of the stored text file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new text file if it does not exist
     *
     * @throws IOException if error during file creation
     */
    public void createTextFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            file.mkdirs();
        }
    }

    /**
     * Updates tasklist with data from existing save file if it exists
     *
     * @param tasks the tasklist to be updated
     */
    public void loadTextFile(TaskList tasks) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String textFileLine;
            while ((textFileLine = reader.readLine()) != null) {
                String[] textLinesArray = textFileLine.split("\\|", -1);
                if (textFileLine.startsWith("T")) {
                    Todo todo = new Todo(textLinesArray[2]);
                    if (textLinesArray[1].equals("1")) {
                        todo.markAsDone();
                    }
                    tasks.addNewTask(todo);
                } else if (textFileLine.startsWith("D")) {
                    Deadline deadline = new Deadline(textLinesArray[2], textLinesArray[3]);
                    if (textLinesArray[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks.addNewTask(deadline);
                } else {
                    Event event = new Event(textLinesArray[2], textLinesArray[3], textLinesArray[4]);
                    if (textLinesArray[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks.addNewTask(event);
                }
            }
        } catch (IOException exception) {
            Messages.IOErrorMessage();
        } catch (Exception exception) {
            Messages.taskLoadErrorMessage();
        }
    }

    /**
     * Updates the text file with data from the tasklist
     *
     * @param tasks tasks within the tasklist to be converted and updated to the text file
     * @throws IOException if error during the update
     */
    public void updateTextFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.sizeOfTasksArray(); i++) {
            Task task = tasks.getTask(i);
            String type;
            String description = task.getDescription();
            String status = task.isDone() ? "1" : "0";
            if (task instanceof Todo) {
                type = "T";
                fw.write(type + "|" + status + "|" + description + "\n");
            } else if (task instanceof Deadline) {
                type = "D";
                String by = ((Deadline) task).getBy();
                fw.write(type + "|" + status + "|" + description + "|" + by + "\n");
            } else {
                type = "E";
                String startTime = ((Event) task).getStartTime();
                String endTime = ((Event) task).getEndTime();
                fw.write(type + "|" + status + "|" + description + "|" + startTime + "|" + endTime + "\n");
            }
        }
        fw.close();
    }
}
