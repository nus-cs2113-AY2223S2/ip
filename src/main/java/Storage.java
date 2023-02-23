import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

class Storage {
    public static TaskList initialisation() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        //create folder if it does not exists
        String currDir = System.getProperty("user.dir");
        String assetsPath = java.nio.file.Paths.get(currDir, "assets").toString();
        File assets = new File(assetsPath);
        if (!assets.exists()) {
            assets.mkdir();
        }
        //create file if it does not exists
        String databasePath = java.nio.file.Paths.get(assetsPath, "database.txt").toString();
        File database = new File(databasePath);
        try {
            if (!database.createNewFile()) {
                database.createNewFile();
            }
        } catch (IOException e) {}

        try {
            Scanner reader = new Scanner(database);
            while (reader.hasNextLine()) {
                String[] inputArray = reader.nextLine().split(",");
                taskList = Storage.readAndUpdate(inputArray, taskList);
            }
            reader.close();
        } catch (IOException e) {}
        return new TaskList(taskList);
    }

    public static ArrayList<Task> readAndUpdate(String[] input, ArrayList<Task> database) {
        if (input[0].equals("T")) {
            database.add(new ToDo(input));
        } else if (input[0].equals("D")) {
            database.add(new Deadline(input));
        } else if (input[0].equals("E")) {
            database.add(new Event(input));
        }
        return database;
    }

    public static void updateStorage(TaskList input) {
        String currDir = System.getProperty("user.dir");
        String databasePath = java.nio.file.Paths.get(currDir, "assets", "database.txt").toString();
        File database = new File(databasePath);

        try {
            FileWriter myWriter = new FileWriter(database);
            for (int i = 0; i < input.size(); i++) {
                Task task = input.get(i);
                myWriter.write(task.toStringForDatabase() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {}
    }

}