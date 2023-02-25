package keqing;

import keqing.tasks.Deadline;
import keqing.tasks.Event;
import keqing.tasks.Task;
import keqing.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class KeqingStorage {
    private static final String filePath = "./data/Keqing.txt";
    private static final String SEPERATOR = " ";

    public static void deleteStorage() {
        File f = new File(filePath);
        if (f.delete()) {
            System.out.println("file deleted successfully");
        };
    }

    public static Task appendTask(String line) throws IOException{
        Task newTask = new Task();
        String[] currentTaskInfo;
        currentTaskInfo = line.split(SEPERATOR, 3);
        boolean isDone;
        isDone = Integer.parseInt(currentTaskInfo[1]) == 1;
        switch (currentTaskInfo[0]) {
        case "T":
            String toDoContent = currentTaskInfo[2];
            ToDo currentTask = new ToDo(toDoContent);
            if (isDone) {
                currentTask.setDone();
            } else {
                currentTask.setUndone();
            }
            newTask = currentTask;
            break;
        case "D":
            String[] DeadlineContent = new String[2];
            DeadlineContent = currentTaskInfo[2].split(SEPERATOR, 2);
            Deadline currentDeadline = new Deadline(DeadlineContent[0], DeadlineContent[1]);
            if (isDone) {
                currentDeadline.setDone();
            } else {
                currentDeadline.setUndone();
            }
            newTask = currentDeadline;
            break;
        case "E":
            String[] EventContent = new String[3];
            EventContent = currentTaskInfo[2].split(SEPERATOR, 3);
            Event currentEvent = new Event(EventContent[0], EventContent[1], EventContent[2]);
            if (isDone) {
                currentEvent.setDone();
            } else {
                currentEvent.setUndone();
            }
            newTask = currentEvent;
            break;
        default:
            newTask = null;
            break;
        }
        return newTask;
    }

    public static String reformatContent(ArrayList<Task> taskList) {
        String content = "";
        for (int i = 0; i < taskList.size(); i ++) {
            Task currentTask = taskList.get(i);
            content += currentTask.getTaskType();
            content += (SEPERATOR);
            if (currentTask.getStatus()) {
                content += "1";
            }
            else {
                content += "0";
            }
            content += (SEPERATOR + currentTask.getDescription() + SEPERATOR);
            ArrayList<String> attributes = new ArrayList<>();
            attributes = currentTask.returnAttribute();
            if (currentTask.getTaskType().equals("D")) {
                content += (attributes.get(0));
            }
            if (currentTask.getTaskType().equals("E")) {
                content += (attributes.get(0) + SEPERATOR + attributes.get(1));
            }
            content += (System.lineSeparator());
        }
        return content;
    }

    public static void updateFile(ArrayList<Task> taskList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write(reformatContent(taskList));
        writer.close();
    }

    public static ArrayList<Task> loadFile() throws IOException, ArrayIndexOutOfBoundsException {
        ArrayList<Task> copyList = new ArrayList<Task>();
        File f = new File(filePath);
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }
        if (f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                copyList.add(appendTask(line));
            }
        }
        return copyList;
    }
}
