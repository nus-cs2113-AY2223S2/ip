package duke;

import duke.instructions.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String FILE_PATH = "data/tasks.txt";

    protected static boolean isByeEnter = false;

    private static final ArrayList<Task> taskNameList = new ArrayList<>();


    public Duke(String filePath) {

        try {
            Storage.readFile(filePath, taskNameList);
        } catch (java.io.FileNotFoundException e) {
            Ui.showNotFoundError();
        }
    }



    public static void run() {

        Ui.greeting();
        boolean isRunning = true;

        while (isRunning) {
            isRunning = Parser.checkInput(taskNameList);

        }
        Storage.writeTaskToFile(FILE_PATH, taskNameList);
        Parser.sayBye();

    }


/*
    public static void writeTaskToFile(String taskName){

        try{
            File savedFile = new File(FILE_PATH);
            FileWriter writeFile = new FileWriter(savedFile);
            for(Task tasks : taskNameList){
                String typeOfTask = tasks.getTaskType();
               switch(typeOfTask){
               case "D":
                   Deadline newDeadline = (Deadline) tasks;
                   writeFile.write("D | " + newDeadline.getStatusIcon() + " | " + newDeadline.getTaskList());
                   break;
               case "E":
                   Event newEvent = (Event) tasks;
                   writeFile.write("E | " + newEvent.getStatusIcon() + " | " + newEvent.getTaskList());
                   break;
               case "T":
                   Todo newToDo = (Todo) tasks;
                   writeFile.write("E | " + newToDo.getStatusIcon() + " | " + newToDo.getTaskList());
                   break;
               default:
                   System.out.println("Invalid inout, please enetr a valid command");
               }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void checkInput(){
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String taskName = userInput.nextLine();
            if (taskName.equalsIgnoreCase("bye")) {
                writeTaskToFile(taskName);
                break;
            }
            try {
                loadTaskFromFile();
                inputValues(taskName);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
    }*/
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
