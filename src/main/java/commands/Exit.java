package commands;

import tasks.Task;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class Exit {
    public static void saveFile(ArrayList<Task> list){
        try {
            FileWriter fw = new FileWriter("/Users/sherlock/ip/data/userData.txt");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fw.write("Type:" + task.getType() + " /Description:" + task.getDescription() + " /toString():" + task + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e){
            System.out.println("Cannot write to file. Something went wrong");
        }
    }
}
