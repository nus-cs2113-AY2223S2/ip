package duke;

import duke.task.List;
import duke.task.Task;
import duke.task.TaskType;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Data {

    public static void loadData() {
        try {
            File myObj = new File("data/data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String rawData = myReader.nextLine();
                String[] dataList = rawData.split("&", 3);

                switch (dataList[0].trim()) {
                case "T":
                    List.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.TODO);
                    break;
                case "D":
                    List.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.DEADLINE);
                    break;
                case "E":
                    List.loadTask(dataList[1].trim(), dataList[2].trim(), TaskType.EVENT);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }
    public static void createFile() {
        try {
            if (new File("data").mkdirs()) {
                File data = new File("data/data.txt");
                data.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("data/data.txt");
            String data = List.writeTask();
            myWriter.write(data);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
