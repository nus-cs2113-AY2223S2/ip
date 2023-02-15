import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Paths;

public class FileAccess {
    public static void main(ArrayList<Task> tasks) throws IOException {
        Files.createDirectories(Paths.get("./data"));
        String filePath = "data/duke.txt";
        try {
            writeToFile(filePath, tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            String taskType = task.getType();
            String line = "";

            switch (taskType) {
            case "todo":
                line = line.concat("T |" + task.getStatusIcon() + "| " + task.description);
                fw.write(line);
                break;

            case "deadline":
                line = line.concat("D |" + task.getStatusIcon() + "| " + task.description);
                line = line.concat(" (by: " + task.getTimings());
                fw.write(line);
                break;

            case "event":
                line = line.concat("E |" + task.getStatusIcon() + "| " + task.description);
                String[] timings = task.getTimings().split("/");
                line = line.concat(" (from: " + timings[0] + " to: " + timings[1] + ")\n");
                fw.write(line);
                break;
            }
        }
        fw.close();
    }
}
