package tasks;

public class Event extends Task {

  private String toDate;
  private String fromDate;

  public Event(String description, String fromDate, String toDate) {
    super(description);
    this.toDate = toDate;
    this.fromDate = fromDate;
  }

  public String getTo() {
    return this.toDate;
  }

  public String getFrom() {
    return this.fromDate;
  }

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
