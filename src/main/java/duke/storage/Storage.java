package duke.storage;

import duke.Duke;
import duke.command.CommandMark;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * storage class handles all file related tasks
 * such as creating file, printing file, editing file etc
 */
public class Storage {

    /**
     * Updates the save file with new tasks if
     * list is modified at end of the program
     *
     * @param path path to the duke save file
     * @throws IOException if file cannot be found
     */
    public static void doEditFile(String path) throws IOException {
        File fileName = new File(path);
        FileWriter savedFile = new FileWriter(fileName, false);
        for (int index = 0; index < Duke.taskCount; index++) {
            savedFile.write(TaskList.retrieveTask(index));
            savedFile.write(System.getProperty("line.separator"));
        }
        savedFile.write(System.getProperty("line.separator"));
        savedFile.close();
    }

    /**
     * First, check if the file exists
     * if file does not exist, a new file is created
     * If file already exists prints the contents of the file
     *
     * @param path path to the duke save file
     */
    public static void openFile(String path) {
        try {
            File fileName = new File(path);
            if (fileName.createNewFile()) {
                Ui.printFileCreated(true);
            } else printFile(fileName);
        } catch (IOException e) {
            Ui.printFileCreated(false);
        } catch (IndexOutOfBoundsException e) {
            Ui.printIncorrectTaskFormat();
        }
    }

    /**
     * prints the contents of the duke save file
     *
     * @param fileName represents the duke save file
     * @throws FileNotFoundException if file not found
     */
    private static void printFile(File fileName) throws FileNotFoundException {
        Scanner s = new Scanner(fileName);
        if (!s.hasNext()) {
            Ui.printEmptyFile();
        } else {
            Ui.printFileContents(s);
            extractData(fileName);
        }
    }

    /**
     * Loops through the file and passes the tasks string into handleUserCommand function
     * handleUserCommand function then adds these tasks into arraylist
     *
     * @param fileName represents the duke save file
     * @throws FileNotFoundException if file not found
     */
    public static void extractData(File fileName) throws FileNotFoundException {

        Scanner s = new Scanner(fileName);
        int count = 1;
        while (s.hasNext()) {
            Duke.toPrint = false;
            String currentLine = s.nextLine();
            String userCommand = currentLine.substring(4);
            TaskList.handleUserCommand(userCommand);
            String markStatus = currentLine.substring(0, 4);
            if (markStatus.equals("[X] ")) {
                CommandMark.execute(count);
            } else if (!markStatus.equals("[ ] ")) {
                Ui.printIncorrectTaskFormat();
            }
            count++;
        }
        Duke.toPrint = true;
    }
}
