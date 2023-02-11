import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import duke.Task;

public class Save {
    public Save(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    public static void saveFile(Task[] textList, int textListCount) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (int i = 0; i < textListCount; ++i) {
            String listLine = textList[i].toString();
            if (listLine.contains("[T]")) {
                Todo todo = (Todo) textList[i];
                if (listLine.contains("[X]")) {
                    fw.write("T" + " | " + "1" + " | " + todo.description + System.lineSeparator());
                } else {
                    fw.write("T" + " | " + "0" + " | " + todo.description + System.lineSeparator());
                }
            } else if (listLine.contains("[D]")) {
                Deadline deadline = (Deadline) textList[i];
                if (listLine.contains("[X]")) {
                    fw.write("D" + " | " + "1" + " | " + deadline.description + "| " + deadline.by + System.lineSeparator());
                } else {
                    fw.write("D" + " | " + "0" + " | " + deadline.description + "| " + deadline.by + System.lineSeparator());
                }
            } else if (listLine.contains("[E]")) {
                Event event = (Event) textList[i];
                if (listLine.contains("[X]")) {
                    fw.write("E" + " | " + "1" + " | " + event.description + "| " + event.from + "- " + event.to + System.lineSeparator());
                } else {
                    fw.write("E" + " | " + "0" + " | " + event.description + "| " + event.from + "- " + event.to + System.lineSeparator());
                }
            }
        }
        fw.close();
    }
    public static void readFile(Task[] textList, int textListCount) throws FileNotFoundException {
        File f = new File("data/duke.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArray = line.split(" ");
            if (lineArray[0].equals("T")) {
                if (lineArray[1].equals("1")) {
                    textList[textListCount] = new Todo (lineArray[3]);
                    textList[textListCount].markAsDone();
                    textListCount++;
                } else {
                    textList[textListCount] = new Todo (lineArray[3]);
                    textListCount++;
                }
            } else if (lineArray[0].equals("D")) {
                if (lineArray[1].equals("1")) {
                    textList[textListCount] = new Deadline (lineArray[3], lineArray[5]);
                    textList[textListCount].markAsDone();
                    textListCount++;
                } else {
                    textList[textListCount] = new Deadline (lineArray[3], lineArray[5]);
                    textListCount++;
                }
            } else if (lineArray[0].equals("E")) {
                if (lineArray[1].equals("1")) {
                    textList[textListCount] = new Event (lineArray[3], lineArray[5], lineArray[7]);
                    textList[textListCount].markAsDone();
                    textListCount++;
                } else {
                    textList[textListCount] = new Event (lineArray[3], lineArray[5], lineArray[7]);
                    textListCount++;
                }
            }
        }
    }
}
