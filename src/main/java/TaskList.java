import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray;
    private int totalTaskNum;
    private Storage taskStorage;

    public TaskList(){
        taskArray = new ArrayList<>();
        totalTaskNum = 0;
        taskStorage = new Storage(this);

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
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
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
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
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
            return true;

        } catch(Exception e){
            System.out.println("☹ OOPS!!! I cannot remove the task. Try again.");
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

