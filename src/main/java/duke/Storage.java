package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.*;

/**
 * Class that handles storing and retrieving task data from local storage
 */
public class Storage {

    /**
     * Stores task data into the task data file
     *
     * @param taskDataFile The data file in which to store task data
     * @param tasks The array list of tasks
     * @throws IOException if there was an error initialising the file or storing the data in the file
     */
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

    /**
     * Retrieves task data into an array list of task
     *
     * @param taskDataFile The task data file to retrieve data from
     * @return An array list of tasks
     * @throws Exception if there was an error creating or retrieving data from the task data file
     */
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

    /**
     * Decodes the task data stored as a string into an appropriate task object
     *
     * @param encodedTask The task data as a string
     * @return The appropriate decoded task object
     * @throws Exception if encoded task is invalid
     */
    private static Task decodeTask(String encodedTask) throws Exception{
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

    /**
     * Encodes a task object's name and status into a string for storage
     *
     * @param task The task to be encoded
     * @param taskTypeAsString The type of the task as a string
     * @return A string representing the task's name and status
     */
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

    /**
     * Encodes a deadline type task into a string
     *
     * @param deadlineTask The deadline task object to be encoded
     * @return A string representing the encoded task
     */
    private static String encodeDeadlineData(Deadline deadlineTask){
        String encodedTask = encodeTaskNameAndStatus(deadlineTask, CommandInputs.ADD_DEADLINE_COMMAND_INPUT);
        encodedTask = encodedTask.concat("`" + deadlineTask.getDoBy());
        return encodedTask;
    }

    /**
     * Encodes an event type task into a string
     *
     * @param eventTask The event task object to be encoded
     * @return A string representing the encoded task
     */
    private static String encodeEventData(Event eventTask){
        String encodedTask = encodeTaskNameAndStatus(eventTask, CommandInputs.ADD_EVENT_COMMAND_INPUT);
        encodedTask = encodedTask.concat("`"+ eventTask.getFrom() + "`" + eventTask.getTo());
        return encodedTask;
    }
}
