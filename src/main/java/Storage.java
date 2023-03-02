import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;


public class Storage {
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public static void readFile(String filePath) throws FileNotFoundException{
        try {
                FileReader fr = new FileReader(filePath);
                String saved_text = "";
            
            int i;
            while ((i = fr.read()) != -1) {
                if ((char) i == '\n') {
                    String[] strArray = saved_text.split(" \\| ");
                    if (strArray[0].equals("TODO")) {
                        Task.tasks.add(Task.TaskType.TODO);              
                    }
                    else if (strArray[0].equals("DEADLINE")) {
                        Task.tasks.add(Task.TaskType.DEADLINE);
                    }
                    else if (strArray[0].equals("EVENT")) {
                        Task.tasks.add(Task.TaskType.EVENT);
                    }
                    if (strArray[1].equals("1")) {
                        Task.marked.add(true);
                    }
                    else if (strArray[1].equals("0")) {
                            Task.marked.add(false);
                    }
                    Task.items.add(strArray[2]);
                    if (strArray[3] == null || strArray[3].equals("null")) {
                        Task.dateTimeFrom.add(null);
                    }
                    else {
                        Task.dateTimeFrom.add(LocalDate.parse(strArray[3]));
                    }
                    if (strArray[4] == null || strArray[4].equals("null")) {
                        Task.dateTimeTo.add(null);
                    }
                    else {
                        Task.dateTimeTo.add(LocalDate.parse(strArray[4]));
                    }
                    saved_text = "";
                }
                else {
                    saved_text += (char) i;
                }
            } 
            fr.close();
            System.out.println("File read successfully");
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    public static void save() {
        try {
            Files.createDirectories(Paths.get("data"));
            writeToFile("data/duke.txt", "");
            String file = "";
            for (int i = 0; i < Task.items.size(); i++) {
                file = (file  + Task.tasks.get(i).toString() + " | " + (Task.marked.get(i) ? "1" : "0") + " | " + Task.items.get(i) +  " | " + Task.dateTimeFrom.get(i) + " | " + Task.dateTimeTo.get(i) + "\n");
            }
            writeToFile("data/duke.txt", file);
            System.out.println("File saved successfully");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
