package Arsdorint.data;

import Arsdorint.command.ArsdorintFileException;
import Arsdorint.parser.TaskParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Arsdorint.MessageList.*;
import static Arsdorint.MessageList.MESSAGE_WRONG_FILE;

public class Storage {
    public Storage() {
    }
    public static final String STORAGE_DIRECTORY = "./storage";
    public static final String STORAGE_FILE_NAME = "./storage/ArsdorintTask.txt";
    public static String[] save() {
        try {
            FileWriter fileWriter = new FileWriter(STORAGE_FILE_NAME);
            for (int i = 0; i < TaskList.list.size(); i++) {
                fileWriter.write(TaskList.list.get(i).toSave());
            }
            fileWriter.close();
            return new String[]{MESSAGE_OVERWRITE_FILE};
        } catch (IOException err) {
            File newFile = new File(STORAGE_DIRECTORY);
            newFile.mkdir();
            save();
            return new String[]{MESSAGE_NO_FILE, MESSAGE_NEW_FILE};
        }
    }
    public static String load() {
        try {
            File file = new File(STORAGE_FILE_NAME);
            Scanner fileRead = new Scanner(file);
            while (fileRead.hasNext()) {
                new TaskParser().fileParser(fileRead.nextLine());
            }
            fileRead.close();
            return MESSAGE_LOAD_FILE;
        } catch (FileNotFoundException err) {
            return MESSAGE_NO_FILE;
        } catch (ArrayIndexOutOfBoundsException err) {
            return MESSAGE_WRONG_FILE;
        } catch (ArsdorintFileException err) {
            return MESSAGE_WRONG_FILE;
        }
    }

}
