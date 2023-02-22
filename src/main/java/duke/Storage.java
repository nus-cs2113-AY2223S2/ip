package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.*;

public class Storage {
    public static void storeTaskData(File taskDataFile, ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(taskDataFile);
        if(!taskDataFile.exists()){
            taskDataFile.createNewFile();
        }
        for(Task task : tasks){
            switch (task.getTaskType()){
            case TODO:
                fileWriter.write(encodeTaskNameAndStatus(task, CommandInputs.ADD_TODO_COMMAND_INPUT) + System.lineSeparator());
                break;
            case DEADLINE:
                fileWriter.write(encodeDeadlineData((Deadline)task) + System.lineSeparator());
                break;
            case EVENT:
                fileWriter.write(encodeEventData((Event)task) + System.lineSeparator());
                break;
            default:
                // throw some error
                break;
            }
        }
        fileWriter.close();
    }

    public static ArrayList<Task> retrieveTaskData(File taskDataFile) throws Exception{
        if(!taskDataFile.exists()){
            taskDataFile.createNewFile();
        }
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(taskDataFile);
        while(scanner.hasNext()){
            Task newTask = decodeTask(scanner.nextLine());
            tasks.add(newTask);
        }
        return tasks;
    }

    public static Task decodeTask(String encodedTask) throws Exception{
        String[] taskParameters = encodedTask.split("`");
        String taskType = taskParameters[0];
        boolean isDone = taskParameters[1].equals("1");
        String taskName = taskParameters[2];
        Task decodedTask;
        switch(taskType){
        case CommandInputs.ADD_TODO_COMMAND_INPUT:
            decodedTask = new ToDo(taskName);
            decodedTask.setDone(isDone);
            break;
        case CommandInputs.ADD_DEADLINE_COMMAND_INPUT:
            String by = taskParameters[3];
            decodedTask = new Deadline(taskName, by);
            decodedTask.setDone(isDone);
            break;
        case CommandInputs.ADD_EVENT_COMMAND_INPUT:
            String from = taskParameters[3];
            String to = taskParameters[4];
            decodedTask = new Event(taskName, from, to);
            decodedTask.setDone(isDone);
            break;
        default:
            throw new Exception("Task type unidentifiable");
        }
        return decodedTask;
    }

    private static String encodeTaskNameAndStatus(Task task, String taskTypeAsString){
        String encodedOutput = taskTypeAsString;
        if(task.isDone()) {
            encodedOutput = encodedOutput.concat("`1`");
        }else{
            encodedOutput = encodedOutput.concat("`0`");
        }
        encodedOutput = encodedOutput.concat(task.getTaskName());
        return encodedOutput;
    }

    private static String encodeDeadlineData(Deadline deadlineTask){
        String encodedTask = encodeTaskNameAndStatus(deadlineTask, CommandInputs.ADD_DEADLINE_COMMAND_INPUT);
        encodedTask = encodedTask.concat("`" + deadlineTask.getDoBy());
        return encodedTask;
    }

    private static String encodeEventData(Event eventTask){
        String encodedTask = encodeTaskNameAndStatus(eventTask, CommandInputs.ADD_EVENT_COMMAND_INPUT);
        encodedTask = encodedTask.concat("`"+ eventTask.getFrom() + "`" + eventTask.getTo());
        return encodedTask;
    }
}
