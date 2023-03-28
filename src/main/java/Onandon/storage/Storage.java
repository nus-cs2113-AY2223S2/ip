package Onandon.storage;

import java.io.*;
import Onandon.module.*;

import java.util.ArrayList;

/**
 * Representation of the store/load function.
 * This clas contains 2 functions :
 * 1) one stores the current status of the list of the tasks into 'checkpoint.txt' file.
 * 2) The other loads stored status of the tasks list and allocates into TaskList class.
 *
 * This class allow user to store/load their schedule so that they can easily continue to
 * edit even if the program is terminated.
 */
public class Storage {
    protected static final String PATH = System.getProperty("user.dir");
    protected static final String CHECKPOINT = "/checkpoint.txt";

    /**
     * Stores the current status of the list of the tasks into 'checkpoint.txt' file
     * so that user can keep their plan list eventhough the program is terminated.
     *
     * @param tasks ArrayList of the tasks.
     */
    public static void storeCheckpoint(TaskList tasks){
        String store = "";

        for(int i=0; i<tasks.getNum(); i++){
            if(tasks.get(i) instanceof Todo){
                store += ("T/" + tasks.get(i).getStatusIcon() + "/" + tasks.get(i).getDescription() + "\n");
            } else if (tasks.get(i) instanceof Deadline) {
                store += ("D/" + tasks.get(i).getStatusIcon() + "/" + tasks.get(i).getDescription());
                store += "/" + tasks.get(i).getBy() + "\n";
            } else if (tasks.get(i) instanceof Event) {
                store += "E/" + tasks.get(i).getStatusIcon() + "/" + tasks.get(i).getDescription();
                store += "/" + tasks.get(i).getFrom() + "/" + tasks.get(i).getTo() + "\n";
            }
        }

        // store checkpoint
        try {
            FileWriter fileWriter = new FileWriter(PATH + CHECKPOINT);
            fileWriter.write(store);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the status of the isDone variable of the task with referring the 'sen' String.
     *
     * @param sen String indicates whether this task is done.
     * @param tasks ArrayList of the tasks.
     * @param index Index of the task user want to process
     */
    public static void setIsDone(String sen, TaskList tasks, int index){
        if(sen.equals("X"))
            tasks.get(index).markAsDone();
        else if(sen.equals(" "))
            tasks.get(index).markAsUnDone();
    }

    /**
     * Loads the 'checkpoint.txt' file and allocates into of ArrayList.
     * User can continue their management from their previous one.
     */
    public static TaskList recallCheckpoint() {
        ArrayList<Task> taskArray = new ArrayList<Task>(100);
        TaskList tasks = new TaskList(taskArray, 0);
        String checkpoint = "";
        String[] checkpointArray;
        String[] sen;
        int cur;
        int cnt = 0;

        try{
            File file = new File(PATH + CHECKPOINT);

            if(!file.exists())
                return tasks;

            FileReader file_reader = new FileReader(file);

            while((cur = file_reader.read()) != -1){
                checkpoint += String.valueOf((char)cur);
            }

            checkpointArray = checkpoint.split("\n");

            for(String c : checkpointArray) {
                sen = c.split("/");

                switch(sen[0]){
                case "T":
                    tasks.add(new Todo(sen[2]));
                    setIsDone(sen[1], tasks, cnt);
                    cnt += 1;
                    break;
                case "D":
                    tasks.add(new Deadline(sen[2], sen[3]));
                    setIsDone(sen[1], tasks, cnt);
                    cnt += 1;
                    break;
                case "E":
                    tasks.add(new Event(sen[2], sen[3], sen[4]));
                    setIsDone(sen[1], tasks, cnt);
                    cnt += 1;
                    break;
                }
            }

            file_reader.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        tasks.setNum(cnt);

        return tasks;
    }
}
