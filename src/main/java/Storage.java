import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    protected static String fileName;
    public static Task dataReader(String saveLine) {
        String [] segments = saveLine.split(" : ");
        System.out.println(saveLine);
        System.out.println(Arrays.toString(segments));
        boolean taskStatus = segments[1].equals("1");
        String taskDescription = segments[2];
        switch(segments[0]) {
        case StrIntLib.deadlineIcon:
            String byDate = segments[3];
            Deadlines newDeadline = new Deadlines(taskDescription, byDate);
            if (taskStatus) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        case StrIntLib.eventIcon:
            String[] startEnd = segments[3].split("-");
            String start = startEnd[0];
            String end = startEnd[1];
            Events newEvent = new Events(taskDescription, start, end);
            if (taskStatus) {
                newEvent.markAsDone();
            }
            return newEvent;
        case StrIntLib.toDoIcon:
            ToDos newToDo = new ToDos(taskDescription);
            if (taskStatus) {
                newToDo.markAsDone();
            }
            return newToDo;
        }
        System.out.println(StrIntLib.invalidTask);
        return null;
    }
    public static String saveFormat(Task selectedTask) {
        String taskStatus = (selectedTask.getStatusIcon().equals("X")) ? "1" : "0";
        String formattedTask = selectedTask.getIcon() + " : " +
                taskStatus + " : " +
                selectedTask.description;
        if (selectedTask instanceof Events) {
            formattedTask += " : " + selectedTask.getStart() + "-" + selectedTask.getEnd();
        } else if (selectedTask instanceof Deadlines) {
            formattedTask += " : " + selectedTask.getDeadline();
        }
        return formattedTask;
    }
    public static void createSave(String filePath) {
        fileName = filePath;
        File saveFile = new File(fileName);
        File directory = new File("data");
        directory.mkdir();
        System.out.println(StrIntLib.saveCreated);

    }

    public static void writeSave() throws IOException {
        FileWriter saveWriter = new FileWriter(fileName);
        for (Task item : TaskList.getTaskList()) {
            saveWriter.write(saveFormat(item)+"\n");
        }
        saveWriter.close();
    }

    public static ArrayList<Task> readSave(){
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File saveFile = new File(fileName);
            Scanner reader = new Scanner(saveFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                Task newTask = dataReader(data);
                taskList.add(newTask);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println(StrIntLib.noSaveFound);
            return taskList;
        }
    }

}
