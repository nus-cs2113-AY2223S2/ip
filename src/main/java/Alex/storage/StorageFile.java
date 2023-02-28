package Alex.storage;

import Alex.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class StorageFile {
    public static void readData(TaskList taskList) throws FileNotFoundException {
        String dir = System.getProperty("user.dir");
        Path filePath = Paths.get(dir, "data", "duke.txt");
        File f = new File(filePath.toString());
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String[] info = s.nextLine().trim().split(",");
            /*for(String part :info) {
                System.out.println(part);
            }*/
            if(info[0].equals("T"))
            {
                Task t = new Todo(info[2] , "T");
                if(info[1].equals("1")) {
                    t.markAsDone();
                }
                taskList.setTask(t);
            }
            else if(info[0].equals("D"))
            {
                Task t = new Deadline(info[2], "D", info[3]);
                if(info[1].equals("1")) {
                    t.markAsDone();
                }
                taskList.setTask(t);
            }
            else if(info[0].equals("E"))
            {
                Task t = new Event(info[2], "E",info[3] ,info[4]);
                if(info[1].equals("1")) {
                    t.markAsDone();
                }
                taskList.setTask(t);
            }

        }
    }


    public static void saveData (TaskList tasklist) throws IOException {
        String toSave = "";
        String dir = System.getProperty("user.dir");
        Path filePath = Paths.get(dir, "data", "duke.txt");
        File file = new File(filePath.toString());
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file);
        for(Task t : tasklist.getAllTasks()) {
            if(t.getType().equals("T")) {
                Todo td = (Todo)t;
                int done = td.getDone() ? 1 : 0;
                toSave += "T" + "," + done  + "," + td.getDescription();
            }
            else if (t.getType().equals("D")) {
                Deadline d = (Deadline)t;
                int done = d.getDone() ? 1 : 0;
                toSave += "D" + "," + done + "," + d.getDescription() + "," + d.getBy();
            }
            else if (t.getType().equals("E")) {
                Event e = (Event)t;
                int done = e.getDone() ? 1 : 0;
                toSave += "E" + ","+ done + "," + e.getDescription() + "," + e.getFrom() + "," + e.getTo();
            }
            toSave += System.lineSeparator();
        }
        fw.write(toSave);
        fw.close();
    }
}
