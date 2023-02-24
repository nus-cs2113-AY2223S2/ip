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
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    // write data to a file
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

    // add data read from file to list
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
}
