import constants.Message;
import constants.Command;
import java.util.Scanner;
import model.database.Database;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.Todo;

public class Duke {

  private static final Database db = new Database();

  private static boolean isRunning = true;



  /**
   * Prints out all the tasks in the tasks array
   */
  private static void printTasks() throws Exception {
    System.out.println(Message.LIST_TASKS.message);
    for (int i = 0; i < Database.getTaskCount(); ++i) {
      Task task = db.read(i);
      System.out.printf("%d. %s\n", (i + 1), task.getDescriptionText());
    }
  }

  /**
   * Adds a todo task into the array and prints out the
   * corresponding message
   *
   * @param taskDescription A string after removing the
   *                        command
   */
  private static void addTodoTask(String taskDescription) throws Exception {
    Todo newTodo = new Todo(taskDescription);
    db.create(newTodo);
    System.out.printf(
      Message.TASK_ADDED.message,
      newTodo.getDescriptionText(),
      Database.getTaskCount()
    );
  }

  /**
   * Adds a deadline task into the array and prints out
   * the corresponding message
   *
   * @param taskDescription A string after removing the
   *                        command
   */
  private static void addDeadlineTask(String taskDescription) throws Exception {
    int index = taskDescription.indexOf("/by");
    String description = taskDescription.substring(0, index);
    String endDuration = taskDescription.substring(index + "/by ".length());
    Deadline newDeadline = new Deadline(description, endDuration);
    db.create(newDeadline);
    System.out.printf(
      Message.TASK_ADDED.message,
      newDeadline.getDescriptionText()
    );
  }

  /**
   * Adds an event task into the tasks array and prints
   * out the corresponding message
   *
   * @param taskDescription A string after removing the
   *                        command
   */
  private static void addEventTask(String taskDescription) throws Exception {
    int indexOfFrom = taskDescription.indexOf("/from");
    int indexOfTo = taskDescription.indexOf("/to");
    String description = taskDescription.substring(0, indexOfFrom);
    String from = taskDescription.substring(
      indexOfFrom + "/from ".length(),
      indexOfTo
    );
    String to = taskDescription.substring(indexOfTo + "/to ".length());
    Event newEvent = new Event(description, from, to);
    db.create(newEvent);
    System.out.printf(
      Message.TASK_ADDED.message,
      newEvent.getDescriptionText()
    );
  }

  /**
   * Toggles the task as mark or un-mark based on the user
   * request. Combined the two tasks together to abide by
   * Single Responsibility Principle and Don't Repeat
   * Yourself
   *
   * @param isMark Whether the user wants to mark the task
   * @param index  The index of the task to be marked
   */
  private static void toggleMark(boolean isMark, int index) throws Exception {
    db.update(index, isMark);
    Task task = db.read(index);
    System.out.printf(
      "%s\n%s\n",
      isMark ? Message.MARKED.message : Message.UNMARKED.message,
      task.getDescriptionText()
    );
  }

  /**
   * Terminates the programme if the /bye command
   * is given
   */
  private static void terminate() {
    isRunning = false;
  }

  public void run() {
    Scanner in = new Scanner(System.in);
    System.out.println(Message.WELCOME.message);

    while (isRunning) {
      try {
        String line = in.nextLine().trim();
        String[] words = line.split(" ", 2);
        String command = words[0];
        String taskDescription;

        switch (command) {
          case Command.LIST:
            printTasks();
            break;
          case Command.DEADLIINE:
            taskDescription = words[1];
            addDeadlineTask(taskDescription);
            break;
          case Command.TODO:
            taskDescription = words[1];
            addTodoTask(taskDescription);
            break;
          case Command.EVENT:
            taskDescription = words[1];
            addEventTask(taskDescription);
            break;
          case Command.MARK:
          case Command.UNMARK:
            int index = Integer.parseInt(words[1]) - 1;
            boolean isMark = words[0].equals(Command.MARK);
            toggleMark(isMark, index);
            break;
          case Command.BYE:
            terminate();
            break;
          default:
            System.out.println("An invalid command has been provided.");
        }
      } catch (Exception e) {
        System.out.printf("Oops, something went wrong! %s\n", e.getMessage());
      }
    }
    System.out.println(Message.GOODBYE.message);
    in.close(); // Closed to prevent memory leak
  }
}
