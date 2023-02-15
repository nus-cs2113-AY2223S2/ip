import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
public class FileSaver {

    protected static String filePath;

    public FileSaver(String filePath){
        FileSaver.filePath = filePath;
    }

    public void load() {
        File loadData = new File(filePath);
        try {
            Scanner newScanner = new Scanner(loadData);
            while (newScanner.hasNext()) {
                processLine(newScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    public void processLine(String line) {
        String[] newLine = line.split(" | ", 2);
        switch (newLine[1].substring(2,3)) {
            case "t":
                TaskArray.createToDo(newLine[1].substring(3));
                break;
            case "d":
                TaskArray.createDeadline(newLine[1].substring(3));
                break;
            case "e":
                TaskArray.createEvent(newLine[1].substring(3));
                break;
        }
        if (newLine[0].equals("true")) {
            TaskArray.storedTasks[TaskArray.getCount()-1].markAsDone();
        }
    }



    public static void createFile() {
        try {
            File newFile = new File(filePath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        } catch (java.io.IOException e) {
            System.out.println("Unknown error occurred. File was not created.");
        }
    }

    public static void addToFile(Task task){
        try {
            FileWriter file = new FileWriter(filePath, true);
            file.write(task.toFile());
            file.close();
        }catch (java.io.IOException e){
            System.out.println("Unknown error occurred. Task-file was not updated.");
        }
    }

    public static void updateFile(){
        try {
            FileWriter file = new FileWriter(filePath);
            for (int i = 0; i < TaskArray.getCount(); i++) {
                file.write(TaskArray.storedTasks[i].toFile());
            }
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Unable to write to file...");
        }
    }
}
