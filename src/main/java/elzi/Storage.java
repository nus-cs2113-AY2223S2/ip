package elzi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import elzi.task.Deadline;
import elzi.task.Event;
import elzi.task.Task;
import elzi.task.Todo;


/**
 * @author : Steven A. O. Waskito
 * Class Storage handles saving and loading of file from harddisk
 **/
public class Storage {
    public final Path FILE_PATH;

    public Storage(String filePath) {
        FILE_PATH = Paths.get("data/storage.txt");
    }

    /**
     * Saves the program taskList to FILE_PATH in the harddisk
     * @param taskList ArrayList that stores tasks
     * @throws ElziException if IO Error occurred
     */
    public void saveFile(TaskList taskList) throws ElziException {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH.toString());
            String textToWrite = "";

            for (int index = 0; index < taskList.getSize(); index++) {
                Task task = taskList.getIndex(index);
                textToWrite = textToWrite.concat(task.encode());
                textToWrite += "\n";
            }

            fileWriter.write(textToWrite);
            fileWriter.close();
        } catch (IOException e) {
            throw new ElziException("IO ERROR");
        }

    }

    /**
     * Create a new file in FILE_PATH directory
     *    Credits
     *    https://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
     * @throws IOException if IO Error occurred
     */
    public void createFile() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        Files.createFile(FILE_PATH);
    }

    /**
     * Read the file in FILE_PATH directory
     * @return taskList the file taskList read
     * @throws IOException if file reading error occurred (incorrect format, file creation error)
     */
    public TaskList readFile() throws ElziException {
        TaskList taskList = new TaskList(200);

        try {
            File file = new File(FILE_PATH.toString());
            Scanner s = new Scanner(file);

            while (s.hasNext()) {
                Task task;
                String input = s.nextLine();
                //Format TYPE_MARK_DESCRIPTION_TO or BYE_FROM
                String[] inputs = input.split("_");
                String type = inputs[0];
                String mark = inputs[1];
                String description = inputs[2];
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, inputs[3]);
                    break;
                case "E":
                    task = new Event(description, inputs[3], inputs[4]);
                    break;
                default:
                    throw new ElziException("Invalid file reading");
                }
                if (mark.equals("1")) {
                    task.setAsDone();
                } else {
                    task.setAsNotDone();
                }
                taskList.addTask(task);
            }

        } catch (FileNotFoundException e) {
            try {
                createFile();
            } catch (IOException f) {
                throw new ElziException("FILE CREATION ERROR");
            }
        }
        return taskList;
    }
}
