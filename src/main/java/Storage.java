import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Storage class handles all file methods of Duke, it only has the attribute fileName
 */
public class Storage {
    private String fileName;

    public Storage(String inputFileName) {
        this.fileName = inputFileName;
    }

    /*
     * Loads file from location specified by <code>fileName</code>
     * 
     * @param none
     * 
     * @return File object file from the location
     */
    public void load(TaskList taskList) {
        File file = new File(fileName);
        String data;
        try {
            if (!file.createNewFile()) {
                Scanner fileData = new Scanner(file);
                while (fileData.hasNext()) {
                    data = fileData.nextLine();
                    String[] inputArgs = data.split("\\|");
                    addFileData(inputArgs, taskList);
                    for (String arg : inputArgs) {
                        System.out.print(arg + " ");
                    }
                }
            }
        } catch (IOException e) {
            System.out.print("\nError getting file data");
        }
    }

    public void addFileData(String[] inputArgs, TaskList taskList) {
        Task newTask;
        String command = inputArgs[0];
        boolean taskStatus = Boolean.parseBoolean(inputArgs[1]);
        switch (command) {
            case "T":
                newTask = new Todo(inputArgs[2]);
                break;
            case "D":
                newTask = new Deadline(inputArgs[2]);
                break;
            case "E":
                newTask = new Event(inputArgs[2]);
                break;
            default:
                throw new IllegalStateException("File contents are invalid");
        }
        if (taskStatus) {
            newTask.markAsDone();
        }
        taskList.returnTaskList().add(newTask);
    }

    /*
     * Saves file to destination specified by <code>fileName</code>, the same
     * location where file is retrieved from
     */
    public void saveToFile(TaskList taskList) {
        try {
            FileWriter fWriter = new FileWriter(fileName);
            for (Task task : taskList.returnTaskList()) {
                fWriter.write(task.fileFormat());
            }
            fWriter.close();
        } catch (IOException e) {
            System.out.print("IOException Error: data not saved to file\n");
        }
    }
}
