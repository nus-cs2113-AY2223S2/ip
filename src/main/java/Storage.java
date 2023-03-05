import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    public static String filePath;

    public Storage(String path) {
        this.filePath = path;
    }

    public static String load() {
        return filePath;
    }

    /**
     * to save the taskList to txt file
     *
     * @param list the whole taskList created by user
     */
    public static void save(ArrayList<Task> list) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);
        for (int i = 0; i < list.size(); i++) {
            writer.write(list.get(i).toString());
            writer.write("\r\n");
        }
        writer.close();
    }
}
