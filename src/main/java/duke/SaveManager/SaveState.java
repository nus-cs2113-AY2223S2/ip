package duke.SaveManager;

import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import duke.command.TaskManager;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaveState{
    private static final String saveLocation = System.getProperty("user.dir")+"/save.json";
    GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
    Gson gson = builder.create();
    public void saveToFile(Task[] taskList){
        File saveFile = new File(saveLocation);
        String gsonData = gson.toJson(taskList);
        System.out.println(saveLocation);
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
