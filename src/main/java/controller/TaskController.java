package controller;

import constants.Message;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.Todo;
import storage.Database;
import validator.Validator;
import view.TaskView;


public class TaskController {

  protected TaskView view = new TaskView();
  protected Validator validator = new Validator();
  protected Database db = Database.getInstance();
  static int counter = 0;

  private void printDescription(Task model) {
    System.out.println(Message.TASK_ADDED.message);
    view.printTaskDescriptionText(model);
    System.out.printf(Message.LIST_NUMBER.message, counter);
  }

  /**
   * List out all the tasks in the database.
   *
   * @throws Exception An exception if there is an issue with
   *                   fetching the data from the database.
   */
  public void listTasks() throws Exception {
    System.out.println(Message.LIST_TASKS.message);
    int numberOfEntries = counter;
    for (int i = 0; i < numberOfEntries; i += 1) {
      Task model = db.read(i);
      System.out.printf("%d. ", (i + 1));
      view.printTaskDescriptionText(model);
    }
  }

  /**
   * Adds a todo task into the database.
   *
   * @param taskDescription The description of the todo task.
   * @throws Exception An exception if there is an issue with
   *                   adding the data from the database.
   */
  public void addTodoTask(String taskDescription) throws Exception {
    Todo model = new Todo(taskDescription);
    counter += 1;
    db.create(model);
    printDescription(model);
  }

  /**
   * Updates the task model in the database with the updated value.
   * Following which, we will read the model and print the corresponding value.
   *
   * @param isMark The value to update the task with
   * @param index  The index of the task to be updated
   * @throws Exception An exception if the task is invalid
   */
  public void toggleMark(boolean isMark, int index) throws Exception {
    db.update(index, isMark);
    Task model = db.read(index);
    System.out.printf(
      "%s\n",
      isMark ? Message.MARKED.message : Message.UNMARKED.message
    );
    view.printTaskDescriptionText(model);
  }

  /**
   * Adds a deadline task into the database
   *
   * @param taskDescription The task description
   * @throws Exception An exception if the task is invalid
   */
  public void addDeadlineTask(String description, String deadline) throws Exception {
    Deadline model = new Deadline(description, deadline);
    counter += 1;
    db.create(model);
    printDescription(model);
  }

  /**
   * Adds an event task into the tasks array and prints
   * out the corresponding message
   *
   * @param taskDescription A string after removing the
   *                        command
   */
  public void addEventTask(String taskDescription, String start, String end) throws Exception {
    counter += 1;
    Event model = new Event(taskDescription, start, end);
    db.create(model);
    printDescription(model);
  }

  public void manuallyAdd(Task model) {
    counter += 1;
    db.create(model);
  }
}
