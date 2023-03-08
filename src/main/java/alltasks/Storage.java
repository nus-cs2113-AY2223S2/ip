//Solution below adapted and reused from Student Oh Yi Xiu Wilson
// with modifications made by Wilson Lee Jun Wei

package alltasks;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private ArrayList<Task> listTasks;
    private Path taskPath;

    public Storage(ArrayList<Task> listTasksArgs) {
        this.listTasks = listTasksArgs;
        String configHomeString;
        // Get the "XDG_CONFIG_HOME" based on the OS of user, based on windows or macs
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // this is for windows
            configHomeString = System.getenv("LOCALAPPDATA");
        } else {
            // this is for MACOS
            configHomeString = System.getenv("HOME");
        }
        Path coffeeHome = Path.of(configHomeString).resolve("coffee");
        try {
            if (Files.notExists(coffeeHome)) {
                Files.createDirectory(coffeeHome);
            }
            Path tasksHome = coffeeHome.resolve("coffeeTasks.txt");
            if (Files.notExists(tasksHome)) {
                Files.createFile(tasksHome);
            }
            this.taskPath = tasksHome;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void writeTasksToFile() {
        StringBuilder buildString = new StringBuilder();
        for (Task task : this.listTasks) {
            buildString.append(task.getInfo() + "\n");
        }
        try {
            Files.writeString(this.taskPath, buildString.toString());
        } catch (IOException ioExceptions) {
            ioExceptions.printStackTrace();
        }
    }

    public ArrayList<Task> get_Tasks_From_File() {
        ArrayList<Task> retrieveTask = new ArrayList<>();
        try {
            List<String> tasksLists = Files.readAllLines(this.taskPath);
            for (String taskString : tasksLists) {
                if (taskString.isEmpty()) {
                    continue;
                }
                Task toAddItem = new ToDo(""); // mark task as done if needed

                String[] tokens = taskString.split("\\|");
                switch (tokens[0]) {
                case "Todo":
                    toAddItem = new ToDo(tokens[2]);
                    break;
                case "Deadline":
                    toAddItem = new Deadline(tokens[2], tokens[3]);
                    break;
                case "Event":
                    toAddItem = new Event(tokens[2], tokens[3], tokens[4]);
                default:
                }
                if (tokens[1].equals("true")) {
                    toAddItem.markAsDone();
                }
                retrieveTask.add(toAddItem);
            }
        } catch (IOException exceptions) {
            exceptions.printStackTrace();
        }
        return retrieveTask;
    }
}
//@@ Student Oh Yi Xiu Wilson




