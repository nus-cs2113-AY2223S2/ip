package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static void doEditFile(String path) throws IOException {
        File fileName = new File(path);
        FileWriter savedFile = new FileWriter(fileName, false);
        for (int index = 0; index < Duke.taskCount; index++) {
            savedFile.write(Duke.tasks.get(index).returnCommand());
            savedFile.write(System.getProperty("line.separator"));
        }
        savedFile.write(System.getProperty("line.separator"));
        savedFile.close();
    }

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

    private static void printFile(File fileName) throws FileNotFoundException {
        Scanner s = new Scanner(fileName);
        if (!s.hasNext()) {
            //print empty file
            Ui.printEmptyFile();
        } else {
            //print here are your tasks
            System.out.println(Ui.LINE);
            System.out.println("\tHere are your stored tasks!");
            int index = 1;
            while (s.hasNext()) {
                System.out.println("\t" + index + ". " + s.nextLine());
                index++;
            }
            System.out.println(Ui.LINE);
        }
        extractData(fileName);
    }

    public static void extractData(File fileName) throws FileNotFoundException {
        Scanner s = new Scanner(fileName);
        int count = 1;
        while (s.hasNext()) {
            Duke.toPrint = false;
            String currentLine = s.nextLine();
            String userCommand = currentLine.substring(4);
            Duke.handleUserCommand(userCommand);
            String markStatus = currentLine.substring(0, 4);
            if (markStatus.equals("[X] ")) {
                TaskList.doCommandMark(count);
            } else if (!markStatus.equals("[ ] ")) {
                // print task wrong format
                Ui.printIncorrectTaskFormat();
            }
            count++;
        }
        Duke.toPrint = true;
    }
}
