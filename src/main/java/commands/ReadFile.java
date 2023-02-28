package commands;

import exceptions.InvalidTaskException;
import tasks.Task;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class ReadFile {
    public static void readFile(File f, ArrayList<Task> list) {
        /**
         * Loads and saves TaskList from or to a file.
         */
        try {
            Scanner myReader = new Scanner(f);
            System.out.println("Reading File");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                SaveToCurrentOperation.save(data, list);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }

    }
}
