package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import taskList.TaskList;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.Files.createDirectories;

public class Storage {
    private final String DIR_PATH = "." + File.separator + "data";
    private final String FILE_PATH = DIR_PATH + File.separator + "duke.txt";

    public void loadData(Ui ui, TaskList taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                Path dirPath = Paths.get("./data");
                createDirectories(dirPath);
                file.createNewFile();
                ui.printFileCreated();
                return;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] parameters = data.split(" \\| ");
                Task task = createTask(parameters);
                taskList.add(task);
            }
            scanner.close();
        } catch (DukeException | IOException e) {
            String errorMessage = e.getMessage();
            ui.printErrorMessage(errorMessage);
        }
        ui.printDataLoadSuccess();
    }

    private Task createTask(String[] parameters) throws DukeException {
        Task newTask;
        switch (parameters[0]) {
        case "T":
            newTask = new Todo(parameters[2]);
            break;
        case "D":
            newTask = new Deadline(parameters[2], parameters[3]);
            break;
        case "E":
            newTask = new Event(parameters[2], parameters[3], parameters[4]);
            break;
        default:
            throw new DukeException("Unrecognized data!");
        }

        if (parameters[1].equals("1")) {
            newTask.markDone();
        }

        return newTask;
    }

    public void updateData(TaskList taskList) throws IOException {
        //format content to write
        StringBuilder content = new StringBuilder();
        for (Task task : taskList.getTasks()) {
            content.append(task.getDataSummary());
            content.append("\n");
        }
        FileWriter fileWriter = new FileWriter(FILE_PATH, false);
        fileWriter.write(content.toString());
        fileWriter.close();
    }
}
