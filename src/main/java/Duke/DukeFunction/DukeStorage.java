package Duke.DukeFunction;

import Duke.DukeCommandLine.DukeTaskInputException;
import Duke.DukeTask.DukeDeadline;
import Duke.DukeTask.DukeEvent;
import Duke.DukeTask.DukeTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeStorage {
    private static final String FILE_PATH = "data/list.txt";
    public void saveTask(DukeList tasks) throws DukeTaskInputException {
        File file = new File(FILE_PATH);
        try {
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            tasks.storeTask(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeTaskInputException(e.getMessage());
        }
    }
    public void loadTask(DukeList tasks) throws DukeTaskInputException {
        File file = new File(FILE_PATH);
        try {
            if(!file.exists()) {
                throw new DukeTaskInputException("No file to load!");
            }
            Scanner fileReader = new Scanner(file);
            String TaskLine;
            while(fileReader.hasNextLine()) {
                TaskLine = fileReader.nextLine();
                String[] taskInfo = TaskLine.split(" \\| ");
                taskInfo[0] = taskInfo[0].trim();
                taskInfo[1] = taskInfo[1].trim();
                taskInfo[2] = taskInfo[2].trim();
                switch (taskInfo[0]) {
                case "T":
                    DukeTask task = new DukeTask(taskInfo[2]);
                    if(taskInfo[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.addTask(task);
                    break;
                case "D":
                    taskInfo[3] = taskInfo[3].trim();
                    DukeTask deadline = new DukeDeadline(taskInfo[2], taskInfo[3]);
                    if(taskInfo[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    tasks.addTask(deadline);
                    break;
                case "E":
                    taskInfo[3] = taskInfo[3].trim();
                    taskInfo[4] = taskInfo[4].trim();
                    DukeTask event = new DukeEvent(taskInfo[2], taskInfo[3], taskInfo[4]);
                    if(taskInfo[1].equals("1")) {
                        event.markAsDone();
                    }
                    tasks.addTask(event);
                    break;
                default:
                    throw new DukeTaskInputException("Sorry, I can't read the file, the format is wrong");
                }
            }
        } catch (IOException e) {
            throw new DukeTaskInputException("[IOException] Sorry, I can't load the file, " + e.getMessage());
        }
    }

}
