package duke.Storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Loads and saves TaskList from or to a file.
 */
public class Storage {
    private final Ui ui = new Ui();
    private static final String saveLocation = System.getProperty("user.dir")+"/save.json";
    final TypeToken<ArrayList<Task>> listOfTasks = new TypeToken<ArrayList<Task>>(){};
    RuntimeTypeAdapterFactory<Task> adapter = RuntimeTypeAdapterFactory.of(
            Task.class,"type", true)
            .registerSubtype(ToDo.class, "ToDo")
            .registerSubtype(Deadline.class, "Deadline")
            .registerSubtype(Event.class, "Event");
    GsonBuilder builder = new GsonBuilder().setPrettyPrinting().registerTypeAdapterFactory(adapter);
    Gson gson = builder.create();

    /**
     * Serializes the TaskList in JSON format and saves it to a file.
     * @param taskList the list of tasks.
     * @exception IOException If there is an error saving the object to file.
     */
    public void saveToFile(ArrayList<Task> taskList){
        File saveFile = new File(saveLocation);
        String gsonData = gson.toJson(taskList);
        if (!saveFile.exists()){
            try {
                saveFile.createNewFile();
            }
            catch (IOException e){
                ui.showException("IOException");
            }

        }
        try{
            FileWriter taskWriter;
            taskWriter = new FileWriter(saveFile.getAbsoluteFile(),false);
            taskWriter.write(gsonData);
            taskWriter.close();
        }catch (IOException e){
            System.out.println("IOException");
        }
    }

    /**
     * Deserializes the list of tasks from JSON to ArrayList and returns it.
     * @return ArrayList This returns the saved list of tasks.
     */
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
