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

public class Storage {

    private File listFile;
    private int listnum;
    private ArrayList<Task> tasks = new ArrayList<>();

    public int getListnum() {
        return listnum;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

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
