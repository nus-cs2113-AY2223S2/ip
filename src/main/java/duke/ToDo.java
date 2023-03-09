package duke;

public class ToDo extends Tasks {

  /**
   * Creates a new Todo object
   * 
   * @param description the description of the task
   */
  public ToDo(String description) {
    super(description);
  }

  /**
   * Returns the string corresponding to the task icon and its description
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
