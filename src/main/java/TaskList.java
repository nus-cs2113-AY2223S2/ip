import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;

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

    public void loadData(ArrayList<String> existingTasks) throws IOException{
        for(String taskInfo : existingTasks){
            Boolean isTaskDone = false;
            if(taskInfo.contains("[O]")){
                isTaskDone = true;
            }

            if(taskInfo.contains("[T]")){
                taskInfo = "todo " + taskInfo.substring(9);
            }else if(taskInfo.contains("[D]")){
                taskInfo = "deadline " + taskInfo.substring(9);
            }else if(taskInfo.contains("[E]")){
                taskInfo = "event " + taskInfo.substring(9);
            }
            Command command = Parser.getCommand(taskInfo);
            if(!addTask(command)){
                UI.printFileLoadingErrorComment();
            }

            if(isTaskDone) {
                markTask(getTotalTaskNum());
            }
        }
    }

    public boolean addTask(Command command){
        switch(command.getType()){
            case "add todo":
                return addTodo(command);
            case "add deadline":
                return addDeadline(command);
            case "add event":
                return addEvent(command);
            default:
                return false;
        }
    }

    public boolean addTodo(Command command){
        try{
            String contents = command.getContent();
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

    public boolean addDeadline(Command command){
        try{
            String contents = command.getContent();
            LocalDate by = command.getBy();
            if(by == null) {return false;}
            Deadline newDeadline = new Deadline (contents, by);
            taskArray.add(newDeadline);
            totalTaskNum++;
        } catch(Exception e){
            UI.printEmptyDescriptionComment("deadline");
            return false;
        }
        taskStorage.writeToFile(this.toString());
        return true;
    }

    public boolean addEvent(Command command){
        try{
            String contents = command.getContent();
            LocalDate from = command.getFrom();
            LocalDate to = command.getTo();
            if(from == null || to == null) {return false;}
            Event newEvent = new Event(contents, from, to);
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

    public int getTotalTaskNum(){
        return totalTaskNum;
    }

    public Task getTask(int taskNumInt){
        return taskArray.get(taskNumInt-1);
    }
}

