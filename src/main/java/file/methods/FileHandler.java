package file.methods;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.Deadline;

public class FileHandler {

    public static void setUpFile(ArrayList<Task> actions) {
        try {
            File f = new File("data/savedList.txt");
            File directory = new File("data");
            if (!directory.exists()) {
                boolean success = directory.mkdir();
                if (!success) {
                    System.out.println("Met an error creating the directory");
                }
            }
            if (!f.exists()) {
                f.createNewFile();
                return;
            }
            String line;
            String[] decisions;
            Scanner in = new Scanner(f);
            Task toBeAdded;
            while (in.hasNext()) {
                line = in.nextLine();
                decisions = line.split("/");
                switch(decisions[0]) {
                    case "t":
                        toBeAdded = new Todo(decisions[2]);
                        if (decisions[1].equals("X")) {
                            toBeAdded.mark();
                        }
                        actions.add(toBeAdded);
                        break;

                    case "d":
                        toBeAdded = new Deadline(decisions[2],decisions[3]);
                        if (decisions[1].equals("X")) {
                            toBeAdded.mark();
                        }
                        actions.add(toBeAdded);
                        break;

                    case "e":
                        toBeAdded = new Event(decisions[2],decisions[3],decisions[4]);
                        if (decisions[1].equals("X")) {
                            toBeAdded.mark();
                        }
                        actions.add(toBeAdded);
                        break;

                    default:
                        System.out.println("File has been corrupted");
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    public static void saveFile(ArrayList<Task> actions) {
        try {
            FileWriter fw = new FileWriter("data/savedList.txt");
            for (Task i : actions) {
                fw.write(i.getCommand() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
