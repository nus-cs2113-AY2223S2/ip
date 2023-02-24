import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    private static final String saveFilePath = "./data/duke.txt";

    public void writeToFile(String filePath, String content) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(content);
        fw.close();
    }

    public void save(TaskList itemList) throws IOException {
        // create directory and file if they dont exist
        File fileObj = new File(saveFilePath);
        File dirObj = fileObj.getParentFile();
        dirObj.mkdirs();
        String saveString = "";
        for (Todo item: itemList.asList()) {
            saveString += item.getSaveRepresentation() + "\n";
        }
        writeToFile(saveFilePath, saveString);
    }



    public void load(TaskList itemList) throws FileNotFoundException {
        File loadFile = new File(saveFilePath);
        Scanner fileScanner = new Scanner(loadFile);
        while(fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] lineParts = line.split(" /// ");
            String itemType = lineParts[0];
            // load each item one by one
            boolean isMarked = lineParts[1] == "1";
            Todo newItem;
            switch (itemType) {
            case "T":
                newItem = new Todo(lineParts[2]);
                newItem.setDone(isMarked);
                itemList.addItem(newItem);
                break;
            case "D":
                newItem = new Deadline(lineParts[2], lineParts[3]);
                newItem.setDone(isMarked);
                itemList.addItem(newItem);
                break;
            case "E":
                newItem = new Event(lineParts[2], lineParts[3], lineParts[4]);
                newItem.setDone(isMarked);
                itemList.addItem(newItem);
                break;
            default:
                // TODO add exception
                break;
            }
        }
        fileScanner.close();
    }
}
