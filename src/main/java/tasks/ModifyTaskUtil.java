package tasks;

import exceptions.*;
import utils.PrintUtil;

public class ModifyTaskUtil {

  public static void confirmAddTask(TaskManager taskManager, Task task) {
    PrintUtil.printText(
      "Got it. I've added this task:\n\t" +
      task +
      String.format(
        "\n\tNow you have %d tasks in the list.",
        taskManager.getNumberOfTasks()
      )
    );
  }

  public static void confirmRemoveTask(TaskManager taskManager, Task task) {
    PrintUtil.printText(
      "Noted. I've removed this task:\n\t" +
      task +
      String.format(
        "\n\tNow you have %d tasks in the list.",
        taskManager.getNumberOfTasks() - 1
      )
    );
  }

  public static void markTaskDone(TaskManager taskManager, String userInput)
    throws InvalidTaskException {
    try {
      int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
      taskManager.markDone(index);
      PrintUtil.printText(
        "Nice! I've marked this task as done:\n\t" + taskManager.getTask(index)
      );
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskException();
    } catch (NumberFormatException e) {
      throw new InvalidTaskException();
    }
  }

  public static void markTaskUndone(TaskManager taskManager, String userInput)
    throws InvalidTaskException {
    try {
      int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
      taskManager.markUndone(index);
      PrintUtil.printText(
        "OK, I've marked this task as not done yet:\n\t" +
        taskManager.getTask(index)
      );
    } catch (IndexOutOfBoundsException e) {
      throw new InvalidTaskException();
    } catch (NumberFormatException e) {
      throw new InvalidTaskException();
    }
  }

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
}
