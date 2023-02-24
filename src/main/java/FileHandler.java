import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class FileHandler {
    public String filePath;
    public File dukeFile;
    FileHandler(String filePath){
        this.filePath = filePath;
        this.dukeFile = new File(filePath); //just creates a new file object, not a new file
    }
    public void createFile() throws IOException{
//        if(this.dukeFile.exists()==false) {
////            FileWriter fw = new FileWriter(this.filePath); //handle the exception here
////            fw.close();
//            dukeFile.createNewFile();
//        }
//        if(dukeFile.exists()==false) {
//            dukeFile.mkdir();
//        }
        dukeFile.createNewFile();
    }
//    public void makeNewDirectory() throws IOException {
//        if(!dukeFile.exists()) {
//            dukeFile.mkdir();
//        }
//    }
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

    public void populateFile(ArrayList<Task> arrayList) throws IOException {
        clearFile();
        for(Task ele: arrayList) {
            addToFile(ele.enCode() + System.lineSeparator());
        }
    }

    public ArrayList<Task> readFile() throws IOException{
        ArrayList<Task> arrayList = new ArrayList<Task>();
        if(dukeFile.createNewFile()==true) {
            System.out.println("Returned empty");
            return arrayList;
        }
        Scanner s = new Scanner(dukeFile);
        while(s.hasNext()) {
            String holder = s.nextLine();
            String[] compartments = holder.split(" # ");
            if( compartments[0].equals("T")) {
                Todos temp = new Todos();
                if(compartments[1].equals("false")) {
                    temp.unMarkTask();
                } else {
                    temp.markTask();
                }
                temp.setDescription(compartments[2]);
                arrayList.add(temp);
            } else if ( compartments[0].equals("D")) {
                Deadlines temp = new Deadlines();
                if(compartments[1].equals("false")) {
                    temp.unMarkTask();
                } else {
                    temp.markTask();
                }
                temp.setDescription(compartments[2]);
                temp.setDeadline(compartments[3]);
                arrayList.add(temp);
            } else if( compartments[0].equals("E")) {
                Events temp = new Events();
                if(compartments[1].equals("false")) {
                    temp.unMarkTask();
                } else {
                    temp.markTask();
                }
                temp.setDescription(compartments[2]);
                temp.setFrom(compartments[3]);
                temp.setTo(compartments[4]);
                arrayList.add(temp);
            }
        }
        return arrayList;
    }

}
