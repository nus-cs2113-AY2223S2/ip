package tasks;

import exceptions.EmptyDescriptorException;
import exceptions.InvalidTaskException;
import exceptions.NoDueDateException;
import exceptions.NoFindTermException;
import exceptions.NoToFromException;
import java.util.ArrayList;
import misc.UI;

/**
 * Provides utility functions to process input by user and translate the input
 * into actions on the task manager.
 */
public class ModifyTaskUtil {

  /**
   * This method is used to display confirmation that a task has been added,
   * as well as to show the number of tasks in the list.
   * @param taskManager The task manager instance that is being used.
   * @param task The task instance that is being used.
   */
  public static void confirmAddTask(TaskManager taskManager, Task task) {
    UI.printText(
      "Got it. I've added this task:\n\t" +
      task +
      String.format(
        "\n\tNow you have %d tasks in the list.",
        taskManager.getNumberOfTasks()
      )
    );
  }

  /**
   * This method is used to display confirmation that a task has been removed,
   * as well as to show the number of tasks in the list.
   * @param taskManager The task manager instance that is being used.
   * @param task The task instance that is being used.
   */
  public static void confirmRemoveTask(TaskManager taskManager, Task task) {
    UI.printText(
      "Noted. I've removed this task:\n\t" +
      task +
      String.format(
        "\n\tNow you have %d tasks in the list.",
        taskManager.getNumberOfTasks() - 1
      )
    );
  }

  /**
   * This method is used to mark a task as done.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception InvalidTaskException On an invalid number being entered or an
   * invalid index being provided.
   * @see InvalidTaskException
   */
  public static void markTaskDone(TaskManager taskManager, String userInput)
    throws InvalidTaskException {
    try {
      int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
      taskManager.markDone(index);
      UI.printText(
        "Nice! I've marked this task as done:\n\t" + taskManager.getTask(index)
      );
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskException();
    } catch (NumberFormatException e) {
      throw new InvalidTaskException();
    }
  }

  /**
   * This method is used to mark a task as undone.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception InvalidTaskException On an invalid number being entered or an
   * invalid index being provided.
   * @see InvalidTaskException
   */
  public static void markTaskUndone(TaskManager taskManager, String userInput)
    throws InvalidTaskException {
    try {
      int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
      taskManager.markUndone(index);
      UI.printText(
        "OK, I've marked this task as not done yet:\n\t" +
        taskManager.getTask(index)
      );
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskException();
    } catch (NumberFormatException e) {
      throw new InvalidTaskException();
    }
  }

  /**
   * This method is used to add a Todo to the list of tasks.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception EmptyDescriptorException When no description is being provided.
   * @see EmptyDescriptorException
   */
  public static void addTodo(TaskManager taskManager, String userInput)
    throws EmptyDescriptorException {
    String description = userInput.substring(4);
    if (description.length() == 0) {
      throw new EmptyDescriptorException();
    }
    Todo todo = new Todo(description);
    taskManager.addTask(todo);
    ModifyTaskUtil.confirmAddTask(taskManager, todo);
  }

  /**
   * This method is used to add a Deadline to the list of tasks.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception EmptyDescriptorException When no description is being provided.
   * @exception NoDueDateException When no due date is being provided.
   * @see EmptyDescriptorException
   * @see NoDueDateException
   */
  public static void addDeadline(TaskManager taskManager, String userInput)
    throws EmptyDescriptorException, NoDueDateException {
    String fullString = userInput.substring(8);
    if (fullString.length() == 0) {
      throw new EmptyDescriptorException();
    }
    try {
      String description = fullString.split(" /by ")[0];
      String by = fullString.split(" /by ")[1];
      Deadline deadline = new Deadline(description, by);
      taskManager.addTask(deadline);
      ModifyTaskUtil.confirmAddTask(taskManager, deadline);
    } catch (IndexOutOfBoundsException e) {
      throw new NoDueDateException();
    }
  }

  /**
   * This method is used to add an Event to the list of tasks.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception EmptyDescriptorException When no description is being provided.
   * @exception NoToFromException When no to or from date is being provided.
   * @see EmptyDescriptorException
   * @see NoToFromException
   */
  public static void addEvent(TaskManager taskManager, String userInput)
    throws EmptyDescriptorException, NoToFromException {
    String fullString = userInput.substring(5);
    if (fullString.length() == 0) {
      throw new EmptyDescriptorException();
    }
    String description = fullString.split(" /")[0];
    try {
      String from = fullString.split(" /")[1].substring(5);
      String to = fullString.split(" /")[2].substring(3);
      Event event = new Event(description, from, to);
      taskManager.addTask(event);
      ModifyTaskUtil.confirmAddTask(taskManager, event);
    } catch (IndexOutOfBoundsException e) {
      throw new NoToFromException();
    }
  }

  /**
   * This method is used to remove a task from the list of tasks.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception InvalidTaskException On an invalid number being entered or an
   * invalid index being provided.
   * @see InvalidTaskException
   */
  public static void removeTask(TaskManager taskManager, String userInput)
    throws InvalidTaskException {
    try {
      int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
      Task task = taskManager.getTask(index);
      ModifyTaskUtil.confirmRemoveTask(taskManager, task);
      taskManager.removeTask(index);
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskException();
    } catch (NumberFormatException e) {
      throw new InvalidTaskException();
    }
  }

  /**
   * This method is used to find a task from the list of tasks.
   * @param taskManager The task manager instance that is being used.
   * @param userInput The text that the user has entered.
   * @exception InvalidTaskException On an invalid number being entered or an
   * invalid index being provided.
   * @see InvalidTaskException
   */
  public static void findTask(TaskManager taskManager, String userInput)
    throws NoFindTermException {
    try {
      String findString = userInput.split(" ")[1].trim();
      ArrayList<Task> tasks = taskManager.getTasksFromFind(findString);

      String toPrint = "";
      if (tasks.size() == 0) {
        UI.printText("There are no matching tasks found.");
        return;
      }
      for (int i = 0; i < tasks.size(); i++) {
        toPrint += String.format("%d. %s\t", i + 1, tasks.get(i));
      }
      UI.printText(toPrint);
    } catch (IndexOutOfBoundsException e) {
      throw new NoFindTermException();
    }
  }
}
