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

    public void writeToFile(String text) {
        try {
            createFileIfNotExist();
            FileWriter writer = new FileWriter("mongo.txt");
            writer.write(text);
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
