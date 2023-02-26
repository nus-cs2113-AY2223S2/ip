package tusky.repository;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tusky.tasks.Task;
import tusky.tasks.Event;
import tusky.tasks.Deadline;
import tusky.tasks.ToDo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
public class FileManager {
    private static final String DIR_NAME = "data";
    private static final String FILE_NAME = "tusky.txt";

    private static Gson gson = new Gson();

    public static String getPath(){
        return DIR_NAME + "/" + FILE_NAME;
    }
    public static ArrayList<Task> readFile () throws FileNotFoundException, NoSuchFileException {
        File f = new File(getPath()); // create a File for the given file path
        try {
            Scanner s = new Scanner(f);
//            Reader br = Files.newBufferedReader(Paths.get(getPath()));
//            Task[] tasks = gson.fromJson(br, Task[].class);
            ArrayList<Task> tasks = new ArrayList<>();
            while(s.hasNext()){
                tasks.add(parseTask(s.nextLine().trim()));
            }
            s.close();
            return tasks;
        } catch (IOException e){
            System.out.println("Something went wrong while reading from the file");
            e.printStackTrace();
        }
        return null;
    }

    public static String taskAdapter(Task task){
        switch(task.getTaskType()){

        case EVENT:
            return String.format("%s|%s|%s|%s|%s\n", task.getTaskSymbol(), task.isDone(), task.getDescription(), ((Event) task).getFrom(), ((Event) task).getTo());
        case DEADLINE:
            return String.format("%s|%s|%s|%s\n", task.getTaskSymbol(), task.isDone(), task.getDescription(), ((Deadline)task).getBy());
        default:
            return String.format("%s|%s|%s\n", task.getTaskSymbol(), task.isDone(), task.getDescription());
        }

    }

    public static Task parseTask(String s){
        String[] strings = s.split("\\|");
        try{
            switch(strings[0]){
            case "T":
                return new ToDo(strings[1], strings[2]);
            case "E":
                return new Event(strings[1], strings[2], strings[3], strings[4]);
            case "D":
                return new Deadline(strings[1], strings[2], strings[3]);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public static void writeFile(ArrayList<Task> tasks) {


        File directory = new File(DIR_NAME);
        if (! directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(getPath());
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(Task task: tasks){
                if(task == null){
                    break;
                }
                bw.write(taskAdapter(task));
            }
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
