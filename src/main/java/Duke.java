import constants.Command;
import constants.ErrorMessage;
import constants.Message;
import controller.TaskController;
import java.util.Scanner;
import validator.error.InvalidTaskError;
import parser.IoParser;
import java.util.HashMap;

public class Duke {

  private static final TaskController controller = new TaskController();
  private static final IoParser parser = new IoParser();
  private static boolean isRunning = true;

  private static void terminate() {
    isRunning = false;
  }

  private static void process(String[] words) {
    try {
      String command = words[0];
      HashMap<String, String> dictionary;
      String taskDescription;

      switch (command) {
        case Command.LIST:
          controller.listTasks();
          break;
        case Command.DEADLINE:
          dictionary = parser.handleDeadline(words[1]);
          taskDescription = dictionary.get("description");
          String deadline = dictionary.get("deadline");
          controller.addDeadlineTask(taskDescription, deadline);
          break;
        case Command.TODO:
          taskDescription = words[1];
          controller.addTodoTask(taskDescription);
          break;
        case Command.EVENT:
          taskDescription = words[1];
          controller.addEventTask(taskDescription);
          break;
        case Command.MARK:
        case Command.UNMARK:
          int index = Integer.parseInt(words[1]) - 1;
          boolean isMark = words[0].equals(Command.MARK);
          controller.toggleMark(isMark, index);
          break;
        case Command.BYE:
          terminate();
          break;
        default:
          throw new InvalidTaskError(ErrorMessage.INVALID_COMMAND.message);
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println(ErrorMessage.NO_DESCRIPTION.message);
    } catch (InvalidTaskError e) {
      System.out.println(e.getDescription());
    } catch (NumberFormatException e) {
      System.out.println(ErrorMessage.INVALID_NUMBER.message);
    } catch (Exception e) {
      System.out.printf("Server error %s\n", e.getMessage());
    }
  }

  public void run() {
    Scanner in = new Scanner(System.in);
    System.out.println(Message.WELCOME.message);

    while (isRunning) {
      String line = in.nextLine().trim();
      String[] words = line.split(" ", 2);
      process(words);
    }
    System.out.println(Message.GOODBYE.message);
    in.close(); // Closed to prevent memory leak
  }
}
