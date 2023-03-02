package components;

import task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from previous file and put it in an arraylist.
     *
     * @return ArrayList.
     * @throws IOException            If file is not found
     * @throws ClassNotFoundException If there is error in loading the ArrayList object from the file
     */
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream(this.filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        ArrayList<Task> data = (ArrayList<Task>) readStream.readObject();
        readStream.close();
        return data;
    }

    /**
     * Write ArrayList to file.
     *
     * @param tasks    ArrayList of tasks.
     * @param filePath Name of file.
     */
    public void writeToFile(ArrayList<Task> tasks, String filePath) {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(tasks);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
