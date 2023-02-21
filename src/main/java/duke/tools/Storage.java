package duke.tools;

import duke.TaskManager;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final static String DATA_PATH = "duke_data.text";
    private final static String TEMP_PATH = "temp_data.text";
    private final static File DATA_FILE = new File(DATA_PATH);

    public static void loadData() throws IOException {
        if(DATA_FILE.exists()){
            Scanner readFile = new Scanner(DATA_FILE);
            while(readFile.hasNextLine()){
                String data = readFile.nextLine();
                //Use [] for special characters
                String[] fileData = data.split("[|]");
                if(fileData[0].equals("T")){
                    String description = fileData[2];
                    Task task = new Todo(description);
                    if(fileData[1].equals("1")){
                        task.markDone();
                    }
                    TaskManager.loadTask(task);
                }else if(fileData[0].equals("D")){
                    String description = fileData[2];
                    String deadline = fileData[3];
                    Task task = new Deadline(description, deadline);
                    if(fileData[1].equals("1")){
                        task.markDone();
                    }
                    TaskManager.loadTask(task);
                }else if(data.charAt(0)=='E'){
                    String description = fileData[2];
                    String start = fileData[3];
                    String end = fileData[4];
                    Task task = new Event(description, start, end);
                    if(fileData[1].equals("1")){
                        task.markDone();
                    }
                    TaskManager.loadTask(task);
                }
            }
            readFile.close();
        }
    }

    public static void copyToFile( ) throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_PATH);
        ArrayList<Task> tasks = TaskManager.getALLTasks();
        if(tasks.size()==0){
            fileWriter.write("");
        }else{
            for(Task task: tasks){
                String data = task.convertToData();
                fileWriter.write(data);
            }
        }

        fileWriter.close();
    }

    public static void updateData(String command, int index) throws IOException{
        Scanner read = new Scanner(DATA_FILE);
        String data;
        int count = 0;
        while (read.hasNext()) {

            data = read.nextLine();
            if (count != index) {
                writeToFile(data + "\n", TEMP_PATH);
            } else {
                StringBuilder setter = new StringBuilder(data);
                if(command.equals("mark")){
                    setter.setCharAt(2, '1');
                    writeToFile(setter + "\n", TEMP_PATH);
                }else if(command.equals("unmark")){
                    setter.setCharAt(2, '0');
                    writeToFile(setter + "\n", TEMP_PATH);
                }
            }
            count += 1;
        }
        read.close();

        Files.copy(Paths.get(TEMP_PATH), Paths.get(DATA_PATH), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(TEMP_PATH));
    }

    public static void writeToFile(String data, String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path, true);
        fileWriter.write(data);
        fileWriter.close();
    }
}
