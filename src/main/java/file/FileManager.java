package file;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import duke.Duke;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static File f = new File("src/main/java/file/duke.txt");

    public static void readIntoList()throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while(s.hasNext()){
            String[] currentLineArray = s.nextLine().split("/");
            String taskType = currentLineArray[0];
            String markStatus = currentLineArray[1];
            String eventDesc = currentLineArray [2];
            switch(taskType){
            case "todo":
                Duke.tasksList.add(new Todo(eventDesc));
                break;
            case "deadline":
                String deadlineDate = currentLineArray[3];
                Duke.tasksList.add(new Deadline(eventDesc, deadlineDate));
                break;
            case "event":
                String startTime = currentLineArray[3];
                String endTime = currentLineArray[4];
                Duke.tasksList.add(new Event(eventDesc, startTime, endTime));
                break;
            }
            if (markStatus.equals("1")){
                Task currentTask = Duke.tasksList.get(Duke.tasksList.size()-1); 
                currentTask.markAsDone();
            }
        }
        s.close();
    }

    public static void saveFile()throws IOException{
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < Duke.tasksList.size(); i++){
            Task currentTask = Duke.tasksList.get(i);
            fw.write(currentTask.encode() + System.lineSeparator());
        }
        fw.close();
    }
}
