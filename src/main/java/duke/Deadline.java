package duke;

public class Deadline extends Tasks {

  protected String by;

  /**
   * Creates a new Deadline object
   * 
   * @param description the description of the task
   * @param by          the deadline of the task
   */
  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  /**
   * Returns the string corresponding to the task icon and its deadline
   */
  @Override
  public String toString() {
    return "[D]" + super.toString() + "(by:" + by + ")";
  }

}
