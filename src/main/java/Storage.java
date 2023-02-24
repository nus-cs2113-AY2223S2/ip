import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    public static TaskList readFileContents(String filePath) throws FileNotFoundException {
        TaskList tempTasks = new TaskList();

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String newLine = s.nextLine(); 
            String[] newInput = newLine.split(":");
            String taskName = newInput[2];
            String taskType = newInput[0];

            switch (taskType) {
            case "T":
                tempTasks.addTask(new Todo (taskName));
                break;
            case "D":
                String deadlineDay = newInput[3];
                tempTasks.addTask(new Deadline(taskName, deadlineDay));
                break;
            case "E":
                String eventFrom = newInput[3];
                String eventTo = newInput[4];
                tempTasks.addTask(new Event(taskName, eventFrom, eventTo));
                break;
            default:
                Greeting.printHelp();
            }
            if (newInput[1].equals("1")) {
                tempTasks.MarkStatusAsDone(tempTasks.getSize() - 1);
            }
        }
        return tempTasks;
    }

    public static void updateFile(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            String taskStatus = currentTask.getStatusIcon();
            String taskType = currentTask.getTaskIcon();
            String taskName = currentTask.getTask();
            String taskStatusBinary = (taskStatus == "X") ? "1" : "0";

            switch (taskType) {
            case "T":
                try {
                    writeToFile("T:"+taskStatusBinary+":"+taskName);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "D":
                String deadlineDay = currentTask.getBy();
                try {
                    writeToFile("D:"+taskStatusBinary+":"+taskName+":"+deadlineDay);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            case "E":
                String eventFrom = currentTask.getFrom();
                String eventTo = currentTask.getTo();
                try {
                    writeToFile("E:"+taskStatusBinary+":"+taskName+":"+eventTo+":"+eventFrom);
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            default:
                Greeting.printHelp();
            }
        }
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("duke.txt", true);
        fw.write(textToAdd+System.lineSeparator());
        fw.close();
    }

    public static void clearFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        fw.write("");
        fw.close();
    }
}
