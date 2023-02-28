import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents the memory to store tasks.
 */
public class Storage {

    private String dirPath;
    private String filePath;

    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * Calls private function to save list of tasks to memory.
     *
     * @param taskList class to store list of tasks at run time.
     * @param ui Ui class for interaction with user.
     */
    public void save(TaskList taskList, Ui ui) {
        try {
            saveTasks(taskList.tasks, filePath);
        } catch (IOException e) {
            ui.println("Error saving tasks to storage.");
        }
    }

    /**
     * Saves list of tasks to memory.
     *
     * @param tasks The array of tasks.
     * @param filePath The path to file to store tasks.
     * @throws IOException If file is not writable.
     */
    private void saveTasks(ArrayList<Task> tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i<tasks.size(); i++) {
            fw.write(tasks.get(i).getTypeOfTask() + " | " + (tasks.get(i).isDone? "1" : "0") + " | " + tasks.get(i).getDetailsToSave() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Calls private function to load list of tasks from memory.
     *
     * @throws DukeException If file is cannot be created or found.
     * @return TaskList Class containing the list of tasks.
     */
    public TaskList load(Ui ui) throws DukeException {
        TaskList taskList = new TaskList();
        taskList.isSilent = true;
        try {
            taskList = loadTasks(taskList, filePath, ui);
        } catch (FileNotFoundException e) {
            taskList.isSilent = false;
            try {
                File dir = new File(dirPath);
                dir.mkdir();
                File myObj = new File(filePath);
                myObj.createNewFile();

            } catch (IOException e2) {
                System.out.println(e2.getMessage());
                throw new DukeException("");
            }

        }
        taskList.isSilent = false;
        return taskList;
    }

    /**
     * Loads list of tasks to memory.
     *
     * @param taskList class to store list of tasks at run time.
     * @param filePath The path to file to store tasks.
     * @throws FileNotFoundException If file is not found.
     * @return TaskList Class containing the list of tasks.
     */
    private TaskList loadTasks(TaskList taskList , String filePath, Ui ui) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] args = line.split(" \\| ");

            switch (args[0]) {
            case "T":
                try {
                    taskList.addTodo(args[2], ui);
                    if(args[1].equals("1")) {
                        taskList.markTask(taskList.tasks.size(), ui);
                    }
                } catch (DukeException e) {
                    // will not fail
                }
                break;
            case "D":
                try {
                    taskList.addDeadline(args[2], ui);
                    if(args[1].equals("1")) {
                        taskList.markTask(taskList.tasks.size(), ui);
                    }
                } catch (DukeException e) {
                    // will not fail
                }
                break;
            case "E":
                try {
                   taskList.addEvent(args[2], ui);
                    if(args[1].equals("1")) {
                        taskList.markTask(taskList.tasks.size(), ui);
                    }
                } catch (DukeException e) {
                    // will not fail
                }
                break;
            default:
                break;
            }
        }

        return taskList;
    }

}
