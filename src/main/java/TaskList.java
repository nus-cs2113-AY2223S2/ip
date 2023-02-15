import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    public final static String FILEPATH = "duke.txt";
    private Task[] taskArray = new Task[100];
    private int totalTaskNum = 0;

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
            taskArray[totalTaskNum++] = newTodo;
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
            String end = userInputSplited[1].replace("/by ", "");
            Deadline newDeadline = new Deadline (contents, end);
            taskArray[totalTaskNum++] = newDeadline;
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
            String start = userInputSplited[1].replace("/from ", "");
            String end = userInputSplited[2].replace("/to ", "");
            Event newEvent = new Event(contents, start, end);
            taskArray[totalTaskNum++] = newEvent;
        } catch(Exception e){
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            return false;
        }
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public boolean markTask(int taskNumInt){
        taskArray[taskNumInt-1].mark();
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public boolean unmarkTask(int taskNumInt){
        taskArray[taskNumInt-1].unmark();
        writeToFile(FILEPATH, this.toString());
        return true;
    }

    public static void writeToFile(String filePath, String textAdded){
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            FileWriter fw = new FileWriter(file);
            fw.write(textAdded);
            fw.close();
        } catch (IOException e){
            System.out.println("☹ OOPS!!! Something went wrong while saving.");
            System.out.println(e.getMessage());
        }
    }


    @Override
    public String toString(){
        String str = "";
        for(int i=1; i<totalTaskNum+1; i++){
            str = str.concat(i + "." + taskArray[i-1] + "\n");
        }
        return str;
    }

    public Task[] getTaskArray(){
        return taskArray;
    }

    public int getTotalTaskNum(){
        return totalTaskNum;
    }
}

