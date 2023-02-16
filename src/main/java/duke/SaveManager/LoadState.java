package duke.SaveManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.SaveManager.RuntimeTypeAdapterFactory;
import duke.task.ToDo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class LoadState {

    private static final String saveLocation = System.getProperty("user.dir")+"/save.json";

    final TypeToken<ArrayList<Task>> listOfTasks = new TypeToken<ArrayList<Task>>(){};
    RuntimeTypeAdapterFactory<Task> adapter = RuntimeTypeAdapterFactory.of(Task.class,"type", true)
            .registerSubtype(ToDo.class, "ToDo")
            .registerSubtype(Deadline.class, "Deadline")
            .registerSubtype(Event.class, "Event");

    GsonBuilder builder = new GsonBuilder().registerTypeAdapterFactory(adapter);
    Gson gson = builder.create();
    public ArrayList<Task> loadTasks(){
        File saveFile = new File(saveLocation);
        ArrayList<Task> savedList = new ArrayList<>();
        if(!saveFile.exists()){
            return savedList;
        }
        InputStreamReader fileReader;
        try{
            fileReader = new InputStreamReader(new FileInputStream(saveFile), StandardCharsets.UTF_8);
            JsonReader gsonInterpreter = new JsonReader(fileReader);
            savedList = gson.fromJson(fileReader, listOfTasks);
        }catch (Exception e){
            System.out.println(e);
        }
        return savedList;
    }
}

/*
    public void readFromFile(){

        File saveFile = new File(saveLocation);
        if(!saveFile.exists()){
            return;
        }
        InputStreamReader fileReader;
        try{
            fileReader = new InputStreamReader(new FileInputStream(saveFile), StandardCharsets.UTF_8);
            System.out.println("entered");
            JsonReader gsonInterpreter = new JsonReader(fileReader);
            System.out.println("Entered too");
            Task[] savedList = gson.fromJson(fileReader, Task[].class);
        }catch (Exception e){
            System.out.println("weird error");
        }
    }

 */