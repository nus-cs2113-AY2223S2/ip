import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * converts tasks between the entry format (to be saved in the text file) and Object format (to be added to taskArray)
 */
public class Storage {
    private static File file;

    public Storage(){
        file =new File("C:\\Users\\leong\\Desktop\\Comp_eng\\Y2S2\\CS2113\\Duke\\ip\\src\\data.txt");
    }

    /**
     * example of format for event entries in the file : taskNumber.taskType.taskIsDone.taskName.from.to
     * @throws FileNotFoundException
     */
    public static void loadFileContentsToTaskArray() throws FileNotFoundException {
        Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            String[] taskDetails = (scanner.nextLine()).split("\\.");
            if(taskDetails[1].equals("T")){
                ToDo todo= new ToDo(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[1]);
                todo.isDone = taskDetails[2].equals("[X]");
                todo.updateTaskDescription();
                Tasklist.addToTaskArrayList(todo);
            }
            else if(taskDetails[1].equals("E")){
                Event event = new Event(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[4],taskDetails[5],taskDetails[1]);
                event.isDone = taskDetails[2].equals("[X]");
                event.updateTaskDescription();
                Tasklist.addToTaskArrayList(event);
            }
            else if(taskDetails[1].equals("D")){
                Deadline deadline = new Deadline(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[4],taskDetails[1]);
                deadline.isDone = taskDetails[2].equals("[X]");
                deadline.updateTaskDescription();
                Tasklist.addToTaskArrayList(deadline);
            }
        }
    }

    public static void makeNewFile(){
        try{
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * this function converts the tasks in the taskArray into entry format (Eg:taskNumber.taskType.taskIsDone.taskName)
     * and writes it to the text file
     */
    public static void save(){
        try{
            if(Tasklist.lastIndex>0){
                FileWriter fileWriter = new FileWriter(file);
                for(int index=0;index<Tasklist.lastIndex;index++){
                    Tasklist task = Tasklist.get(index);
                    fileWriter.append(task.createEntry());
                }
                fileWriter.close();
            }
            if(Tasklist.lastIndex==0){
                FileWriter fw = new FileWriter(file);
                fw.close();
            }
            UserInterface.savedMessage();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
