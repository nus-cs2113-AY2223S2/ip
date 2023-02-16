package Onandon.checkpoint;

import java.io.*;
import Onandon.module.*;

import java.util.Arrays;
import java.util.List;

public class Checkpoint {
    protected static final String PATH = "src/main/java/";
    protected static final String CHECKPOINT = "checkpoint.txt";

    public static void storeCheckpoint(Task[] tasks, int cnt){
        String store = "";

        for(int i=0; i<cnt; i++){
            if(tasks[i] instanceof Todo){
                store += ("T/" + tasks[i].getStatusIcon() + "/" + tasks[i].getDescription() + "\n");
            } else if (tasks[i] instanceof Deadline) {
                store += ("D/" + tasks[i].getStatusIcon() + "/" + tasks[i].getDescription());
                store += "/" + tasks[i].getBy() + "\n";
            } else if (tasks[i] instanceof Event) {
                store += "E/" + tasks[i].getStatusIcon() + "/" + tasks[i].getDescription();
                store += "/" + tasks[i].getFrom() + "/" + tasks[i].getTo() + "\n";
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

    public static List<Object> recallCheckpoint() {
        Task[] tasks = new Task[100];
        String checkpoint = "";
        String[] checkpointArray;
        String[] sen;
        int cur;
        int cnt = 0;

        try{
            File file = new File(PATH + CHECKPOINT);

            if(!file.exists())
                return Arrays.asList(tasks, cnt);

            FileReader file_reader = new FileReader(file);

            while((cur = file_reader.read()) != -1){
                checkpoint += String.valueOf((char)cur);
            }

            checkpointArray = checkpoint.split("\n");

            for(String c : checkpointArray) {
                sen = c.split("/");

                switch(sen[0]){
                case "T":
                    tasks[cnt] = new Todo(sen[2]);
                    if(sen[1].equals("X"))
                        tasks[cnt].markAsDone();
                    else if(sen[1].equals(" "))
                        tasks[cnt].markAsUnDone();
                    cnt += 1;
                case "D":
                    tasks[cnt] = new Deadline(sen[2], sen[3]);
                    if(sen[1].equals("X"))
                        tasks[cnt].markAsDone();
                    else if(sen[1].equals(" "))
                        tasks[cnt].markAsUnDone();
                    cnt += 1;
                case "E":
                    tasks[cnt] = new Event(sen[2], sen[3], sen[4]);
                    if(sen[1].equals("X"))
                        tasks[cnt].markAsDone();
                    else if(sen[1].equals(" "))
                        tasks[cnt].markAsUnDone();
                    cnt += 1;
                }
            }

            file_reader.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        return Arrays.asList(tasks, cnt);
    }
}
