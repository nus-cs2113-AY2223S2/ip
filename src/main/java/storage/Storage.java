package storage;

import com.google.gson.Gson;
import constants.Command;
import constants.ErrorMessage;
import controller.TaskController;
import model.storage.JsonStorage;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.Todo;
import ui.Ui;

import java.io.*;
import java.util.ArrayList;

public class Storage {

  protected static Storage instance = null;
  protected static final String FILE_NAME = "./data.json";
  protected static final TaskController taskController = new TaskController();
  protected static final Gson gson = new Gson();
  protected static File file = new File(FILE_NAME);
  protected static Ui ui = Ui.getInstance();

  /**
   * This function is used to create a file if it does not exist. This helps
   * the user to reduce the trouble of having to create his own file and
   * creating it in the incorrect location.
   */
  protected static void createFileIfNotExist() {
    try {
      if (file.createNewFile()) {
        FileWriter writer = new FileWriter(FILE_NAME);
        writer.write("[]");
        writer.close();
      }
    } catch (IOException e) {
      ui.printMessage("An IO Exception occurred");
      e.printStackTrace();
    }
  }

  /**
   * This function is used to read from an existing file. In order to reduce
   * the possibility of file does not exist, I will forcefully create a new
   * file prior to reading.
   */
  public void readFromFile() {
    try {
      createFileIfNotExist();
      BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
      JsonStorage[] x = gson.fromJson(br, JsonStorage[].class);
      boolean isMarked;

      for (JsonStorage item : x) {
        String type = item.getType();
        switch (type) {
        case Command.TODO:
            Todo todo = new Todo(item.getDescription());
            isMarked = item.isMarked();
            todo.setDone(isMarked);
            taskController.manuallyAdd(todo);
            break;
        case Command.EVENT:
            Event event = new Event(
              item.getDescription(),
              item.getStart(),
              item.getEnd()
            );
            isMarked = item.isMarked();
            event.setDone(isMarked);
            taskController.manuallyAdd(event);
            break;
          default:
            Deadline deadline = new Deadline(
              item.getDescription(),
              item.getEnd()
            );
            isMarked = item.isMarked();
            deadline.setDone(isMarked);
            taskController.manuallyAdd(deadline);
            break;
        }
      }
    } catch (FileNotFoundException e) {
      ui.printMessage(ErrorMessage.FOUND_NOT_FOUND_EXCEPTION);
    } catch (Exception e) {
      ui.printMessage(e.getMessage());
    }
  }

  /**
   * This function rewrites the whole JSON file based on the tasks provided.
   *
   * @param tasks The array list from the {@code Storage} class.
   */
  public void updateFile(ArrayList<Task> tasks) {
    ArrayList<JsonStorage> items = new ArrayList<>();
    for (Task task : tasks) {
      JsonStorage item = new JsonStorage();

      // Set the two properties as they are common for all types of task.
      item.setDescription(task.getTaskName());
      item.setMarked(task.isDone());

      // Set the properties based on the type of task.
      if (task instanceof Todo) {
        item.setStart(null);
        item.setEnd(null);
        item.setType(Command.TODO);
      } else if (task instanceof Deadline) {
        Deadline deadlineTask = (Deadline) task;
        item.setStart(null);
        item.setEnd(deadlineTask.getEndDate());
        item.setType(Command.DEADLINE);
      } else if (task instanceof Event) {
        Event eventTask = (Event) task;
        item.setStart(eventTask.getFrom());
        item.setEnd(eventTask.getTo());
        item.setType(Command.EVENT);
      }
      items.add(item);
    }
    String json = gson.toJson(items);

    try {
      FileWriter writer = new FileWriter(FILE_NAME);
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      ui.printMessage(ErrorMessage.IO_EXCEPTION_ERROR);
    }
  }

  protected Storage() {}

  public static Storage getInstance() {
    if (instance == null) {
      instance = new Storage();
    }

    createFileIfNotExist();
    return instance;
  }
}
