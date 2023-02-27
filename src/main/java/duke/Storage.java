package duke;

import duke.task.TaskList;
import duke.task.TaskType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    private static String filePath = "data/data.txt";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static TaskList loadData() {
        TaskList tasks = new TaskList();

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String rawData = myReader.nextLine();
                String[] dataList = rawData.split("&", 3);

                switch (dataList[0].trim()) {
                case "T":
                    tasks.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.TODO);
                    break;
                case "D":
                    tasks.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.DEADLINE);
                    break;
                case "E":
                    tasks.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.EVENT);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
        }

        return tasks;
    }
    public static void createFile() {
        try {
            if (new File("data").mkdirs()) {
                File data = new File(filePath);
                data.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            String data = TaskList.writeTask();
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
