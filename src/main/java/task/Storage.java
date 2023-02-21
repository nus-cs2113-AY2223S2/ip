package task;

import DataManager.Parser;
import UI.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static String filePath;
    private static FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static void loadExistingData() {
        try {
            File savedFile = new File(filePath);
            if(savedFile.createNewFile()) {
                return;
            }
            Scanner data = new Scanner(savedFile);
            while(data.hasNext()) {
                String existingData = data.nextLine();
                char typeOfTask = existingData.charAt(0);
                switch (typeOfTask) {
                case 'T':
                    Parser.parseExistingTodo(existingData);
                    break;
                case 'D':
                    Parser.parseExistingDeadline(existingData);
                    break;
                case 'E':
                    Parser.parseExistingEvent(existingData);
                    break;
                default:
                    Ui.showError(Ui.UNRECOGNISED_TASKTYPE);
                }
            }
        } catch (IOException e) {
            Ui.showError(Ui.FILE_ACCESS_ERROR);
        }
    }

    public static void createFileWriterObject() {
        try {
            fw = new FileWriter(filePath);
        } catch (Exception e) {
            System.out.println(Ui.FILEWRITER_CREATION_ERROR);
        }
    }

    public static void storeFileData(String taskDescription) {
        try {
            fw.write(taskDescription + System.lineSeparator());
        } catch (Exception e) {
            System.out.println(Ui.FILE_UPDATING_ERROR);
        }
    }

    public static void closeFileWriterObject() {
        try {
            fw.close();
        } catch (Exception e) {
            System.out.println(Ui.FILEWRITER_CREATION_ERROR);
        }
    }
}
