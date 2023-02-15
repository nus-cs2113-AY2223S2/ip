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

    public static void saveData(String textToAdd) throws IOException {
        try {
            File obj = new File("data.txt");
            if (obj.createNewFile()) {
                System.out.println("File created: " + obj.getName());
            } else {
                System.out.println("File already exists mate.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

        try {
            FileWriter myWriter = new FileWriter("data.txt");
            for (Task task : ThomasShelby.tasks) {
                if (task.getType() == "T") {
                    myWriter.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.description + '\n');
                } else if (task.getType() == "D") {
                    Deadline obj = (Deadline) task;
                    myWriter.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.description + "|" + obj.by + '\n');
                } else if (task.getType() == "E") {
                    Event obj = (Event) task;
                    myWriter.write(task.getType() + "|" + task.getStatusIcon() + "|" + task.description + "|" + obj.start + "|" + obj.end + '\n');
                }
            }
            myWriter.close();
            System.out.println("Successful.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void loadData() throws IOException {
        try {
            File myData = new File("data.txt");
            Scanner sc = new Scanner(myData);
            while (sc.hasNextLine()) {
                String[] str = sc.nextLine().split("|", 3);
                switch (str[0]) {
                case "T":
                    Todo obj = new Todo(str[2]);
                    if (str[1].equals("[X]")) {
                        obj.setIsDone(true);
                    } else {
                        obj.setIsDone(false);
                    }
                    ThomasShelby.tasks.add(obj);
                case "D":
                    Deadline obj = new Deadline(str[2]);
                    if (str[1].equals("[X]")) {
                        obj.setIsDone(true);
                    } else {
                        obj.setIsDone(false);
                    }
                    ThomasShelby.tasks.add(obj);
                case "E":
                    Event obj = new Deadline(str[2]);
                    if (str[1].equals("[X]")) {
                        obj.setIsDone(true);
                    } else {
                        obj.setIsDone(false);
                    }
                    ThomasShelby.tasks.add(obj);
                }
            }
            sc.close();
        } catch (FileNotFoundExecption e) {
            System.out.println("No such file there eh!");
            createFile();
        } finally {
            fw.flush();
            fw.close();
        }
    }
}
