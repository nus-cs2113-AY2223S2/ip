import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Storage {
    public final static String FILEPATH = "./duke.txt";

    public Storage(){
        try {
            File file = new File(FILEPATH);
            if (file.createNewFile()) {
                UI.printFileCreatedComment(file.getName());
            }
        } catch(IOException e){
            UI.printFileNotCreatedComment();
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<String> scanData() throws IOException {
        File file = new File(FILEPATH);
        Scanner scanner = new Scanner(file);
        ArrayList<String> existingTasks = new ArrayList<>();
        while(scanner.hasNext()){
            existingTasks.add(scanner.nextLine());
        }
        return existingTasks;
    }


    public void writeToFile(String textAdded){
        try {
            File file = new File(FILEPATH);
            FileWriter fw = new FileWriter(file);
            fw.write(textAdded);
            fw.close();
        } catch (IOException e){
            UI.printSaveErrorComment();
            System.out.println(e.getMessage());
        }
    }

    public void writeDeadlineToFile(Deadline deadline){
        try {
            File file = new File(FILEPATH);
            FileWriter fw = new FileWriter(file, true);
            String textAdded = "[D]";
            if(deadline.getIsDone()){
                textAdded += "[O]";
            }else{
                textAdded += "[ ]";
            }
            textAdded = textAdded + deadline.getContents() + " by " + deadline.getBy();
            fw.write(textAdded);
            fw.close();
        } catch (IOException e){
            UI.printSaveErrorComment();
            System.out.println(e.getMessage());
        }
    }

    public void writeEventToFile(Event event){
        try {
            File file = new File(FILEPATH);
            FileWriter fw = new FileWriter(file, true);
            String textAdded = "[D]";
            if(event.getIsDone()){
                textAdded += "[O]";
            }else{
                textAdded += "[ ]";
            }
            textAdded = textAdded + event.getContents() + " from " + event.getFrom() + " to " + event.getTo();
            fw.write(textAdded);
            fw.close();
        } catch (IOException e){
            UI.printSaveErrorComment();
            System.out.println(e.getMessage());
        }
    }

}
