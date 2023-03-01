package duke.storage;

import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the storage used to store and retrieve data
 * from the user's task list
 */
public class Storage {
    public static final String filePath = "duke.txt";
    public static void writeToFile(TaskList tasks){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.toTextFileFormat());
                fw.write('\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void readFileData(TaskList tasks){
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            String data;
            while (s.hasNext()) {
                data = s.nextLine();
                addFileDataToList(tasks, data);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found, new file will be created");
        }
    }

    /**
     * Adds data stored in duke.txt into user's tasklist.
     *
     * @param tasks  Task list containing all saved task
     * @param data   Loaded data from duke.txt
     */

    public static void addFileDataToList(TaskList tasks, String data){
        String[] inputData = data.split("/");
        String taskType = inputData[0];
        switch (taskType){
        case "todo":
            String todoName = inputData[1];
            Boolean todoStatus = Boolean.parseBoolean(inputData[2]);
            tasks.addTask(new Todo(todoName, todoStatus));
            break;

        case "deadline":
            String deadlineName = inputData[1];
            String deadline = inputData[3];
            Boolean deadlineStatus = Boolean.parseBoolean(inputData[2]);
            tasks.addTask(new Deadline(deadlineName, deadline, deadlineStatus));
            break;

        case "event":
            String eventName = inputData[1];
            String start = inputData[3];
            String end = inputData[4];
            Boolean eventStatus = Boolean.parseBoolean(inputData[2]);
            tasks.addTask(new Event(eventName, start, end, eventStatus));
            break;
        }
    }
}
