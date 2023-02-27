package corefunctionalities;

import dataypes.Task;
import dataypes.TaskFileHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A class that encapsulates an {@link ArrayList<Task>} which contains Tasks entered by the user,
 * and associated functions to use/modify the data.
 *
 * @author Muthya Narayanachary Akhil
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Constructor which reads data (if any) from {@link FileHandler} object using {@link FileHandler#readFile()}
     *
     * @param fileObject file Object which can access <code>dukeData.txt</code>
     * @exception IOException In the event {@link FileHandler#readFile()} is unable to read the file
     */
    public TaskList(FileHandler fileObject) {
        try {
            taskList = fileObject.readFile();
        } catch (IOException e) {
            System.out.println("Whoops cannot read file");
        }
    }

    /**
     * Adds a {@link Task} object to the {@link TaskList#taskList}. Method is used by {@link helpers.Command} to add
     * <code>Task</code> objects to {@link TaskList#taskList}
     *
     * @param task The {@link Task} object which is added to the {@link TaskList#taskList}.
     */
    public void addTask(Task task) {
        taskList.add(task); //note that the actual addition is done in the ExceptionHandler
    }


    /**
     * Returns the size of the {@link TaskList#taskList}. This method is used by methods in {@link Ui}
     * and {@link helpers.Command}
     * @return The size of the {@link TaskList#taskList}
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the index of an {@link Task} ele in the {@link TaskList#taskList}.
     * This method is used in {@link TaskList#find(String, Ui)}
     *
     * @param ele The {@link Task} whose index is being sought out.
     * @return The index of the {@link Task} which is being sought out.
     */
    public int getIndex(Task ele) {
        return taskList.indexOf(ele);
    }

    /**
     * Returns the {@link Task} at index <code>i</code> in  {@link TaskList#taskList}. Used by methods in
     * {@link helpers.Command}, {@link Ui} and {@link helpers.ExceptionGenerator}
     *
     * @param i Represent the index of the {@link Task}
     * @return A {@link Task} object which is at the <code>ith</code> index in {@link TaskList#taskList}
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Removes an instance of {@link Task} at index <code>i</code> in the {@link TaskList#taskList}. Used by methods in
     * {@link helpers.Command}.
     *
     * @param i The index where {@link Task} needs to be removed
     */
    public void removeTask(int i) {
        taskList.remove(i);
    }

    /**
     * Processes {@link Task} from {@link TaskList#taskList} into file readable format
     * using {@link TaskFileHandler#enCode()}
     *
     * Used by {@link FileHandler#populateFile(TaskList)}.
     *
     * @param fileObject In order to update the file with new data
     * @throws IOException In the event the file cannot be written to.
     */
    public void addTaskListFile(FileHandler fileObject) throws IOException{
        for(Task ele: taskList) {
            fileObject.addToFile(ele.enCode() + System.lineSeparator());
        }
    }

    /**
     * Finds instances of a certain keyword entered by the user amidst all the tasks in the {@link TaskList#taskList}.
     * Used by {@link helpers.ExceptionGenerator}.
     *
     * @param toSearch
     * @param ui
     */
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
