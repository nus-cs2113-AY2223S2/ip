import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

/**
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private ArrayList<String> fileContents = new ArrayList<>();
    private ArrayList<String> commandsFromUser = new ArrayList<>();
    private boolean canPrintFeedback = false;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read file specified by <code>filePath</code>, if it does not exist, create a new file
     * named "duke.txt". If the file is not empty, read the file contents.
     * @return File contents in ArrayList <String> type.
     * @throws IOException when file does not exist.
     */
    public ArrayList<String> loadFileContents() throws IOException {
        File F =  new File(filePath);
        if(!F.exists()){
            F = new File("duke.txt");
            this.filePath = F.getPath().toString();
        }
        Scanner s = new Scanner(F);
        while(s.hasNext()){
            fileContents.add(s.nextLine());
        }
        return fileContents;
    }

    /**
     * iterate an ArrayList with valid user inputs from CLI and write each line into the file.
     * Write to file when commands are COMMAND_MARK or COMMAND_UNMARK.
     * @throws IOException when program is unable to write to file.
     */
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(String s:commandsFromUser){
            fw.write(s);
            fw.write('\n');

        }
        fw.close();
    }

    /**
     * Appends the most recent valid user inputs from CLI to file.
     * Append to file when commands are COMMAND_EVENT, COMMAND_DEADLINE, COMMAND_TODO.
     * @param userInput valid commands from CLI.
     * @throws IOException when program is unable to append to file.
     */
    public void appendToFile(String userInput) throws IOException{
        FileWriter fw = new FileWriter(this.filePath,true);
        fw.write(userInput);
        fw.write('\n');
        fw.close();
    }
    public void addGetCommandsFromUser(String userInput){
        commandsFromUser.add(userInput);
    }


}
