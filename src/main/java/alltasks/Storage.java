//Solution below adapted and reused from Student Oh Yi Xiu Wilson
// with modifications made by Wilson Lee Jun Wei

package alltasks;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private ArrayList<Task> list_Tasks;
    private Path task_Path;

    public Storage(ArrayList<Task> list_Tasks_arg) {
        this.list_Tasks = list_Tasks_arg;
        String config_Home_String;
        // Get the "XDG_CONFIG_HOME" based on the OS of user, based on windows or macs
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // this is for windows
            config_Home_String = System.getenv("LOCALAPPDATA");
        } else {
            // this is for MACOS
            config_Home_String = System.getenv("HOME");
        }
        Path coffee_Home = Path.of(config_Home_String).resolve("coffee");
        try {
            if (Files.notExists(coffee_Home)) {
                Files.createDirectory(coffee_Home);
            }
            Path tasks_Home = coffee_Home.resolve("coffeeTasks.txt");
            if (Files.notExists(tasks_Home)) {
                Files.createFile(tasks_Home);
            }
            this.task_Path = tasks_Home;
        } catch (IOException io_exception) {
            io_exception.printStackTrace();
        }
    }

    public void write_Tasks_To_File() {
        StringBuilder build_String = new StringBuilder();
        for (Task task : this.list_Tasks) {
            build_String.append(task.getInfo() + "\n");
        }
        try {
            Files.writeString(this.task_Path, build_String.toString());
        } catch (IOException io_Exceptions) {
            io_Exceptions.printStackTrace();
        }
    }

    public ArrayList<Task> get_Tasks_From_File() {
        ArrayList<Task> retrieve_Task = new ArrayList<>();
        try {
            List<String> tasks_Lists = Files.readAllLines(this.task_Path);
            for (String task_String : tasks_Lists) {
                if (task_String.isEmpty()) {
                    continue;
                }
                Task to_Add_Item = new Todo(""); // mark task as done if needed

                String[] tokens = task_String.split("\\|");
                switch (tokens[0]) {

                case "Todo":
                    to_Add_Item = new Todo(tokens[2]);
                    break;

                case "Deadline":
                    to_Add_Item = new Deadline(tokens[2], tokens[3]);
                    break;

                case "Event":
                    to_Add_Item = new Event(tokens[2], tokens[3], tokens[4]);
                }
                if (tokens[1].equals("true")) {
                    to_Add_Item.markAsDone();
                }
                retrieve_Task.add(to_Add_Item);
            }
        } catch (IOException exceptions) {
            exceptions.printStackTrace();
        }
        return retrieve_Task;
    }
}
//@@ Student Oh Yi Xiu Wilson




