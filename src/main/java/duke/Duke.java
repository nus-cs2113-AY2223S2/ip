package duke;

import constants.Command;
import constants.ErrorMessage;
import constants.Keyword;
import constants.Message;
import controller.TaskController;
import exception.DukeException;
import parser.Parser;
import ui.Ui;

import java.util.HashMap;

public class Duke {
  protected static Duke instance;

  protected Duke() {
  }

  /**
   * The function that is used to return the instance of Duke. Singleton pattern
   * is used.
   *
   * @return The Duke instance
   */
  public static Duke getInstance() {
    if (instance == null) {
      instance = new Duke();
    }
    return instance;
  }

  private static final TaskController controller = new TaskController();
  private static final Parser parser = new Parser();
  private static final Ui ui = Ui.getInstance();
  private static boolean isRunning = true;

  private static void terminate() {
    isRunning = false;
  }

  private static void process(String words) {
    try {
      HashMap<String, String> dictionary = parser.parse(words);
      String command = dictionary.get(Keyword.COMMAND);
      processDictionary(dictionary, command);
    } catch (DukeException e) {
      ui.printMessage(e.getDescription());
    } catch (NumberFormatException e) {
      ui.printMessage(ErrorMessage.PARSE_INT_ERROR);
    } catch (Exception e) {
      ui.printMessage(e.getMessage());
    }
  }

  private static void processDictionary(HashMap<String, String> dictionary, String command) throws Exception {
    switch (command) {
    case Command.DELETE:
      handleDelete(dictionary);
      break;
    case Command.LIST:
      controller.listTasks();
      break;
    case Command.BYE:
      terminate();
      break;
    case Command.TODO:
      handleTodo(dictionary);
      break;
    case Command.MARK:
    case Command.UNMARK:
      handleMarkOrUnmark(dictionary, command);
      break;
    case Command.DEADLINE:
      handleDeadline(dictionary);
      break;
    case Command.EVENT:
      handleEvent(dictionary);
      break;
    case Command.FIND:
      handleFind(dictionary);
      break;
    default:
      throw new DukeException(ErrorMessage.INVALID_COMMAND);
    }
  }

  private static void handleFind(HashMap<String, String> dictionary) throws Exception {
    String keyword = dictionary.get(Keyword.KEYWORD);
    controller.findTask(keyword);
  }

  private static void handleTodo(HashMap<String, String> dictionary) {
    String description;
    description = dictionary.get(Keyword.DESCRIPTION);
    controller.addTodoTask(description);
  }

  private static void handleDeadline(HashMap<String, String> dictionary) {
    String description = dictionary.get(Keyword.DESCRIPTION);
    String deadline = dictionary.get(Keyword.DEADLINE);
    controller.addDeadlineTask(description, deadline);
  }

  private static void handleEvent(HashMap<String, String> dictionary) {
    String description = dictionary.get(Keyword.DESCRIPTION);
    String start = dictionary.get(Keyword.START);
    String end = dictionary.get(Keyword.END);
    controller.addEventTask(description, start, end);
  }

  private static void handleMarkOrUnmark(HashMap<String, String> dictionary, String command) throws Exception {
    String index = dictionary.get(Keyword.INDEX);
    int position = Integer.parseInt(index) - 1;
    boolean isMark = command.equals(Command.MARK);
    controller.toggleMark(isMark, position);
  }

  private static void handleDelete(HashMap<String, String> dictionary) throws Exception {
    String index = dictionary.get(Keyword.INDEX);
    int position = Integer.parseInt(index) - 1;
    controller.deleteTask(position);
    ui.printMessage(Message.TASK_DELETED);
  }

  /**
   * This function is where the bot will run and when we can do whatever we
   * want with it.
   */
  public void run() {
    ui.printWelcomeMessage();

    while (isRunning) {
      String input = ui.readInput();
      process(input);
    }
    ui.printGoodbyeMessage();
  }
}
