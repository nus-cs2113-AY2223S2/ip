package duke.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static String filepath;
    protected ArrayList<Task> listOfTask;

    public Storage(String filepath, ArrayList<Task> listOfTask) {
        this.filepath = filepath;
        this.listOfTask = listOfTask;
    }

    public static void printFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }

    public static void writeFile(String filePath, String textAdd) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(textAdd);
        writer.close();
    }

    public static void updateFile(String filePath, ArrayList<Task> listOfTask) throws IOException {
        String newList = "";
        for (int i = 0; i < listOfTask.size(); i++) {
            newList += listOfTask.get(i).toString() + System.lineSeparator();
        }
        writeFile(filePath, newList);
    }
}
