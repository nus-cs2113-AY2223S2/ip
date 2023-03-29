package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String filePath = "duke.txt";
    public Storage() {
    }
    public File createFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Writes into the file when change is made to data.
     * @param storedTasks list of tasks stored by user.
     * @throws IOException error when file does not exist.
     */
    public void updateFile(ArrayList<Task> storedTasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task i : storedTasks) {
            String textToAdd = i.getTypeIcon() + "/" + i.getStatusIcon() + "/" + i.getDescription() + "/" +
                    i.getInfo() + "\n";
            fileWriter.write(textToAdd);
        }
        fileWriter.close();
    }
}
