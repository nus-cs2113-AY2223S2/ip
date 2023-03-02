import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    public static void createFile() {
        //File directory = new File("Buddy.txt");
        File newFile = new File("Buddy.txt");

        try {
            //if(directory.mkdirs()){
            //    System.out.println("Directory has been created :)");
            // }
            //else {
            //     System.out.println("Directory exists already!");
            // }

            if (newFile.createNewFile()) {
                System.out.println("File has been created :)");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file :(");
            e.printStackTrace();
        }
    }

    public static void updateFile(ArrayList<Task> taskList) throws IOException {
        /*try {
            createFile();
        } catch (IOException e) {
            System.out.println("Error occurred as the file probably exists");
        }*/
        FileWriter overwriteFile = new FileWriter("Buddy.txt");
        for (Task task : taskList) {
            String taskType = task.getType();
            String taskName = task.getTaskName();
            String taskStatus = task.getStatusIcon();

            switch (taskType) {
                case "T":
                    overwriteFile.write(taskType + "$" + taskStatus + "$" + taskName + "\n");
                    break;
                case "D":
                    Deadline deadlineTask = (Deadline) task;
                    overwriteFile.write(taskType + "$" + taskStatus + "$" + taskName + "$" + deadlineTask.getDeadline() + "\n");
                    break;
                case "E":
                    Event eventTask = (Event) task;
                    overwriteFile.write(taskType + "$" + taskStatus + "$" + taskName + "$" + eventTask.getStart() + "$" + eventTask.getEnd() + "\n");
                    break;
                default:
            }

        }
        overwriteFile.close();

    }


    public static void loadFile(ArrayList<Task> taskList) throws FileNotFoundException {
        File file = new File("Buddy.txt");
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] taskElements = line.split("\\$");
            String taskType = taskElements[0].trim();
            String taskStatus = taskElements[1].trim();
            String taskName = taskElements[2].trim();

            switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(taskName);
                    if (taskStatus.equals("X")) {
                        newTodo.setDone(true);
                    }
                    taskList.add(newTodo);
                    Buddy.taskCount++;
                    break;

                case "D":
                    String deadline = taskElements[3].trim();
                    Deadline newDeadline = new Deadline(taskName, deadline);
                    if (taskStatus.equals("X")) {
                        newDeadline.setDone(true);
                    }
                    taskList.add(newDeadline);
                    Buddy.taskCount++;
                    break;

                case "E":
                    String start = taskElements[3].trim();
                    String end = taskElements[4].trim();
                    Event newEvent = new Event(taskName, start, end);

                    if (taskStatus.equals("X")) {
                        newEvent.setDone(true);
                    }
                    taskList.add(newEvent);
                    Buddy.taskCount++;
                    break;
                default:

            }
        }


    }

}
