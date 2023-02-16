package commands;

import tasks.Task;

import java.io.File;
import java.util.ArrayList;

public class FileIO {
    static File f = new File("/Users/sherlock/ip/src/userData.txt");

    public static boolean isExist() {
        return f.exists();
    }

    public static void runFileIO(ArrayList<Task> list) {
        boolean isFileExist = isExist();
        if (isFileExist) {
            ReadFile.readFile(f, list);
        } else {
            CreateFile.createFile(f);
        }
    }

}
