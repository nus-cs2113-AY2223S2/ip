import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

class Backend {
    public static ArrayList<Task> initialisation() {
        ArrayList<Task> res = new ArrayList<Task>();

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
                String[] inputtArray = reader.nextLine().split(",");
                res = Backend.readAndUpdate(inputtArray, res);
            }
            reader.close();
        } catch (IOException e) {}
        return res;
    }

    public static ArrayList<Task> readAndUpdate(String[] inputt, ArrayList<Task> database) {
        if (inputt[0].equals("T")) {
            database.add(new ToDo(inputt));
        } else if (inputt[0].equals("D")) {
            database.add(new Deadline(inputt));
        } else if (inputt[0].equals("E")) {
            database.add(new Event(inputt));
        }
        return database;
    }

    public static void updateDatabase(ArrayList<Task> inputt) {
        String currDir = System.getProperty("user.dir");
        String databasePath = java.nio.file.Paths.get(currDir, "assets", "database.txt").toString();
        File database = new File(databasePath);

        try {
            FileWriter myWriter = new FileWriter(database);
            for (Task t : inputt) {
                myWriter.write(t.toStringForDatabase() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {}
    }

}