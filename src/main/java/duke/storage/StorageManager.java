package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.utils.Input;

public class StorageManager {

    public static void writeToFile(ArrayList<Task> Tasks) throws IOException {
        FileWriter fw = new FileWriter("data.txt");
        BufferedWriter info = new BufferedWriter(fw);
        for (int i = 0; i < Tasks.size(); i++) {
            String lineToAdd = Input.parseTaskToWrite(Tasks.get(i));
            info.write(lineToAdd);
            info.newLine();
        }
        info.close();
    }

    public static ArrayList<Task> readFileContents() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Task task;
        try {
            File f = new File("data.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                task = Input.parseFileContent(sc.nextLine());
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            File newFile = new File("data.txt");
            newFile.createNewFile();
        }
        return tasks;

    }


}
