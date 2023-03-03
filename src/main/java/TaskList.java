import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Represents a list of tasks that Duke has to manage your tasks.
 * A <code>TaskList</code> object loads existing data in file named "Duke.txt" whenever a Duke is operated
 * and can add/delete/mark/print tasks on the list.
 * Also, it can automatically update the local file by using Storage class.
 */

public class TaskList {
    private ArrayList<Task> taskArray;
    private int totalTaskNum;
    private Storage taskStorage = new Storage();

    public TaskList(boolean shouldLoad){
        taskArray = new ArrayList<>();
        totalTaskNum = 0;
        if(!shouldLoad){
            return;
        }

        try{
            ArrayList<String> existingTasks = Storage.scanData();
            loadData(existingTasks);
        } catch(IOException e){
            UI.printFileLoadingErrorComment();
            System.out.println(e.getMessage());
        }
    }

    private void loadData(ArrayList<String> existingTasks) throws IOException{
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

    /**
     * Returns boolean value after successfully adding a task to the task list.
     *
     * @param command <code>Command</code> class object that contains a command to add todo, deadline, or event.
     * @return boolean value regarding the success of the addition
     */
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

    /**
     * Returns boolean value after successfully adding a task to the task list.
     *
     * @param task <code>Task</code> class object that contains a todo, deadline, or event task.
     * @return boolean value regarding the success of the addition
     */
    public boolean addTask(Task task){
        taskArray.add(task);
        totalTaskNum++;
        return true;
    }

    private boolean addTodo(Command command){
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

    private boolean addDeadline(Command command){
        try{
            String contents = command.getContent();
            LocalDate by = command.getBy();
            if(by == null) {return false;}
            Deadline newDeadline = new Deadline (contents, by);
            taskArray.add(newDeadline);
            totalTaskNum++;
            taskStorage.writeToFile(this.toString());
        } catch(Exception e){
            UI.printEmptyDescriptionComment("deadline");
            return false;
        }
        return true;
    }

    private boolean addEvent(Command command){
        try{
            String contents = command.getContent();
            LocalDate from = command.getFrom();
            LocalDate to = command.getTo();
            if(from == null || to == null) {return false;}
            Event newEvent = new Event(contents, from, to);
            taskArray.add(newEvent);
            totalTaskNum++;
            taskStorage.writeToFile(this.toString());
        } catch(Exception e){
            UI.printEmptyDescriptionComment("event");
            return false;
        }
        return true;
    }

    /**
     * Marks a task as done and reflects it on the local file.
     *
     * @param taskNumInt the index of a task to mark.
     */

    public void markTask(int taskNumInt){
        taskArray.get(taskNumInt-1).mark();
        taskStorage.writeToFile(this.toString());
    }

    /**
     * Marks a task as undone and reflects it on the local file.
     *
     * @param taskNumInt the index of a task to unmark.
     */
    public void unmarkTask(int taskNumInt){
        taskArray.get(taskNumInt-1).unmark();
        taskStorage.writeToFile(this.toString());
    }

    /**
     * Deletes a task from the task list, rewrite the local file, and return true.
     * If it fails to delete, it returns false.
     *
     * @param taskNumInt the index of a task to delete.
     * @return boolean value indicating the success of deletion.
     */
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

    public ArrayList<Task> getTaskArray() {
        return taskArray;
    }

    public String showTaskList(){
        String str = "";
        for(int i=1; i<totalTaskNum+1; i++){
            str = str.concat(i + "." + taskArray.get(i-1).showTask() + "\n");
        }
        return str;
    }
}

