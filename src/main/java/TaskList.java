import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> taskArray;
    private int totalTaskNum;
    private Storage taskStorage = new Storage();

    public TaskList(){
        taskArray = new ArrayList<>();
        totalTaskNum = 0;
        try{
            ArrayList<String> existingTasks = Storage.scanData();
            loadData(existingTasks);
        } catch(IOException e){
            UI.printFileLoadingErrorComment();
            System.out.println(e.getMessage());
        }
    }

    public void loadData(ArrayList<String> existingTasks){
        for(String taskInfo : existingTasks){
            String taskState = taskInfo.substring(0,1);
            String[] taskContent = taskInfo.substring(3).split("/");
            switch(taskContent.length){
                case 1:
                    addTodo(taskContent);
                    break;
                case 2:
                    addDeadline(taskContent);
                    break;
                case 3:
                    addEvent(taskContent);
                    break;
            }
            if(taskState.equals("O")) markTask(getTotalTaskNum());
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
                return false;
        }
    }

    public boolean addTodo(String[] userInputSplited){
        try{
            String contents = userInputSplited[0].replace("todo ", "");
            Todo newTodo = new Todo(contents);
            taskArray.add(newTodo);
            totalTaskNum++;
        } catch(Exception e) {
            UI.printEmptyDescriptionComment("todo");
            return false;
        }
        taskStorage.writeToFile(this.toString());
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
            UI.printEmptyDescriptionComment("deadline");
            return false;
        }
        taskStorage.writeToFile(this.toString());
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
            UI.printEmptyDescriptionComment("event");
            return false;
        }
        taskStorage.writeToFile(this.toString());
        return true;
    }

    public boolean markTask(int taskNumInt){
        taskArray.get(taskNumInt-1).mark();
        taskStorage.writeToFile(this.toString());
        return true;
    }

    public boolean unmarkTask(int taskNumInt){
        taskArray.get(taskNumInt-1).unmark();
        taskStorage.writeToFile(this.toString());
        return true;
    }


    public boolean delete(int taskNumInt){
        try{
            taskArray.remove(taskNumInt-1);
            totalTaskNum--;
            taskStorage.writeToFile(this.toString());
            return true;

        } catch(Exception e){
            return false;
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

    public Task getTask(int taskNumInt){
        return taskArray.get(taskNumInt-1);
    }
}

