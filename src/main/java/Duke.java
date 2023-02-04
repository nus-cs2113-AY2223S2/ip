import constants.Command;
import constants.Message;
import controller.TaskController;
import database.Database;

import java.util.Scanner;

public class Duke {

  private static final Database db = new Database();
  private static final TaskController controller = new TaskController(db);
  private static boolean isRunning = true;

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
          controller.listTasks();
          break;
        case Command.DEADLINE:
          taskDescription = words[1];
          controller.addDeadlineTask(taskDescription);
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
