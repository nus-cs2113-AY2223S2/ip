package duke.storage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import duke.exception.TaskLoadErrorException;
import duke.exception.InvalidDateTimeFormatException;
import duke.tasks.Tasklist;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;



/*
Format of tasks in txt file:
For todo task:
(taskType) | (Marked/Unmarked) | taskDescription
For deadline task:
(taskType) | (Marked/Unmarked) | taskDescription | by
For event task:
(taskType) | (Marked/Unmarked) | taskDescription | startTime | endTime
*/

public class Storage {
    private final Path filePath ;

    public Storage(String filePath){
        this.filePath = Paths.get(filePath);
    }

    public Tasklist textFileToProgram() throws TaskLoadErrorException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try{
            Files.createDirectories(filePath.getParent());
            File taskFile = filePath.toFile();
            if(!taskFile.createNewFile()) {
                Scanner sc = new Scanner(new FileReader(taskFile));
                while (sc.hasNextLine()) {
                    String textFileLine = sc.nextLine();
                    String[] textFileLines = textFileLine.trim().split("\\s+\\|\\s+");
                    String taskType = textFileLines[0];
                    String status = textFileLines[1];
                    String description = textFileLines[2];
                    Task task = null;
                    if (Objects.equals(taskType, "T")) {
                        task = new Todo(description);
                    } else if (Objects.equals(taskType, "D")) {
                        task = new Deadline(description, textFileLines[3]);
                    } else if (Objects.equals(taskType, "E")) {
                        task = new Event(description, textFileLines[3], textFileLines[4]);
                    }

                    if (Objects.equals(status, "1")) {
                        assert task != null;
                        task.markAsDone();
                    }

                    listOfTasks.add(task);
                }

            }


//                    switch(textFileLines[0]){
//                    case "T": // identify as a to-do task
//                        String taskDescription = textFileLine.substring(8);
//                        Todo todoTask = new Todo(taskDescription, "T");
//                        //Determine if task is pre-marked
//                        if (textFileLines[2].equals("1")){
//                            todoTask.markAsDone();
//                        }
//                        listOfTasks.add(todoTask);
//                        break;
//
//                    case "D": // identify as a deadline task
//                        textFileLine = textFileLine.substring(8);   // read line from task description onwards
//                        int separatorIdx = textFileLine.indexOf("|");
//                        taskDescription = textFileLine.substring(0 , separatorIdx-1);
//                        String by = textFileLine.substring(separatorIdx + 2);
//                        Deadline deadlineTask = new Deadline(taskDescription, "D" , by);
//                        //Determine if task is pre-marked
//                        if (textFileLines[2].equals("1")){
//                            deadlineTask.markAsDone();
//                        }
//                        listOfTasks.add(deadlineTask);
//                        break;
//                    case "E":
//                        textFileLine = textFileLine.substring(8);
//                        separatorIdx = textFileLine.indexOf("|");
//                        int lastSeparatorIdx = textFileLine.lastIndexOf("|");
//                        taskDescription = textFileLine.substring(0, separatorIdx - 1);
//                        String from = textFileLine.substring(separatorIdx + 2, lastSeparatorIdx - 1);
//                        String to = textFileLine.substring(lastSeparatorIdx + 2);
//                        Event event = new Event(taskDescription, "E", from, to);
//                        if (textFileLines[2].equals("1")) {
//                            event.markAsDone();
//                        }
//                        listOfTasks.add(event);
//                        break;



        } catch (IOException exception){
        throw new TaskLoadErrorException();
        } catch(InvalidDateTimeFormatException exception){

        }
        return new Tasklist(listOfTasks);
    }
    // Save changes from program to txt file
    public void updateFile(Tasklist tasklist) {
        try {
            FileWriter taskFileWriter = new FileWriter(filePath.toFile());
            for (int i = 0; i < tasklist.getNumberOfTasks(); i++) {
                taskFileWriter.write(tasklist.getTask(i).saveText());
                taskFileWriter.write(System.lineSeparator());
            }
            taskFileWriter.close();
        } catch (IOException e) {

        }
    }









//        File txtf = new File(filePath);
//        Scanner scan = new Scanner(txtf);
//        // Load data from file
//        while (scan.hasNext()) {
//            String textFileLine = scan.nextLine();
//            String[] textFileLines = textFileLine.split(" ");
//            //Categorise and filter based on taskType
//            switch(textFileLines[0]){
//            case "T": // identify as a to-do task
//                String taskDescription = textFileLine.substring(8);
//                Todo todoTask = new Todo(taskDescription, "T");
//                //Determine if task is pre-marked
//                if (textFileLines[2].equals("1")){
//                    todoTask.markAsDone();
//                }
//                listOfTasks.add(todoTask);
//                break;
//
//            case "D": // identify as a deadline task
//                textFileLine = textFileLine.substring(8);   // read line from task description onwards
//                int separatorIdx = textFileLine.indexOf("|");
//                taskDescription = textFileLine.substring(0 , separatorIdx-1);
//                String by = textFileLine.substring(separatorIdx + 2);
//                Deadline deadlineTask = new Deadline(taskDescription, "D" , by);
//                //Determine if task is pre-marked
//                if (textFileLines[2].equals("1")){
//                    deadlineTask.markAsDone();
//                }
//                listOfTasks.add(deadlineTask);
//                break;
//            case "E":
//                textFileLine = textFileLine.substring(8);
//                separatorIdx = textFileLine.indexOf("|");
//                int lastSeparatorIdx = textFileLine.lastIndexOf("|");
//                taskDescription = textFileLine.substring(0, separatorIdx - 1);
//                String from = textFileLine.substring(separatorIdx + 2, lastSeparatorIdx - 1);
//                String to = textFileLine.substring(lastSeparatorIdx + 2);
//                Event event = new Event(taskDescription, "E", from, to);
//                if (textFileLines[2].equals("1")) {
//                    event.markAsDone();
//                }
//                listOfTasks.add(event);
//                break;
//
//            default:
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//                System.out.println("File conversion is complete!");
//                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//
//            }
//        }
//        return listOfTasks;
//    }

//    public static void writeToFile(String newText) throws IOException{
//        FileWriter fw = new FileWriter(filePath);
//        fw.write(newText);
//        fw.close();
//    }
//    public static void appendTextToFile(String newText) throws IOException {
//        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
//        fw.write(newText);
//        fw.close();
//    }



//    public static void checkFileAccess() throws IOException {
//        File dir = new File(directoryPath);
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//        File f = new File(filePath);
//        if (!f.exists()) {
//            f.createNewFile();
//        }
//    }
}
