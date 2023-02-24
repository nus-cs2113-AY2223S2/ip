import constants.Command;
import constants.ErrorMessage;
import controller.TaskController;
import exception.DukeException;
import parser.Parser;
import ui.Ui;

import java.util.HashMap;

public class Duke {

  private static final TaskController controller = new TaskController();
  private static final Parser parser = new Parser();
  private static final Ui ui = new Ui();
  private static boolean isRunning = true;

  private static void terminate() {
    isRunning = false;
  }

  private static void process(String words) {
    try {
      HashMap<String, String> dictionary = parser.parse(words);
      String command = dictionary.get("command");
      String description;
      String index;
      int position;

      switch (command) {
      case Command.DELETE:
        index = dictionary.get("index");
        position = Integer.parseInt(index) - 1;
        controller.deleteTask(position);
        break;
      case Command.LIST:
        controller.listTasks();
        break;
      case Command.BYE:
        terminate();
        break;
      case Command.TODO:
        description = dictionary.get("description");
        controller.addTodoTask(description);
        break;
      case Command.MARK:
      case Command.UNMARK:
        index = dictionary.get("index");
        position = Integer.parseInt(index) - 1;
        boolean isMark = command.equals(Command.MARK);
        controller.toggleMark(isMark, position);
        break;
      case Command.DEADLINE:
        description = dictionary.get("description");
        String deadline = dictionary.get("deadline");
        controller.addDeadlineTask(description, deadline);
        break;
      case Command.EVENT:
        description = dictionary.get("description");
        String start = dictionary.get("start");
        String end = dictionary.get("end");
        controller.addEventTask(description, start, end);
        break;
      case Command.FIND:
        String keyword = dictionary.get("keyword");
        controller.findTask(keyword);
        break;
      default:
        throw new DukeException(ErrorMessage.INVALID_COMMAND.message);
      }
    } catch (DukeException e) {
      ui.printMessage(e.getDescription());
    } catch (NumberFormatException e) {
      ui.printMessage(ErrorMessage.PARSE_INT_ERROR.message);
    } catch (Exception e) {
      ui.printMessage(e.getMessage());
    }
  }

  /**
   * Running the chatbot with all the commands
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
