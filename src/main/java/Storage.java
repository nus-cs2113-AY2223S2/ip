import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> getSavedTasks(Ui ui) throws FileNotFoundException {
        File dir = new File("data");
        File f = new File(filePath);

        // Creates directory for data file if it does not exist
        if (!dir.exists()){
            dir.mkdirs();
        }

        // Create data file if it does not exist
        try {
            f.createNewFile();
        } catch (IOException e) {
            ui.showCreateFileError();
        }

        // Reading from text file to get stored data
        Scanner s = new Scanner(f);
        ArrayList<String> savedTasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            savedTasks.add(line);
        }
        return savedTasks;
    }

    public void saveTasks(TaskList taskList, Ui ui) {
        try {
            writeToFile(taskList);
            ui.showSaveIsSuccessful();
            ui.showFarewell();
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    private void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.numOfTasks; i++) {
            // determine task type
            String taskType = taskList.tasks.get(i).getTaskType();

            // Add task type and isDone for each task
            String lineToWrite = taskType;
            if (taskList.tasks.get(i).isDone) {
                lineToWrite += "|1";
            } else {
                lineToWrite += "|0";
            }

            // Add description for each task
            lineToWrite += "|" + taskList.tasks.get(i).description;

            // Add by or (from and to) fields depending on task type
            if (taskType.equals("D")) {
                lineToWrite += "|" + taskList.tasks.get(i).getBy();
            } else if (taskType.equals("E")) {
                lineToWrite += "|" + taskList.tasks.get(i).getFrom() + "|" + taskList.tasks.get(i).getTo();
            }
            lineToWrite += System.lineSeparator();

            fw.write(lineToWrite);
        }
        fw.close();
    }
}
