import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {
    // function to write to file
    public static void writeToFile(String filePath, String textToAdd) throws IOException { // overwrites file
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    // reads file from data/duke.txt
    // scans the file line by line
    // splits the line by the delimiter " | "
    // stores the task type, status, description, date and time into the respective
    // arraylists
    // constructor cannot be used due to the way the constructor is written
    public static void readFile(String filePath) throws FileNotFoundException { // reads file
        File file = new File(filePath);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String[] strArray = reader.nextLine().split(" \\| ");
            if (strArray[0].trim().equals("TODO")) // store task type
                Task.setAddTypeArray(Task.TaskType.TODO);
            else if (strArray[0].trim().equals("DEADLINE"))
                Task.setAddTypeArray(Task.TaskType.DEADLINE);
            else if (strArray[0].trim().equals("EVENT"))
                Task.setAddTypeArray(Task.TaskType.EVENT);
            if (strArray[1].trim().equals("1")) // store task status
                Task.setAddMarkArray(true);
            else
                Task.setAddMarkArray(false);
            if (strArray[3].trim().equals("null")) // store date and time
                Task.setAddDateFromArray(null);
            else
                Task.setAddDateFromArray(LocalDateTime.parse(strArray[3].trim()));
            if (strArray[4].trim().equals("null"))
                Task.setAddDateToArray(null);
            else
                Task.setAddDateToArray(LocalDateTime.parse(strArray[4].trim()));
            Task.setAddDescArray(strArray[2].trim()); // store task description
        }
        reader.close();
        System.out.println("File read successfully");
    }

    // saves file to data/duke.txt
    // creates a new directory if it does not exist
    // writes to string file by reading the arraylists
    // then writes to file to save
    public static void save() {
        try {
            Files.createDirectories(Paths.get("data"));
            String file = "";
            for (int i = 0; i < Task.getSize(); i++) {
                file = (file + Task.getType(i).toString() + " | " + (Task.getMark(i) ? "1" : "0") + " | "
                        + Task.getDesc(i) + " | " + Task.getDateTimeFrom(i) + " | " + Task.getDateTimeTo(i) + "\n");
            }
            writeToFile("data/duke.txt", file);
            System.out.println("File saved successfully");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
