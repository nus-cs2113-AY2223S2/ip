package storage;

import com.google.gson.Gson;
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
  protected static Ui ui = new Ui();

  /**
   * This function is used to create a file if it does not exist. This helps
   * the user to reduce the trouble of having to create his own file and
   * creating it in the incorrect location.
   */
  protected static void createFileIfNotExist() {
    try {
      file.createNewFile();
    } catch (IOException e) {
      ui.printMessage("An IO Exception occured");
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
      boolean isMarked = false;

      for (JsonStorage item : x) {
        String type = item.getType();
        switch (type) {
          case "todo":
            Todo todo = new Todo(item.getDescription());
            isMarked = item.isMarked();
            todo.setDone(isMarked);
            taskController.manuallyAdd(todo);
            break;
          case "event":
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
      ui.printMessage(ErrorMessage.FOUND_NOT_FOUND_EXCEPTION.message);
    } catch (Exception e) {
      ui.printMessage(e.getMessage());
    }
  }

  /**
   * Updates the JSON file
   *
   * @param tasks The array list from Storage
   */
  public void updateFile(ArrayList<Task> tasks) {
    ArrayList<JsonStorage> items = new ArrayList<JsonStorage>();
    for (Task task : tasks) {
      String end = "";
      String type = "";
      String start = "";
      if (task instanceof Todo) {
        start = null;
        end = null;
        type = "todo";
      } else if (task instanceof Deadline) {
        Deadline deadlineTask = (Deadline) task;
        start = null;
        end = deadlineTask.getEndDate();
        type = "deadline";
      } else if (task instanceof Event) {
        Event eventTask = (Event) task;
        start = eventTask.getFrom();
        end = eventTask.getTo();
        type = "event";
      }
      JsonStorage item = new JsonStorage(
        task.getTaskName(),
        task.isDone(),
        end,
        type,
        start
      );
      items.add(item);
    }
    String json = gson.toJson(items);

    try {
      FileWriter writer = new FileWriter(FILE_NAME);
      writer.write(json);
      writer.close();
    } catch (IOException e) {
      ui.printMessage(ErrorMessage.IO_EXCEPTION_ERROR.message);
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
