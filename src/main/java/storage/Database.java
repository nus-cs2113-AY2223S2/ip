package storage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import misc.Constants;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskManager;
import tasks.Todo;

/**
 * Represents a database class that is able to read from or write to a CSV file.
 */
public class Database {

  // Format is type, description, marked, to/deadline, from.

  /**
   * This method is used to write to a CSV file based on what is in the
   * task manager at the time.
   * @param taskManager The task manager instance that is being used.
   */
  public static void writeToDatabase(TaskManager taskManager) {
    ArrayList<Task> tasks = taskManager.getAllTasks();
    try {
      FileWriter csvWriter = new FileWriter(Constants.FILE_PATH);
      for (Task task : tasks) {
        String csvRow = "";
        if (task instanceof Todo) {
          csvRow +=
            String.format(
              "todo,%s,%s,0,0\n",
              task.getDescription(),
              task.getIsDone() ? "true" : "false"
            );
        } else if (task instanceof Deadline) {
          csvRow +=
            String.format(
              "deadline,%s,%s,%s,0\n",
              ((Deadline) task).getDescription(),
              ((Deadline) task).getIsDone() ? "true" : "false",
              ((Deadline) task).getDeadline()
            );
        } else if (task instanceof Event) {
          csvRow +=
            String.format(
              "event,%s,%s,%s,%s\n",
              ((Event) task).getDescription(),
              ((Event) task).getIsDone() ? "true" : "false",
              ((Event) task).getTo(),
              ((Event) task).getFrom()
            );
        }
        csvWriter.append(csvRow);
      }
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      System.out.println("An IOException has occured.");
    }
  }

  /**
   * This method is used to read fo a database and pipe the output into a
   * TaskManager instance.
   * @returns TaskManager The TaskManager instance that is being initiated
   * from the CSV file.
   */
  public static TaskManager loadDatabase() {
    TaskManager taskManager = new TaskManager();
    try {
      BufferedReader csvReader = new BufferedReader(
        new FileReader(Constants.FILE_PATH)
      );
      String row;
      int index = 0;
      while ((row = csvReader.readLine()) != null) {
        String[] rowArray = row.split(Constants.DELIMITER);
        switch (rowArray[0]) {
          case "todo":
            String description = rowArray[1];
            Todo todo = new Todo(description);
            taskManager.addTask(todo);
            break;
          case "deadline":
            description = rowArray[1];
            String byDate = rowArray[3];
            Deadline deadline = new Deadline(description, byDate);
            taskManager.addTask(deadline);
            break;
          case "event":
            description = rowArray[1];
            String fromDate = rowArray[4];
            String toDate = rowArray[3];
            Event event = new Event(rowArray[1], fromDate, toDate);
            taskManager.addTask(event);
        }
        if (rowArray[2] == "true") {
          taskManager.markDone(index);
        }
        index++;
      }
      csvReader.close();
    } catch (FileNotFoundException e) {
      try {
        FileWriter csvWriter = new FileWriter(Constants.FILE_PATH);
        csvWriter.close();
      } catch (IOException ex) {
        System.out.println("An IOException has occured.");
      }
    } catch (IOException e) {
      System.out.println("An IOException has occured.");
    }
    return taskManager;
  }
}
