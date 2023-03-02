package duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Contains the operations involving with file I/O.
 */
public class Storage {

    private File listFile;
    private int listnum;
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Get the number of items in task list.
     * @return Number of items in task list.
     */
    public int getListnum() {
        return listnum;
    }

    /**
     * Get the list of tasks.
     * @return List of tasks in <code>ArrayList<Task></code> type.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Constructor of <code>Storage</code> class.
     * @param filePath File which stores data of <code>Duke</code>.
     * @throws IOException If <code>IOException</code> appears in reading or creating the file.
     */
    public Storage(String filePath) throws IOException {
        listFile = new File(filePath);
        // create file `f` if it does not exist
        if(!listFile.exists()) {
            if(!listFile.getParentFile().exists()) {
                listFile.getParentFile().mkdirs();
            }
            listFile.createNewFile();
        }
        listnum = 0;
    }

    /**
     * Save <code>Duke</code> data(the <code>todoList</code>) to data file.
     * @throws IOException If saver encounters <code>IOException</code>.
     */
    public void save() throws IOException {
        FileWriter saver = new FileWriter(listFile);
        listnum = 0;
        for(Task task : tasks) {
            // construct one line in file
            String line = task.getTypeIcon() + " | " 
                        + (task.getStatusIcon().equals("X") ? "1" : "0") + " | " 
                        + task.getDescription();
            if(task.getTypeIcon() == 'D' || task.getTypeIcon() == 'E') {
                line += " | " + task.getTimeBound();
            }
            saver.append(line + System.lineSeparator());
            listnum++;
        }
        saver.close();
    }

    /**
     * Load <code>Duke</code> data(the <code>todoList</code>) from data file.
     * @return The <code>Storage</code> class containing all data read from data file.
     * @throws DukeException If data in data file is not written in correct form,
     * e.g. the type is not 'T', 'D' or 'E'.
     */
    public Storage load() throws DukeException {
        try (Scanner in = new Scanner(listFile)) {
            while(in.hasNext()) {
                String line = in.nextLine();
                Task task;

                int typeIdx = line.indexOf(" | ");
                String type = line.substring(0, typeIdx);

                int doneIdx = line.indexOf(" | ", typeIdx + " | ".length());
                String done = line.substring(typeIdx + " | ".length(), doneIdx);

                if(type.equals("T")) {
                    String desc = line.substring(doneIdx + " | ".length());
                    task = new Todo(desc);
                } else if(type.equals("D")) {
                    int descIdx = line.indexOf(" | ", doneIdx + " | ".length());
                    String desc = line.substring(doneIdx + " | ".length(), descIdx);
                    
                    String by = line.substring(descIdx + " | ".length());
                    task = new Deadline(desc, by);
                } else if(type.equals("E")) {
                    int descIdx = line.indexOf(" | ", doneIdx + " | ".length());
                    String desc = line.substring(doneIdx + " | ".length(), descIdx);

                    int fromIdx = line.indexOf("-", descIdx + " | ".length());
                    String from = line.substring(descIdx + " | ".length(), fromIdx);

                    String to = line.substring(fromIdx + "-".length());
                    task = new Event(desc, from, to);
                } else {
                    in.close();
                    throw new DukeException("Unknown task type!");
                }

                task.setIsDone(done.equals("1") ? true : false);
                tasks.add(task);
                listnum++;
            }
            in.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return this;
    }
}
