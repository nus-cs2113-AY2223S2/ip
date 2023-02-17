package tasks;

/**
 * Builds into Deadlines, Events and Todos. Is able to be marked done or undone,
 * and has a description.
 */
public class Task {

  protected boolean isDone;
  protected String description;

  /**
   * Task Class Constructor which takes in a description argument.
   */
  public Task(String description) {
    this.description = description.trim();
  }

  /**
   * This method is a getter used to check whether a task is done.
   * @return boolean This is a true or false value indicating whether a task is done.
   */
  public boolean getIsDone() {
    return this.isDone;
  }

  /**
   * This method is used to mark a task as done
   */
  public void markDone() {
    this.isDone = true;
  }

  /**
   * This method is used to mark a task as undone
   */
  public void markUndone() {
    this.isDone = false;
  }

  /**
   * This method is a getter used to get the description of a task.
   * @return String This returns a String which corresponds to the task description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * This method is used to convert a Task Instance into a String.
   * @return String This is the output we wish to get when we process a Task
   * instance as a String.
   */
  @Override
  public String toString() {
    return String.format("[T][%s] %s\n", isDone ? "X" : " ", description);
  }
}
