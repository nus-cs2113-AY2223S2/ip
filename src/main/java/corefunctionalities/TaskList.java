package corefunctionalities;

import dataypes.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(FileHandler fileObject) {
        try {
            taskList = fileObject.readFile();
        } catch (IOException e) {
            System.out.println("Whoops cannot read file");
        }
    }

    //TODO: Add Task into the TaskList -- done
    //TODO: Delete Element from the taskList --done
    //TODO: Retrieve element from the taskList --done
    //TODO: Get the size of the tasklist -- done

    public void addTask(Task task) {
        taskList.add(task); //note that the actual addition is done in the ExceptionHandler
    }

    public int getSize() {
        return taskList.size();
    }
    public int getIndex(Task ele) {
        return taskList.indexOf(ele);
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
    public void find(String toSearch, Ui ui) {
        ui.printLine();
        int counter = 0;
        for(Task ele: taskList) {
            if(ele.getDescription().contains(toSearch)==true) {
                System.out.println("\t" + Integer.toString(getIndex(ele)+1) + "." + ele.getStatusAndDescription());
                counter+=1;
            }
        }
        if(counter == 0) {
            System.out.println("\tSorry, nothing matches this!");
            ui.printLine();
        } else {
            ui.printLine();
        }
    }

}
