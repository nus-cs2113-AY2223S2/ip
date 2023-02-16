import constants.Command;
import constants.Message;
import controller.TaskController;
import parser.IoParser;

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
      }
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
    //   case Command.MARK:
    //   case Command.UNMARK:
    //     int index = Integer.parseInt(words[1]) - 1;
    //     boolean isMark = words[0].equals(Command.MARK);
    //     controller.toggleMark(isMark, index);
    //     break;
    //   case Command.BYE:
    //     terminate();
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
