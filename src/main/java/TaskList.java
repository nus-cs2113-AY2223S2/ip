import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class TaskList {
    public final static String FILEPATH = "./duke.txt";
    private ArrayList<Task> taskArray = new ArrayList<>();
    private int totalTaskNum = 0;

    public TaskList(){
        try {
            File file = new File(FILEPATH);
            if (file.createNewFile()) {
                System.out.println("I created the file " + file.getName());
                System.out.println("From now on, I will record your tasks here.");
            } else{
                Scanner scanner = new Scanner(file);
                ArrayList<String[]> existingTasks = new ArrayList<>();
                while(scanner.hasNext()){
                    String task = scanner.nextLine().substring(8);
                    String[] taskInfo = task.split("/");
                    existingTasks.add(taskInfo);
                }
                loadData(existingTasks);
            }
        } catch(IOException e){
            System.out.println("☹ OOPS!!! I cannot create new file.");
            System.out.println(e.getMessage());
        }
    }

    public boolean addTask(String userInput){
        String[] userInputSplited = userInput.split("/");
        String[] userCommand = userInputSplited[0].split(" ");
        switch(userCommand[0]){
            case "todo":
                return addTodo(userInputSplited);
            case "deadline":
                return addDeadline(userInputSplited);
            case "event":
                return addEvent(userInputSplited);
            default:
                System.out.println("Failed to add: Invalid Task format"); return false;
        }
    }

    public boolean addTodo(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("todo ", "");
            Todo newTodo = new Todo(contents);
            taskArray.add(newTodo);
            totalTaskNum++;
        } catch(Exception e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            return false;
        }
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public boolean addDeadline(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("deadline ", "");
            String end = userInputSplited[1].replace("by: ", "");
            Deadline newDeadline = new Deadline (contents, end);
            taskArray.add(newDeadline);
            totalTaskNum++;
        } catch(Exception e){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            return false;
        }
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public boolean addEvent(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("event ", "");
            String start = userInputSplited[1].replace("from: ", "");
            String end = userInputSplited[2].replace("to: ", "");
            Event newEvent = new Event(contents, start, end);
            taskArray.add(newEvent);
            totalTaskNum++;
        } catch(Exception e){
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return false;
        }
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public boolean markTask(int taskNumInt){
        taskArray.get(taskNumInt-1).mark();
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public boolean unmarkTask(int taskNumInt){
        taskArray.get(taskNumInt-1).unmark();
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public static void writeToFile(String filePath, String textAdded){
        try {
            File file = new File(FILEPATH);
            FileWriter fw = new FileWriter(file);
            fw.write(textAdded);
            fw.close();
        } catch (IOException e){
            System.out.println("☹ OOPS!!! Something went wrong while saving.");
            System.out.println(e.getMessage());
        }
    }

    public boolean delete(int taskNumInt){
        try{
            taskArray.remove(taskNumInt-1);
            totalTaskNum--;
            return true;

        } catch(Exception e){
            System.out.println("☹ OOPS!!! I cannot remove the task. Try again.");
            return false;
        }
    }

    public void loadData(ArrayList<String[]> existingTasks){
        for(String[] taskInfo : existingTasks){
            switch(taskInfo.length){
                case 1:
                    addTodo(taskInfo);
                    break;
                case 2:
                    addDeadline(taskInfo);
                    break;
                case 3:
                    addEvent(taskInfo);
                    break;
            }
        }
    }


    @Override
    public String toString(){
        String str = "";
        for(int i=1; i<totalTaskNum+1; i++){
            str = str.concat(i + "." + taskArray.get(i-1) + "\n");
        }
        return str;
    }

    public ArrayList<Task> getTaskArray(){
        return taskArray;
    }

    public int getTotalTaskNum(){
        return totalTaskNum;
    }
}

