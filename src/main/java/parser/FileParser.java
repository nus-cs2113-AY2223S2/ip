package parser;

import com.google.gson.Gson;
import model.task.Task;

import java.io.*;
import java.util.Scanner;

public class FileParser {

    protected static FileParser instance = null;
    protected static final String FILE_NAME = "data.json";
    protected static final Gson gson = new Gson();
    protected static File db = new File(FILE_NAME);

    /**
     * This function is used to create a file if it does not exist. This helps
     * the user to reduce the trouble of having to create his own file and
     * creating it in the incorrect location.
     */
    protected static void createFileIfNotExist() {
        try {
            db.createNewFile();
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
            Task[] tasks = gson.fromJson(br, Task[].class);
            System.out.println(tasks.toString());
        } catch (FileNotFoundException e) {
            System.out.println("A file not found exception occured");
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

    public void updateFile(String messageToChange, String newMessage) {
        try {
            // Step 1: Create a temporary file and a writer to it.
            String TEMP_FILE_NAME = "temp.txt";
            File temp = new File(TEMP_FILE_NAME);
            temp.createNewFile();
            FileWriter writer = new FileWriter(TEMP_FILE_NAME);

            // Step 2: Create a scanner to the current file            
            String CURRENT_FILE_NAME = "mongo.txt";
            File db = new File(CURRENT_FILE_NAME);
            Scanner reader = new Scanner(db);

            // Step 3: If the current line is not the information to be
            // changed, copy it over to the temp file. If the current 
            // line is to be changed, add in the newMessage instead.
            while (reader.hasNextLine()) {
                String information = reader.nextLine().trim();
                if (information != messageToChange) {
                    writer.write(information);
                } else {
                    writer.write(newMessage);
                }
            }

            // Step 4: Delete the old file and rename the temp file to
            // be the old file
            db.delete();
            temp.renameTo(db);

            // Step 5: Close the reader and the writer
            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occured during the update process");
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
