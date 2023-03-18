import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;

public class Data {

    protected String filePath;
    protected String fileDirectory;


    public Data(String filePath, String fileDirectory) {
        this.filePath = filePath;
        this.fileDirectory = fileDirectory;
    }

    /**
     * Checks if the directory exists and creates one if it does not.
     * Then it will check if the file exists and creates one if it does not.
     *
     * @throws IOException if there is an error in creating the directory or file.
     */
    public void checkFile() throws IOException {
        File file = new File(filePath);
        File directory = new File(fileDirectory);
        if (!directory.isDirectory()) {
            Path path = Paths.get(String.valueOf(directory));
            Files.createDirectories(path);
        }
        if (!file.isFile()) {
            Files.createFile(Path.of(filePath));
        }
    }
    /**
     * Reads in the file duke.txt and categorise into its respective task: Todo, Deadline or Event.
     *
     * @param taskList taskList contains the functions to read and create the tasks correspondingly
     * @throws IOException if there is and error when reading in the file.
     */
    public void readFile(TaskList taskList) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] substring = input.split("-|\\|");
            if (substring[0].trim().equals("T")) {
                taskList.readTodoTask(substring[1].trim(), substring[2].trim());
            } else if (substring[0].trim().equals("D")) {
                taskList.readDeadlineTask(substring[1].trim(), substring[2].trim(), substring[3].trim());
            } else if (substring[0].trim().equals("E")) {
                taskList.readEventTask(substring[1].trim(), substring[2].trim(), substring[3].trim(), substring[4].trim());
            }
        }
    }

    /**
     * Writes the updated of tasks in the ArrayList back into the file duke.txt
     *
     * @throws IOException if there is an error in writing the file.
     */
    public void writeFile() throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : TaskList.tasks) {
            fileWriter.write(task.saveToFile());
        }
        fileWriter.close();
    }
}
