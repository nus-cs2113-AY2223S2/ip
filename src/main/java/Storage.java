import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Storage {
    private String filePath;
    private ArrayList<String> fileContents = new ArrayList<>();
    private ArrayList<String> commandsFromUser = new ArrayList<>();
    private boolean canPrintFeedback = false;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
    public void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(String s:commandsFromUser){
            fw.write(s);
            fw.write('\n');

        }
        fw.close();
    }

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
