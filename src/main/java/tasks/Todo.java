package tasks;

/**
 * Represents a Todo Task, the most basic extension of a Task.
 * Has a description and is able to be marked done or not.
 */
public class Todo extends Task {

  /**
   * Todo constructor that takes in a description
   */
  public Todo(String description) {
    super(description);
  }
}
