package parser;

import com.google.gson.Gson;
import model.storage.JsonStorage;
import controller.TaskController;

import java.io.*;

public class FileParser {

    protected static FileParser instance = null;
    protected static final String FILE_NAME = "data.json";
    protected static final TaskController taskController = new TaskController();
    protected static final Gson gson = new Gson();
    protected static File file = new File(FILE_NAME);

    /**
     * This function is used to create a file if it does not exist. This helps
     * the user to reduce the trouble of having to create his own file and
     * creating it in the incorrect location.
     */
    protected static void createFileIfNotExist() {
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An IO Exception occured");
            e.printStackTrace();
        }
    }

    /**
     * This function is used to read from an existing file. In order to reduce
     * the possibility of file does not exist, I will forcefully create a new
     * file prior to reading.
     */
    public static void readFromFile() {
        try {
            createFileIfNotExist();
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            JsonStorage[] x = gson.fromJson(br, JsonStorage[].class);
            for (JsonStorage item: x) {
                String type = item.getType();
                switch (type) {
                case "todo":
                case "event":
                case "deadline":
                default:
                break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("A file not found exception occured");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This function is used to write a line to the file.
     *
     * @param text The text data to be written into a file
     */
    public void writeToFile(String text) {
        try {
            createFileIfNotExist();
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.append(text + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            System.out.println("Oops, something went wrong");
            e.printStackTrace();
        }
    }

    protected FileParser() {
    }

    public static FileParser getInstance() {
        if (instance == null) {
            instance = new FileParser();
        }

        createFileIfNotExist();
        return instance;
    }
}
