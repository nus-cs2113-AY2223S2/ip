import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Storage class handles all file methods of Duke, it only has the attribute fileName
 */
public class Storage {
    protected static String fileName;

    public Storage(String inputFileName) {
        fileName = inputFileName;
    }

    /*
     * Loads file from location specified by <code>fileName</code>
     * 
     * @param none
     * 
     * @return File object file from the location
     */
    public File load() {
        File file = new File(fileName);
        return file;
    }

    /*
     * Saves file to destination specified by <code>fileName</code>, the same
     * location where file is retrieved from
     */
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
