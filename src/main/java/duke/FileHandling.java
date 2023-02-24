package duke;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class FileHandling {
    public static void saveContents() {
        try {
            File fileObj = new File("./tasklist.txt");
            if (fileObj.createNewFile()) {
                System.out.println("File created: " + fileObj.getName());
                System.out.println(fileObj.getAbsolutePath()); // checker
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        try {
            FileWriter myWriter = new FileWriter("./tasklist.txt");
            for (Task task : Echo.tasks) {
                if (task.getSymbol() == "T") {
                    myWriter.write(task.getSymbol() + "|" + task.getStatusIcon() + "|" + task.description + '\n');
                } else if (task.getSymbol() == "D") {
                    Deadline obj = (Deadline) task;
                    myWriter.write(task.getSymbol() + "|" + task.getStatusIcon() + "|" + task.description + "|" + obj.by + '\n');
                } else if (task.getSymbol() == "E") {
                    Event obj = (Event) task;
                    myWriter.write(task.getSymbol() + "|" + task.getStatusIcon() + "|" + task.description + "|" + obj.start + "|" + obj.end + '\n');
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    public static void loadContents() {
        try {
            System.out.println("Loading contents...");
            File myObj = new File("./tasklist.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String array[] = data.split("|");
                if (array[0].equals("T")) {
                    Todo obj = new Todo(array[2]);
                    if (array[1].equals("X")) {
                        obj.markAsDone();
                    } else {
                        obj.markAsUndone();
                    }
                    Echo.tasks.add(obj);
                } else if (array[1].equals("X")) {
                    Deadline obj = new Deadline(array[2], array[3]);
                    if (array[1] == "X") {
                        obj.markAsDone();
                    } else {
                        obj.markAsUndone();
                    }
                    Echo.tasks.add(obj);
                } else if (array[1].equals("X")) {
                    Event obj = new Event(array[2], array[3], array[4]);
                    if (array[1] == "X") {
                        obj.markAsDone();
                    } else {
                        obj.markAsUndone();
                    }
                    Echo.tasks.add(obj);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No contents found...");
        }
    }
}
