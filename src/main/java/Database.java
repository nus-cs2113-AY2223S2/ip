import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
    public static String memoryContents = "";
    protected ArrayList<Task> listOfTasks;

    public Database() throws IOException {
        File memory = new File("data/database.txt");
        this.listOfTasks = new ArrayList<>();
        if (memory.exists()) {
            Scanner scan = new Scanner(memory);
            while (scan.hasNextLine()) {
                String item = scan.nextLine();
                int idx = item.indexOf("---");
                String input = item.substring(0,idx);
                if (item.contains("--- T |")) {
                    ToDo newTodo = new ToDo (input);
                    listOfTasks.add(newTodo);
                }else if (item.contains("--- E |")) {
                    int idxOfSlash = input.indexOf('/');
                    Event newEvent = new Event(input.substring(0, idxOfSlash),
                            input.substring(idxOfSlash + 6, input.indexOf('/', idxOfSlash + 1)),
                            input.substring(input.indexOf('/', idxOfSlash + 1) + 4, idx - 1));
                    listOfTasks.add(newEvent);
                } else if(item.contains("--- D |")) {
                    int idxOfSlash = input.indexOf('/');
                    Deadline newDeadline = new Deadline(input.substring(0, idxOfSlash), input.substring(idxOfSlash + 4, idx - 1));
                    listOfTasks.add(newDeadline);
                }
                memoryContents += (item + "\n");
            }
            System.out.println("Currently in memory: \n" + memoryContents);
        } else {
            File directory = new File("data");
            directory.mkdirs();
            memory.createNewFile();
        }
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/database.txt", true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("data/database.txt");
        fw.write(textToAdd);
        fw.close();
    }


}
