package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected static String filepath;
    public Storage(String filepath) {
        this.filepath = filepath;
        System.out.println(filepath);
    }


    public static void loadTasksToFile() throws IOException {
        // @@author tangphi-reused
        // Reused from www.w3schools.com/java/java_files_create.asp
        // with minor modifications
        try {
            File file = new File(filepath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists");
            }
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println("READER LINE\n" + line); //Do you want this printed in data.txt?
            }
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred");
            e.printStackTrace();
        }

    }

    public static void writeTasksToFile(ArrayList tasks) throws IOException, ClassNotFoundException {
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter(filepath);
            for (int i = 0; i < tasks.size(); i++ ) {

                Task task = (Task) tasks.get(i);

                if (task instanceof Todo) {
                    myWriter.write(task.getDescription() + "\n");
                }
                else if (task instanceof Event) {
                    myWriter.write(task.getDescription() + "\n");
                }
                else if (task instanceof Deadline) {
                    myWriter.write(task.getDescription() + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred");
            e.printStackTrace();
        }
    }
    // @@author
}
