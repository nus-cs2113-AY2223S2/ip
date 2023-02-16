package duke.SaveManager;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import duke.command.TaskManager;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class SaveState{
    private static final String saveLocation = System.getProperty("user.dir")+"/save.json";
    final TypeToken<ArrayList<Task>> listOfTasks = new TypeToken<ArrayList<Task>>(){};
    RuntimeTypeAdapterFactory<Task> adapter = RuntimeTypeAdapterFactory.of(Task.class,"type")
            .registerSubtype(ToDo.class, "ToDo")
            .registerSubtype(Deadline.class, "Deadline")
            .registerSubtype(Event.class, "Event");
    GsonBuilder builder = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter);
    Gson gson = builder.create();
    public void saveToFile(ArrayList<Task> taskList){
        File saveFile = new File(saveLocation);
        String gsonData = gson.toJson(taskList);
        if (!saveFile.exists()){
            try {
                saveFile.createNewFile();
            }
            catch (IOException e){
                System.out.println("dead");
            }

        }
        try{
            FileWriter taskWriter;
            taskWriter = new FileWriter(saveFile.getAbsoluteFile(),false);
            taskWriter.write(gsonData);
            taskWriter.close();
            System.out.println("Status saved");
        }catch (IOException e){
            System.out.println("more dead");
        }
    }
}
