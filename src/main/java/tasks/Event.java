package tasks;

/**
 * Represents an Event, which has all the basic functionality of a Todo,
 * in addition to being able to have a from and to date.
 */
public class Event extends Task {

  private String toDate;
  private String fromDate;

  /**
   * Event Class Constructor which takes in a description argument,
   * a fromDate argument as well as a toDate argument.
   */
  public Event(String description, String fromDate, String toDate) {
    super(description);
    this.toDate = toDate;
    this.fromDate = fromDate;
  }

  /**
   * This method is a getter used to get the to date of an Event.
   * @return String This returns a String which corresponds to the to date
   * of an Event.
   */
  public String getTo() {
    return this.toDate;
  }

  /**
   * This method is a getter used to get the from date of an Event.
   * @return String This returns a String which corresponds to the from date
   * of an Event.
   */
  public String getFrom() {
    return this.fromDate;
  }

  /**
   * This method is used to convert an Event Instance into a String.
   * @return String This is the output we wish to get when we process an Event
   * instance as a String.
   */
  @Override
  public String toString() {
    return String.format(
      "[E][%s] %s (from: %s to: %s)\n",
      super.isDone ? "X" : " ",
      super.description,
      fromDate,
      toDate
    );
  }
}
