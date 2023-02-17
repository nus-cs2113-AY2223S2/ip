package Onandon.checkpoint;

import java.io.*;
import Onandon.module.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Checkpoint {
    protected static final String PATH = System.getProperty("user.dir");
    protected static final String CHECKPOINT = "/checkpoint.txt";

    public static void storeCheckpoint(ArrayList<Task> tasks, int cnt){
        String store = "";

        for(int i=0; i<cnt; i++){
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

    public static List<Object> recallCheckpoint() {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
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
                    tasks.add(new Todo(sen[2]));
                    if(sen[1].equals("X"))
                        tasks.get(cnt).markAsDone();
                    else if(sen[1].equals(" "))
                        tasks.get(cnt).markAsUnDone();
                    cnt += 1;
                    break;
                case "D":
                    tasks.add(new Deadline(sen[2], sen[3]));
                    if(sen[1].equals("X"))
                        tasks.get(cnt).markAsDone();
                    else if(sen[1].equals(" "))
                        tasks.get(cnt).markAsUnDone();
                    cnt += 1;
                    break;
                case "E":
                    tasks.add(new Event(sen[2], sen[3], sen[4]));
                    if(sen[1].equals("X"))
                        tasks.get(cnt).markAsDone();
                    else if(sen[1].equals(" "))
                        tasks.get(cnt).markAsUnDone();
                    cnt += 1;
                    break;
                }
            }

            file_reader.close();
        } catch (IOException e) {
            e.getStackTrace();
        }

        return Arrays.asList(tasks, cnt);
    }
}
