package tasks;

/**
 * Represents a Deadline, which has all the basic functionality of a Todo,
 * in addition to being able to have a by date.
 */
public class Deadline extends Task {

  private String deadline;

  /**
   * Deadline Class Constructor which takes in a description argument,
   * a deadline argument.
   */
  public Deadline(String description, String deadline) {
    super(description);
    this.deadline = deadline;
  }

  /**
   * This method is a getter used to get the deadline of an Event.
   * @return String This returns a String which corresponds to the deadline
   * of an Event.
   */
  public String getDeadline() {
    return this.deadline;
  }

  /**
   * This method is used to convert an Deadline Instance into a String.
   * @return String This is the output we wish to get when we process an Deadline
   * instance as a String.
   */
  @Override
  public String toString() {
    return String.format(
      "[D][%s] %s (by: %s)\n",
      super.isDone ? "X" : " ",
      super.description,
      deadline
    );
  }
}
