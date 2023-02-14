package duke.SaveManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.SaveManager.RuntimeTypeAdapterFactory;
import duke.task.ToDo;

public class LoadState {

    private static final String saveLocation = System.getProperty("user.dir")+"/save.json";

    RuntimeTypeAdapterFactory<Task> adapter = RuntimeTypeAdapterFactory.of(Task.class,"type")
            .registerSubtype(ToDo.class)
            .registerSubtype(Deadline.class)
            .registerSubtype(Event.class);

    GsonBuilder builder = new GsonBuilder().registerTypeAdapterFactory(adapter);
    Gson gson = builder.create();
    public void loadTasks(){
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