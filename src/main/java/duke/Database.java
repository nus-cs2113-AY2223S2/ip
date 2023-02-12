package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Database {

    protected ArrayList<Task> tasks;
    public static final String FILE_PATH = "data/duke.txt";

    public Database() {
        this.tasks = new ArrayList<>();
        try {
            initialise();
        } catch (IOException e) {
            System.out.println("failed to initialise database");
        }
    }

    public void saveAddTask(String[] information) throws IOException{
        FileWriter fw = new FileWriter(FILE_PATH,true);
        fw.write(writeTask(information));
        fw.close();
    }

    public static String writeTask(String[] information) {
        String output = "";
        for (String string : information) {
            output += string + " , ";
        }
        output += System.lineSeparator();
        return output;
    }
    private void initialise() throws IOException {
        File savedData = new File(FILE_PATH);
        if (savedData.exists()) {
            Scanner contents = new Scanner(savedData);
            ArrayList<String> databaseString = new ArrayList<>();
            while (contents.hasNext()) {
                databaseString.add(contents.nextLine());
            }
            dataConversion(databaseString);
        } else {
            File directory = new File("data");
            directory.mkdirs();
            savedData.createNewFile();
        }

    }

    private void dataConversion(ArrayList<String> databaseString) {
        for (String data : databaseString) {
            String[] information = data.split(" , ");
            Task task = new Task("");
            boolean corrupted = false;
            switch (information[0]) {
            case "T":
                task = new Todo(information[2]);
                break;
            case "D":
                task = new Deadline(information[2], information[3]);
                break;
            case "E":
                task = new Event(information[2], information[3], information[4]);
                break;
            default:
                corrupted = true;
                System.out.println("file is corrupted");
            }
            if (!corrupted) {
                if (information[1].equals("1")) {
                    task.setDone("mark");
                } else if (information[1].equals("0")) {
                    task.setDone("unmark");
                } else {
                    System.out.println("file is corrupted");
                }
                tasks.add(task);
            }
        }
    }


}
