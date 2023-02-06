package storage;

import java.util.ArrayList;
import model.task.Task;

public class Database {

  protected final ArrayList<Task> tasks = new ArrayList<Task>();

  /**
   * A simple function to mimic the database CREATE functionality.
   *
   * @param task The task to be set.
   */
  public void create(Task model) {
    tasks.add(model);
  }

  /**
   * A simple function to mimic the database READ functionality.
   *
   * @param index The index of the task to be read.
   * @return The task at the index.
   * @throws Exception An exception if an invalid index is provided.
   */
  public Task read(int index) throws Exception {
    int size = tasks.size();
    if (index >= size || index < 0) {
      throw new Exception("Invalid index provided.");
    }
    return tasks.get(index);
  }

  /**
   * A simple function to micmic the database UPDATE functionality.
   *
   * @param index The index of the task to be updated.
   * @param task  The new state of the task.
   * @throws Exception An exception if the index is invalid.
   */
  public void update(int index, boolean value) throws Exception {
    int size = tasks.size();
    if (index >= size || index < 0) {
      throw new Exception("Invalid index provided.");
    }
    Task model = tasks.get(index);
    model.setDone(value);
    tasks.set(index, model);
  }

  /**
   * A simple function to micmic the database DELETE functionality.
   *
   * @param index The index of the task to be updated.
   * @param task  The new state of the task.
   * @throws Exception An exception if the index is invalid.
   */
  public void delete(int index) throws Exception {
    int size = tasks.size();
    if (index >= size || index < 0) {
      throw new Exception("Invalid index provided.");
    }
    tasks.remove(index);
  }
}
