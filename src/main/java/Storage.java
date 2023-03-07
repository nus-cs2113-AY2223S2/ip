import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static String memoryContents = "";

    public Storage(ArrayList<Task> listOfTasks) throws IOException {
        File memory = new File("data/database.txt");
        if (memory.exists()) {
            Scanner scan = new Scanner(memory);
            while (scan.hasNextLine()) {
                String item = scan.nextLine();
                Task newTask = Parser.readDatabaseTasks(TaskList.listOfTasks, item);
                listOfTasks.add(newTask);
                memoryContents += (item + "\n");
            }
        } else {
            File directory = new File("data");
            directory.mkdirs();
            memory.createNewFile();
        }
    }

    /**
     * Append a String into the database txt file
     * @param textToAppend String that is about to be appended into the txt file
     * @throws IOException
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/database.txt", true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Create a new database txt file and add a String into the txt file
     * @param textToAdd String that is about to be added into the txt file
     * @throws IOException
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/database.txt");
        fw.write(textToAdd);
        fw.close();
    }


}
