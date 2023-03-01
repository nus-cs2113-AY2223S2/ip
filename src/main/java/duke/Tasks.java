package duke;

public class Tasks {

  protected String description;
  protected boolean isDone;

  /**
   * Constructor for a new Tasks object. Stores a description and status of the
   * task. Status of the task is initialised as not done
   * 
   * @param description the description of the new task
   */
  public Tasks(String description) {
    this.description = description;
    this.isDone = false;
  }

  /**
   * A string represents the task icon
   * 
   * @return the string representing the task icon
   */
  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  /**
   * Set the task status as done
   */
  public void markAsDone() {
    this.isDone = true;
  }

  /**
   * Set the task status as not done
   */
  public void markAsUnDone() {
    this.isDone = false;
  }

  /**
   * Returns the string corresponding to the task icon and its description
   */
  @Override
  public String toString() {
    return getStatusIcon() + " " + description;
  }

}
