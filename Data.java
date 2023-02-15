import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import tasks.*;

public class Data {
    public static void createFile() {
        try {
            if (new File("data").mkdirs()) {
                System.out.println("Good job making that directory, you'll need it.");
                File data = new File("data/data.txt");
                data.createNewFile();
            } else {
                System.out.println("Mate that directory already exists.");
            }
        } catch (IOExpection e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String textToAdd) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void loadData() throws IOException {
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] str = myReader.nextLine().split("|", 3);
                switch (str[0]) {
                case "T":
                    Todo obj = new Todo(str[2]);
                    if (str[1].equals("[X]")) {
                        obj.setIsDone(true);
                    } else {
                        obj.setIsDone(false);
                    }
                case "D":
                    Deadline obj = new Deadline(str[2]);
                    if (str[1].equals("[X]")) {
                        obj.setIsDone(true);
                    } else {
                        obj.setIsDone(false);
                    }
                case "E":
                    Event obj = new Deadline(str[2]);
                    if (str[1].equals("[X]")) {
                        obj.setIsDone(true);
                    } else {
                        obj.setIsDone(false);
                    }
                }
            }
        } catch (FileNotFoundExecption e) {
            System.out.println("No such file there eh!");
            createFile();
        } finally {
            fw.flush();
            fw.close();
        }
    }
}
