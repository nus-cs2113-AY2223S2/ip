import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    protected static String fileName;

    public Storage(String inputFileName) {
        fileName = inputFileName;
    }

    public File load() {
        File file = new File(fileName);
        return file;
    }

    public static void saveToFile() {
        try {
            FileWriter fWriter = new FileWriter(fileName);
            for (Task task : TaskList.returnTaskList()) {
                fWriter.write(task.fileFormat());
            }
            fWriter.close();
        } catch (IOException e) {
            System.out.print("IOException Error: data not saved to file\n");
        }
    }
}
