package tasks;

public class Event extends Task {

  private String to;
  private String from;

  public Event(String description, String from, String to) {
    super(description);
    this.to = to;
    this.from = from;
  }

  public String getTo() {
    return this.to;
  }

  public String getFrom() {
    return this.from;
  }

  @Override
  public String toString() {
    return String.format(
      "[E][%s] %s (from: %s to: %s)\n",
      super.isDone ? "X" : " ",
      super.description,
      from,
      to
    );
  }
}
