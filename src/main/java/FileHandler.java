import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public String filePath;
    public File dukeFile;
    FileHandler(String filePath){
        this.filePath = filePath;
        this.dukeFile = new File(filePath); //just creates a new file object, not a new file
    }

    public void createFile() throws IOException{
        if(this.dukeFile.exists()==false) {
            FileWriter fw = new FileWriter(this.filePath); //handle the exception here
            fw.close();
        }
    }
    public void addToFile(String toAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(toAdd);
        fw.close();
    }
    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }

}
