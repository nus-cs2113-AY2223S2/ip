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
            } else{
                ArrayList<String> existingTasks = scanData(file);
                loadData(existingTasks);
            }
        } catch(IOException e){
            System.out.println("File cannot be created");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> scanData(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> existingTasks = new ArrayList<>();
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            String taskInfo = data.substring(6,7) + "/" + data.substring(8);
            existingTasks.add(taskInfo);
        }
        return existingTasks;
    }

    public void loadData(ArrayList<String> existingTasks){
        for(String taskInfo : existingTasks){
            String taskState = taskInfo.substring(0,1);
            String[] taskContent = taskInfo.substring(3).split("/");
            switch(taskContent.length){
                case 1:
                    taskList.addTodo(taskContent);
                    break;
                case 2:
                    taskList.addDeadline(taskContent);
                    break;
                case 3:
                    taskList.addEvent(taskContent);
                    break;
            }
            if(taskState.equals("O")) taskList.markTask(taskList.getTotalTaskNum());
        }
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
