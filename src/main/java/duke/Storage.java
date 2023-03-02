package duke;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Storage {

    String fileName;

    /**
     * Initializes Storage class.
     *
     * @param fileName name of file where task list is stored on memory.
     */
    public Storage (String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads TaskList from stored memory based on standardized task storage format.
     * 
     * @return Initial TaskList.
     * @throws IOException If there is an issue reading from the file.
     */
    public TaskList readFromStorage () throws IOException {
        File f = new File(fileName);

        f.createNewFile();
        Scanner s = new Scanner(f);

        TaskList tasks = new TaskList();


        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskSplit = task.split("\\|");
            switch (taskSplit[1]) {
                case "T":
                    Todo todo = new Todo(taskSplit[2]);
                    tasks.addTask(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(taskSplit[2], taskSplit[3]);
                    tasks.addTask(deadline);
                    break;
                case "E":
                    Event event = new Event(taskSplit[2], taskSplit[3], taskSplit[4]);
                    tasks.addTask(event);
                    break;
                default:
                    break;
            }
            if (taskSplit[0].equals("1")) {
                try {
                    tasks.getTask(tasks.length() - 1).markDone();
                } catch (IncorrectIndexException e) { 
                    //Will never be reached since we use tasks.length()
                }
            }
        }
        s.close();

        return tasks;
    }

    /**
     * Writes TaskList to storage so it can be retrieved when the program is ran later.
     *
     * @param tasks TaskList to be written.
     * @throws IOException If there is an issue writing to the file.
     */
    public void writeToFile (TaskList tasks) throws IOException {
        new FileWriter(fileName).close(); // reset output file
        FileWriter fw = new FileWriter(fileName, true);
        for (int i = 0; i < tasks.length(); i++) {
            try {
                Task task = tasks.getTask(i);
                fw.write(task.storeString());
            } catch (IncorrectIndexException e) {
                fw.close();
                throw new RuntimeException("Wrong index passed in through for loop");
            }
        }
        fw.close();
    }
    
}
