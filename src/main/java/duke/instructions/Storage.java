package duke.instructions;

import duke.DukeException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class mainly used for saving and loading the file when user exit the program
 */
public class Storage {
    protected static String filePath;
    private static final ArrayList<Task> taskNameList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static final String LINE = "    ____________________________________________________________";


    /**
     * Store the task to txt file
     * @param filePath the path where the file stored
     * @param taskNameList the array list that store the tasks
     */
    public static void writeTaskToFile(String filePath, ArrayList<Task> taskNameList){

        try{
            File savedFile = new File(filePath);
            FileWriter writeFile = new FileWriter(savedFile);
            for(Task tasks : taskNameList){
                String typeOfTask = tasks.getTaskType();
                switch(typeOfTask){
                case "D":
                    Deadline newDeadline = (Deadline) tasks;
                    writeFile.write("D | " + newDeadline.taskStatus() + " | " + newDeadline.getTaskList() + System.lineSeparator());
                    break;
                case "E":
                    Event newEvent = (Event) tasks;
                    writeFile.write("E | " +  newEvent.taskStatus()  + " | " + newEvent.getTaskList() + System.lineSeparator());
                    break;
                case "T":
                    Todo newToDo = (Todo) tasks;
                    writeFile.write("T | " + newToDo.taskStatus()  + " | " + newToDo.getTaskList() + System.lineSeparator());
                    break;
                default:
                    System.out.println("Invalid input, please enter a valid command");
                }
            }
            writeFile.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * Write the file to external txt file
     * @param filePath filePath the path where the file stored
     * @param taskNameList store the list of task
     * @throws FileNotFoundException
     */

    public static void readFile(String filePath, ArrayList<Task> taskNameList) throws FileNotFoundException {

        File savedFile = new File(filePath);
        if (!savedFile.getParentFile().exists()) {
            savedFile.getParentFile().mkdirs();

        }
        try {
            if (!savedFile.exists()) {
                savedFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Failed to create a new file!!!");
        }
        Scanner fileRead = new Scanner(new FileInputStream((filePath)));
        try {
            while (fileRead.hasNext()) {
                String command = fileRead.nextLine();
                String[] inputCommands = command.split("\\|");
                String fileType = inputCommands[0].trim();
                boolean isCompleted = true;
                if (inputCommands[1].trim().equals("Not Done")) {
                    isCompleted = false;
                }

                switch (fileType) {
                case "D":
                    Deadline newDeadline = new Deadline(command);
                    taskNameList.add(newDeadline);
                    newDeadline.statusIcon(isCompleted);
                    break;
                case "T":
                    Todo newTodo = new Todo(command);
                    taskNameList.add(newTodo);
                    newTodo.statusIcon(isCompleted);
                    break;
                case "E":
                    Event newEvent = new Event(command);
                    taskNameList.add(newEvent);
                    newEvent.statusIcon(isCompleted);
                    break;
                default:
                    throw new FileNotFoundException();
                }

                }
            System.out.println("    Hear is the previous task: " + System.lineSeparator() + LINE);
            for (int indexOfInstruction = 0; indexOfInstruction < taskNameList.size(); indexOfInstruction++) {
                System.out.print("     " + (indexOfInstruction + 1) + "."
                        + taskNameList.get(indexOfInstruction).getState().trim() + System.lineSeparator());
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Tasklist.txt file cannot be accessed!");
        }

    }

    }





