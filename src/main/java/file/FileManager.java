package file;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.io.FileWriter;
import java.io.IOException;
import tasklist.Tasklist;

public class FileManager {
    
    public static File f = new File("duke.txt");
    
    public static void readIntoList()throws FileNotFoundException, IOException {
        if (!f.exists()){
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String[] currentLineArray = s.nextLine().split("/");
            String taskType = currentLineArray[0];
            String markStatus = currentLineArray[1];
            String eventDesc = currentLineArray [2];
            switch(taskType){
            case "todo":
                Tasklist.tasksList.add(new Todo(eventDesc));
                break;
            case "deadline":
                String deadlineDate = currentLineArray[3];
                Tasklist.tasksList.add(new Deadline(eventDesc, deadlineDate));
                break;
            case "event":
                String startTime = currentLineArray[3];
                String endTime = currentLineArray[4];
                Tasklist.tasksList.add(new Event(eventDesc, startTime, endTime));
                break;
            }
            if (markStatus.equals("1")){
                Task currentTask = Tasklist.tasksList.get(Tasklist.tasksList.size()-1); 
                currentTask.markAsDone();
            }
        }
        s.close();
    }

    public static void saveFile()throws IOException{
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < Tasklist.tasksList.size(); i++){
            Task currentTask = Tasklist.tasksList.get(i);
            fw.write(currentTask.encode() + System.lineSeparator());
        }
        fw.close();
    }

    public static void initialise(){
        try{
            readIntoList();
        } catch (FileNotFoundException e){
            System.out.println("File Not Found");
        } catch (IOException e){
            System.out.println("IO Error");
        }
    }
}
