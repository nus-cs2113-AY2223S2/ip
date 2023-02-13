package sage.utility;

import sage.tasktypes.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileMgmt {

    private static final String PROJECT_FILE_DIR = "./data/sage.txt";

    public void updateFile(ArrayList<Task> list) {
        File f = new File(PROJECT_FILE_DIR);
        try {
            f.getParentFile().mkdirs();
            if (!f.exists()) {
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(PROJECT_FILE_DIR);
            for (Task taskobj : list) {
                fw.write(String.valueOf(taskobj));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
