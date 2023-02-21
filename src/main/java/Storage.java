import duke.exception.IllegalCommandException;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private static String storageFilePath;
    /*
     * Constructor to initialise file path used for storage.
     */
    public Storage(String storageFilePath){
        this.storageFilePath = storageFilePath;
    }
    /*
     * Loads data into tasks from storageFilePath
     */
    public static void load() throws IOException, IllegalCommandException {
        File folder = new File("data");
        if(!(folder.exists()&&folder.isDirectory())){
            new File("data").mkdirs();
        }
        File f = new File(storageFilePath);
        if(!f.exists()){
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String nextLine =  s.nextLine();
            String[] saveLine = nextLine.split(" ");
            String cmd = "";
            for(int i=0; i<saveLine.length-1; i++){
                cmd += " ";
                cmd += saveLine[i];
            }
            cmd = cmd.trim();
            String done = saveLine[saveLine.length-1].trim();
            try{
                TaskList.silentlyAddTask(cmd);
            }catch(IllegalCommandException e){
                throw new IllegalCommandException("parsing error");
            }
            if(done.equals("1")){
                TaskList.setLastTaskMarkedOrUnmarked(true);
            }
        }
    }
    /*
     * Saves all tasks into storageFilePath
     */
    public static void save() throws IOException {
        File f = new File(storageFilePath);
        if(f.exists()){
            f.delete();
        }
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        ArrayList<String> saveStrings = TaskList.getSaveStrings();
        for(String s: saveStrings){
            fw.write(s);
        }
        fw.close();
    }
    /*
     * Greets the user and loads saved data
     */
    public static void initialise(){
        Ui.greet();
        try{
            load();
        }catch(IOException e){
            System.out.println(Ui.LOADING_SYSTEM_ERROR_STRING);
        }catch(IllegalCommandException e){
            System.out.println(Ui.LOADING_PROGRAM_ERROR_STRING);
        }
    }
}
