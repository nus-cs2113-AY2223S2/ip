public class Tasks {

  protected String description;
  protected boolean isDone;

  public Tasks(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]"); // mark done task with X
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void markAsUnDone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    return getStatusIcon() + " " + description;
  }

}
