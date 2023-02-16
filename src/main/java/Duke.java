import constants.Command;
import constants.ErrorMessage;
import constants.Message;
import controller.TaskController;
import parser.IoParser;
import validator.error.InvalidTaskError;

import java.util.HashMap;
import java.util.Scanner;

public class Duke {

  private static final TaskController controller = new TaskController();
  private static final IoParser parser = new IoParser();
  private static boolean isRunning = true;

  private static void terminate() {
    isRunning = false;
  }

  private static void process(String words) {
    HashMap<String, String> dictionary = parser.parse(words);
    String command = dictionary.get("command");
    String description;
    try {
      switch (command) {
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
        String index = dictionary.get("index");
        int position = Integer.parseInt(index);
        boolean isMark = command.equals(Command.MARK);
        controller.toggleMark(isMark, position);
        break;
      default:
        throw new InvalidTaskError(ErrorMessage.INVALID_COMMAND.message);
      }
    } catch (NumberFormatException e) {
      System.out.println("You have attempted to mark a non-number index");
    } catch (Exception e) {
      System.out.println("Oops, an unexpected error occured!");
    }


    // switch (command) {
    //   case Command.TODO:
    //     dictionary = parser.handleTodo(words[1]);
    //     taskDescription = dictionary.get("description");
    //     controller.addTodoTask(taskDescription);
    //     break;
    //   case Command.EVENT:
    //     taskDescription = words[1];
    //     controller.addEventTask(taskDescription);
    //     break;
    //     break;
    //   default:
    //     throw new InvalidTaskError(ErrorMessage.INVALID_COMMAND.message);
    // }
  }

  public void run() {
    Scanner in = new Scanner(System.in);
    System.out.println(Message.WELCOME.message);

    while (isRunning) {
      String line = in.nextLine().trim();
      process(line);
    }
    System.out.println(Message.GOODBYE.message);
    in.close(); 
  }
}
