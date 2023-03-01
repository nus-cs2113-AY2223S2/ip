package components;

import task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, ClassNotFoundException {

        FileInputStream readData = new FileInputStream(this.filePath);
        ObjectInputStream readStream = new ObjectInputStream(readData);
        ArrayList<Task> data = (ArrayList<Task>) readStream.readObject();
        readStream.close();
        return data;
    }

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

//    public void ArrayList<Task> readFromFile(ArrayList<Task> tasks, String filePath) {
//        File file = new File("duke.txt");
//        if (file.exists()) {
//            try {
//                FileInputStream readData = new FileInputStream(filePath);
//                ObjectInputStream readStream = new ObjectInputStream(readData);
//                tasks = (ArrayList<Task>) readStream.readObject();
//                readStream.close();
//            } catch (Exception e) {
//                System.out.println("Error in file");
//            }
//        }
//        else {
//            tasks = new ArrayList<>();
//        }
//        return tasks;
//    }
}
