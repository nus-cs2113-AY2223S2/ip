package database;

import model.task.Task;
import parser.FileParser;

public class Database {

  protected final int MAX_TASK = 100;

  protected final FileParser parser = FileParser.getInstance();

  protected final Task[] tasks = new Task[MAX_TASK];

  protected static int counter = 0;

  /**
   * A simple function to mimic the database CREATE functionality.
   *
   * @param task The task to be set. The task will be set in the next
   *             available index if there is still space.
   * @throws Exception An exception if the database is already full.
   */
  public void create(Task task) throws Exception {
    if (counter >= MAX_TASK) {
      throw new Exception("The database is full. Don't ask me how");
    }
    parser.writeToFile(task.getDescriptionText());
    tasks[counter] = task;
    counter += 1;
  }

  /**
   * A simple function to mimic the database READ functionality.
   *
   * @param index The index of the task to be read.
   * @return The task at the index.
   * @throws Exception An exception if an invalid index is provided.
   */
  public Task read(int index) throws Exception {
    if (index >= counter || index < 0) {
      throw new Exception("Invalid index provided.");
    }
    return tasks[index];
  }

  /**
   * A simple function to micmic the database UPDATE functionality.
   *
   * @param index The index of the task to be updated.
   * @param task  The new state of the task.
   * @throws Exception An exception if the index is invalid.
   */
  public void update(int index, boolean value) throws Exception {
    if (index >= counter || index < 0) {
      throw new Exception("Invalid index provided.");
    }
    tasks[index].setDone(value);
  }
}
