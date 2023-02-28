package commands;

import tasks.Task;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class Exit {

    /** 
     * Save the data stored in list to a txt file
     */
    public static void saveFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("data/userData.txt");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                int isDone = task.getIsDone() ? 1 : 0;
                fw.write(isDone + " " + task.getUserInput() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file. Something went wrong");
        }
    }
}
