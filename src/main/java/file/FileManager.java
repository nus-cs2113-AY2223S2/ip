package file;
import java.io.File;
import java.util.Scanner;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import tasklist.Tasklist;

/*
 * Handles the reading and writing to and from the duke.txt file
 * File f represents the duke.txt file
 */
public class FileManager {
    
    public static File f = new File("duke.txt");
    
    /*
     * Reads the duke.txt file into the Tasklist taskslist
     * Creates a new duke.txt if the file does not exist
     * Scanner s represents the scanner used in reading the file
     * 
     * @throws IOException if there is an input/output error
     * @see IOException
     */
    public static void readIntoList()throws IOException {
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
                LocalDate deadlineDate = Tasklist.convertStringToDate(currentLineArray[3]);
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

    /*
     * Write the encoded tasks in taskslist into the file
     * FileWriter fw represents the FileWriter used in writing the file
     * 
     * @throws IOException if there is an input/output error
     * @see IOException
     */
    public static void saveFile()throws IOException{
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < Tasklist.tasksList.size(); i++){
            Task currentTask = Tasklist.tasksList.get(i);
            fw.write(currentTask.encode() + System.lineSeparator());
        }
        fw.close();
    }

    /*
     * Initailises readIntoList() method when duke is started
     * Catches the IOException throw by readIntoList()
     */
    public static void initialise(){
        try{
            readIntoList();
        } catch (IOException e){
            System.out.println("IO Error");
        }
    }
}
