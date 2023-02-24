import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    TaskList(FileHandler fileObject) {
        try {
            taskList = fileObject.readFile();
        } catch (IOException e) {
            System.out.println("Whoops cannot read file");
        }
    }

    //TODO: Add Task into the TaskList -- done
    //TODO: Delete Element from the taskList
    //TODO: Retrieve element from the taskList --done
    //TODO: Get the size of the tasklist -- done

    public void AddTask(Task task) {
        taskList.add(task); //note that the actual addition is done in the ExceptionHandler
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public void removeTask(int i) {
        taskList.remove(i);
    }

    public void addTaskListFile(FileHandler fileObject) throws IOException{
        for(Task ele: taskList) {
            fileObject.addToFile(ele.enCode() + System.lineSeparator());
        }
    }
}
