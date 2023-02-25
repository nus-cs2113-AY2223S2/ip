package Storage;

import Tasks.Task;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    public static void fileWrite(ArrayList<Task> task1) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Rolex.txt"));
        for (Task x : task1) {
            writer.write(x.giveTaskName() + ";" + x.giveTaskStatus() + ";" + x.giveTaskSign() + "\n");
        }
        writer.close();
    }

    public static ArrayList<Task> fileAccess(File fileName) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String input;
        while((input = bufferedReader.readLine())!=null){
            String[] line = input.split(";");
            Task r = new Task(line[0], Boolean.parseBoolean(line[1]), line[2]);
            taskList.add(r);
        }
        bufferedReader.close();
        return taskList;
    }

    public static ArrayList<Task> createFile() throws IOException {
        File fileName = new File("rolex.txt");
        if(fileName.exists()){
            return fileAccess(fileName);
        }
        return new ArrayList<>();
    }
}
