package duke;

import duke.task.TaskList;
import duke.task.TaskType;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The <code>Storage</code> object handles the creation of the data file
 * as well as manages the loading and writing of the data file.
 */
public class Storage {

    private static String filePath = "data/data.txt";

    /**
     * The class constructor that instantiates the <code>Storage</code> object with a specified file path.
     *
     * @param filePath The file path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from the data file into a <code>TaskList</code> object.
     * If the data file or folder is missing a new folder and/or data file will be created.
     * If the data file is empty, an empty <code>TaskList</code> will be returned.
     *
     * @return The <code>TaskList</code> object.
     */
    public static TaskList loadData() {
        TaskList tasks = new TaskList();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String rawData = myReader.nextLine();
                String[] dataList = rawData.split("&", 3);

                switch (dataList[0].trim()) {
                case "T":
                    tasks.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.TODO);
                    break;
                case "D":
                    tasks.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.DEADLINE);
                    break;
                case "E":
                    tasks.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.EVENT);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
        }

        return tasks;
    }

    /**
     * Creates a new data file and folder if needed.
     */
    public static void createFile() {
        try {
            if (new File("data").mkdirs()) {
                File data = new File(filePath);
                data.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Writes data to the data file using the list of tasks stored in <code>TaskList</code> object.
     */
    public static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            String data = TaskList.writeTask();
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
