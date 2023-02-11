package tasks;

public class Task {

  protected boolean isDone;
  protected String description;

  public Task(String description) {
    this.description = description.trim();
  }

  public boolean getIsDone() {
    return this.isDone;
  }

  public void markDone() {
    this.isDone = true;
  }

  public void markUndone() {
    this.isDone = false;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return String.format("[T][%s] %s\n", isDone ? "X" : " ", description);
  }
}
