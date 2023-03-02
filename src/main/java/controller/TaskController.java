package controller;

import constants.Message;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.Todo;
import tasklist.TaskList;
import ui.Ui;
import view.TaskView;

/**
 * This class serves as a controller that will be used to link the model to
 * the view. The MVC framework has been used for separation of concerns.
 */
public class TaskController {

  protected TaskView view = new TaskView();
  protected TaskList db = TaskList.getInstance();
  protected Ui ui = Ui.getInstance();

  private void printDescription(Task model) {
    ui.printMessage(Message.TASK_ADDED);
    view.printTaskDescriptionText(model);
    ui.printf(Message.LIST_NUMBER, db.getSize());
  }

  /**
   * Iterate through the array list and prints out the corresponding task.
   *
   * @throws Exception An exception if there is an issue with
   *                   fetching the data from the database.
   */
  public void listTasks() throws Exception {
    ui.printMessage(Message.LIST_TASKS);
    int numberOfEntries = db.getSize();
    for (int i = 0; i < numberOfEntries; i += 1) {
      Task model = db.read(i);
      ui.printf("%d. ", (i + 1));
      view.printTaskDescriptionText(model);
    }
  }

  /**
   * This function takes in a description for a todo task and add it to the
   * end of the task list. Following which, the JSON file will be updated.
   *
   * @param taskDescription The description of the todo task.
   */
  public void addTodoTask(String taskDescription) {
    Todo model = new Todo(taskDescription);
    db.create(model);
    printDescription(model);
  }

  /**
   * Toggle the task to mark or un-mark based on the user's request. Following
   * which, we will update the JSON file with the updated values in the task
   * list.
   *
   * @param isMark The value to update the task with.
   * @param index  The index of the task to be updated.
   * @throws Exception An exception if the task is invalid
   */
  public void toggleMark(boolean isMark, int index) throws Exception {
    db.update(index, isMark);
    Task model = db.read(index);
    ui.printf(
            "%s\n",
            isMark ? Message.MARKED : Message.UNMARKED
    );
    view.printTaskDescriptionText(model);
  }

  /**
   * This function takes in a deadline task and add it to the end of the
   * task list. Following which, we will update the JSON file again.
   *
   * @param description The task description.
   * @param deadline    The deadline for the deadline task.
   */
  public void addDeadlineTask(String description, String deadline) {
    Deadline model = new Deadline(description, deadline);
    db.create(model);
    printDescription(model);
  }

  /**
   * This function takes in an event task and add it to the end of the
   *  task list. Following which, we will update the JSON file again.
   *
   * @param taskDescription A string after removing the command.
   * @param start The start time of the event task.
   * @param end The end time of the event task.
   */
  public void addEventTask(String taskDescription, String start, String end) {
    Event model = new Event(taskDescription, start, end);
    db.create(model);
    printDescription(model);
  }

  /**
   * Manually add the tasks into the tasks list. This function is used only
   * when we are parsing the JSON file from the task lists and loading up.
   *
   * @param model The task model
   */
  public void manuallyAdd(Task model) {
    db.create(model);
  }

  /**
   * Deletes the task from the task list based on the index provided. Following
   * which, we will update the JSON file.
   *
   * @param index The index of the task to be deleted
   * @throws Exception When the index is out of bounds
   */
  public void deleteTask(int index) throws Exception {
    db.delete(index);
  }

  /**
   * This function is used to help search for the tasks that contains the
   * keywords in the description, and then prints out the corresponding
   * description.
   *
   * @param keyword The keyword provided by the user.
   * @throws Exception When the index provided is out of bounds.
   */
  public void findTask(String keyword) throws Exception {
    int numberOfEntries = db.getSize();
    int counter = 1;
    ui.printMessage(Message.FIND_TASK);
    for (int i = 0; i < numberOfEntries; i += 1) {
      Task model = db.read(i);
      String taskName = model.getDescriptionText();
      if (model.getTaskName().contains(keyword)) {
        ui.printf("%d. %s\n", counter, taskName);
        counter += 1;
      }
    }
  }
}
