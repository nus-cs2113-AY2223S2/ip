import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Storage {
    public final static String FILEPATH = "./duke.txt";
    private TaskList taskList;

    public Storage(TaskList taskList){
        this.taskList = taskList;
        try {
            File file = new File(FILEPATH);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
        } catch(IOException e){
            System.out.println("File cannot be created");
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> scanData() throws IOException {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> existingTasks = new ArrayList<>();
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            String taskInfo = data.substring(6,7) + "/" + data.substring(8);
            existingTasks.add(taskInfo);
        }
        return existingTasks;
    }


    public void writeToFile(String textAdded){
        try {
            File file = new File(FILEPATH);
            FileWriter fw = new FileWriter(file);
            fw.write(textAdded);
            fw.close();
        } catch (IOException e){
            System.out.println("Not saved correctly.");
            System.out.println(e.getMessage());
        }
    }

}
