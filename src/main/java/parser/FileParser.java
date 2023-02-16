package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileParser {

    protected static FileParser instance = null;

    /**
     * This function is used to create a file if it does not exist. This helps
     * the user to reduce the trouble of having to create his own file and
     * creating it in the incorrect location.
     *
     * @throws IOException If an I/O exception occurs
     */
    protected static void createFileIfNotExist() {
        try {
            File db = new File("mongo.txt");
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
    protected static void readFromFile() {
        try {
            createFileIfNotExist();
            File db = new File("mongo.txt");
            Scanner reader = new Scanner(db);
            while (reader.hasNextLine()) {
                // TODO: Replace the printing with other logic
                System.out.println(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("A file not found exception occured");
            e.printStackTrace();
        }
    }

    /**
     * This function is used to update the file
     *
     * @param text
     */
    public void writeToFile(String text) {
        try {
            createFileIfNotExist();
            FileWriter writer = new FileWriter("mongo.txt", true);
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
