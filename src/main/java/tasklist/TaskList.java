package tasklist;

import model.task.Task;
import storage.Storage;

import java.util.ArrayList;

/**
 * Database is a class that is used to mock a real life database. It contains
 * the basic CRUD operations in the modern database. It adheres to the
 * Singleton design pattern which will be taught later.
 */
public class TaskList {

  protected static TaskList instance = null;

  protected final ArrayList<Task> tasks = new ArrayList<Task>();

  protected final static Storage storage = Storage.getInstance();

  protected TaskList() {}

  public static TaskList getInstance() {
    if (instance == null) {
      instance = new TaskList();
    }
    return instance;
  }

  public int getSize() {
    return tasks.size();
  }

  /**
   * A simple function to mimic the database CREATE functionality. The user
   * would just need to pass in the task and it will be saved into the database
   *
   * @param task The task to be set.
   */
  public void create(Task model) {
    tasks.add(model);
    storage.updateFile(tasks);
  }

  /**
   * A simple function to mimic the database READ functionality. The user
   * inputs the index of the database that he would like to read and the
   * database will read it if it exists.
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
   * A simple function to mimic the database UPDATE functionality.
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
    storage.updateFile(tasks);
  }

  /**
   * A simple function to mimic the database DELETE functionality.
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
    storage.updateFile(tasks);
  }
}
