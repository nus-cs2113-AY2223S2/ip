package duke;

import duke.commands.IncorrectCommand;
import duke.commands.task.Deadline;
import duke.commands.task.Event;
import duke.commands.task.Task;
import duke.commands.task.ToDo;
import java.io.*;
import java.util.ArrayList;

/**
 * Handles the reading, writing and creation of the "duke.txt" file which contains user data.
 */

public class FileProcessor {

    public File dukeTextFile;

    /**
     * Read from an existing file the user data and adds each task to the task list provided
     * Creates a file if file does not exist
     * @param taskList task list to be operated on if file exists and is not empty
     */
    public FileProcessor(ArrayList<Task> taskList) {
        this.dukeTextFile = new File("duke.txt");
        try {
            if (dukeTextFile.createNewFile()) {
                System.out.println("File created: " + dukeTextFile.getName());
            } else {
                BufferedReader reader = new BufferedReader(new FileReader("duke.txt"));
                String line = reader.readLine();
                while (line != null) {
                    String[] splitLine = line.split("/");
                    addToList(taskList, splitLine);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Adds a task specified by the parameters to the task list provided
     * @param taskList task list to be operated on
     * @param parameters contains task details
     */

    public void addToList(ArrayList<Task> taskList, String[] parameters) {
        Task toAdd;
        switch (parameters[0]) {
        case ("[T]"):
            toAdd = new ToDo(parameters[2], parameters[1]);
            taskList.add(toAdd);
            break;
        case ("[D]"):
            toAdd = new Deadline(parameters[2], parameters[1], parameters[3]);
            taskList.add(toAdd);
            break;
        case ("[E]"):
            toAdd = new Event(parameters[2], parameters[1], parameters[3], parameters[4]);
            taskList.add(toAdd);
            break;
        }
    }


    /**
     * Writes all task in the task list provided in a standardised format into the open file "duke.txt"
     * @param taskList contains the tasks to be written
     */

    public void writeFile(ArrayList<Task> taskList) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("duke.txt"));
            for (int x = 0; x < taskList.size(); x += 1) {
                Task currentTask = taskList.get(x);
                switch (currentTask.taskChar) {
                case ("[T]"):
                    bufferedWriter.write(currentTask.taskChar + "/" + currentTask.status
                            + "/" + currentTask.taskDescription + "\n");
                    break;
                case ("[D]"):
                    bufferedWriter.write(currentTask.taskChar + "/" + currentTask.status
                            + "/" + currentTask.taskDescription
                            + "/" + ((Deadline) currentTask).by + "\n");
                    break;
                case ("[E]"):
                    bufferedWriter.write(currentTask.taskChar + "/" + currentTask.status
                            + "/" + currentTask.taskDescription
                            + "/" + ((Event) currentTask).from
                            + "/" + ((Event) currentTask).to + "\n");
                    break;
                }
            }
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
