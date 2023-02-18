package orca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    static final String FILE_PATH = "./data/orca.txt";

    TaskList() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        } else {
            // Load data from file.
            try {
                FileReader reader = new FileReader(FILE_PATH);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    boolean isDone = line.charAt(4) == 'X';
                    if (line.charAt(1) == 'T') {
                        String description = line.substring(7);
                        tasks.add(new Todo(description, isDone));
                    } else if (line.charAt(1) == 'D') {
                        String description = line.substring(7, line.indexOf(" (by: "));
                        String by = line.substring(line.indexOf(" (by: ") + 6, line.length() - 1);
                        tasks.add(new Deadline(description, by, isDone));
                    } else if (line.charAt(1) == 'E') {
                        String description = line.substring(7, line.indexOf(" (from: "));
                        String from =
                                line.substring(line.indexOf(" (from: ") + 8, line.indexOf(" to: "));
                        String to = line.substring(line.indexOf(" to: ") + 5, line.length() - 1);
                        tasks.add(new Event(description, from, to, isDone));
                    }
                }
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void markTask(int taskNo) throws OrcaException {
        try {
            tasks.get(taskNo - 1).setDone(true);
        } catch (NullPointerException e) {
            throw new OrcaException("There is no task with this number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
    }

    public void unmarkTask(int taskNo) throws OrcaException {
        try {
            tasks.get(taskNo - 1).setDone(false);
        } catch (NullPointerException e) {
            throw new OrcaException("There is no task with this number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
    }

    public ArrayList<Task> get() {
        return tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    public Task deleteTask(int taskNo) throws OrcaException {
        try {
            Task removedTask = tasks.remove(taskNo - 1);
            return removedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new OrcaException("There is no task with this number.");
        }
    }

}
