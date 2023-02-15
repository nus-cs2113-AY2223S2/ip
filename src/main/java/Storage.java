import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {

    public static File createFile(String filepath) {
        try {
            File f = new File(filepath);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
            }
            return f;
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return null;
    }

    public static void readFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String[] fullLine = s.nextLine().split(" / ");
            String taskType = fullLine[0];
            String isTaskDone = fullLine[1];
            String description = fullLine[2];
            switch (taskType) {
            case "T":
                Psyduck.addToDo(description);
                Psyduck.getNewestTask().setDone(Boolean.parseBoolean(isTaskDone));
                break;
            case "D":
                String by = fullLine[3];
                Psyduck.addDeadline(description, by);
                Psyduck.getNewestTask().setDone(Boolean.parseBoolean(isTaskDone));
                break;
            case "E":
                String from = fullLine[3];
                String to = fullLine[4];
                Psyduck.addEvent(description, from, to);
                Psyduck.getNewestTask().setDone(Boolean.parseBoolean(isTaskDone));
                break;
            default:
                //invalid task type/wrong format ignored
            }

        }
    }
    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(createFile(filePath));
        for (int i = 1; i < Psyduck.getTaskCount() + 1; i++) {
            if (Psyduck.getTask(i).getType().equals("T")) {
                fw.write( Psyduck.getTask(i).getType() + " / "
                        + Psyduck.getTask(i).isDone() + " / "
                        + Psyduck.getTask(i).getDescription() + " / "
                        + System.lineSeparator());
            } else if (Psyduck.getTask(i).getType().equals("D")) {
                Deadline temp = (Deadline) Psyduck.getTask(i);
                fw.write( temp.getType() + " / "
                        + temp.isDone() + " / "
                        + temp.getDescription() + " / "
                        + temp.getBy() + " / "
                        + System.lineSeparator());
            } else if (Psyduck.getTask(i).getType().equals("E")) {
                Event temp = (Event) Psyduck.getTask(i);
                fw.write( temp.getType() + " / "
                        + temp.isDone() + " / "
                        + temp.getDescription() + " / "
                        + temp.getFrom() + " / "
                        + temp.getTo() + " / "
                        + System.lineSeparator());
            } else {
                System.out.println("An error has occurred, file writing unsuccessful");
                fw.close();
                return;
            }

        }
        fw.close();
    }

}
