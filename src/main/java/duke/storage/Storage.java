package duke.storage;

import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.ui.ExceptionsUi;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    /* file path of the data */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /* read all the data from the file */
    public void getFileData(TaskList taskList) {
        File file = new File(filePath);
        System.out.println("\tLoading existing data . . .\n");
        String input;
        try {
            if (!file.createNewFile()) {
                Scanner fileInputReader = new Scanner(file);
                while (fileInputReader.hasNext()) {
                    input = fileInputReader.nextLine();
                    String[] inputArgs = input.split(" \\| ");
                    addFileDataToList(inputArgs, taskList);
                }
            }
        } catch (IOException e) {
            ExceptionsUi.printIOExceptionError();
        } catch (IllegalStateException e) {
            ExceptionsUi.printInvalidFileContentError();
        } finally {
            Ui.printLoadDataComplete();
        }
    }

    /* interpret the data read from the file into Task and store in task list */
    public void addFileDataToList(String[] inputArgs, TaskList taskList) {
        Task newTask;
        String command = inputArgs[0];
        boolean isMarked = Boolean.parseBoolean(inputArgs[1]);
        switch (command) {
        case "T":
            newTask = new Todo(inputArgs[2]);
            break;
        case "D":
            newTask = new Deadline(inputArgs[2], inputArgs[3]);
            break;
        case "E":
            newTask = new Event(inputArgs[2], inputArgs[3], inputArgs[4]);
            break;
        default:
            throw new IllegalStateException("(!) Invalid file contents.");
        }
        if (isMarked) {
            newTask.mark();
        }
        taskList.getTasks().add(newTask);
    }

    /* store all the tasks in the task list into the file */
    public void saveDataToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : taskList.getTasks()) {
                fileWriter.write(task.toFileFormat());
            }
            fileWriter.close();
        } catch (IOException e) {
            ExceptionsUi.printIOExceptionError();
        }
    }
}
