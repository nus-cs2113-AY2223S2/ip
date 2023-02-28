package Handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class StorageManager {

    private static final String FILE_PATH = "./data/duke.txt";

    /**
     * Creates a new file if it does not exist and loads the tasks in the file into
     * the taskList in TaskManager
     * 
     * @throws FileNotFoundException
     *             if the file is not found
     * @throws IOException
     *             if there is an error reading the file
     */
    public static void loadFileContents() {

        new File("./data").mkdir();

        File f = new File(FILE_PATH);

        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                Ui.createFileErrorMessage();
            }
        }

        try {
            writeToTaskManager(f);
        } catch (FileNotFoundException e) {
            Ui.fileNotFoundMessage();
        }
    }

    /**
     * Saves the tasks in the taskList in TaskManager into the file
     * 
     * @throws IOException
     *             if there is an error writing to the file
     */
    public static void saveFileContents() {

        try {
            writeToFile();
        } catch (Exception e) {
            Ui.writeToFileErrorMessage();
        }
    }

    /**
     * Reads every line of the file and load each task into the taskList in
     * TaskManager
     * 
     * @param f
     *            the file to be read
     * @throws FileNotFoundException
     *             if the file is not found
     */
    private static void writeToTaskManager(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            TaskManager.readTaskFromFile(nextLine);
        }
        s.close();
    }

    /**
     * Writes every task in the taskList in TaskManager into the file
     * 
     * @throws IOException
     *             if there is an error writing to the file
     */
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < TaskManager.getTaskCount(); i++) {
            fw.write(TaskManager.getTaskList().get(i).describeTaskForFile() + "\n");
        }
        fw.close();
    }
}
