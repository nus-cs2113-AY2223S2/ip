package misc;

import exceptions.EmptyDescriptorException;
import exceptions.InvalidTaskException;
import exceptions.NoDueDateException;
import exceptions.NoToFromException;
import java.util.Scanner;
import storage.Database;
import tasks.ModifyTaskUtil;
import tasks.TaskManager;

public class UI {

  public static void greet() {
    String LOGO =
      """
 .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
| |     ____     | || |   ______     | || |      __      | || | ____    ____ | || |      __      | |
| |   .'    `.   | || |  |_   _ \\    | || |     /  \\     | || ||_   \\  /   _|| || |     /  \\     | |
| |  /  .--.  \\  | || |    | |_) |   | || |    / /\\ \\    | || |  |   \\/   |  | || |    / /\\ \\    | |
| |  | |    | |  | || |    |  __'.   | || |   / ____ \\   | || |  | |\\  /| |  | || |   / ____ \\   | |
| |  \\  `--'  /  | || |   _| |__) |  | || | _/ /    \\ \\_ | || | _| |_\\/_| |_ | || | _/ /    \\ \\_ | |
| |   `.____.'   | || |  |_______/   | || ||____|  |____|| || ||_____||_____|| || ||____|  |____|| |
| |              | || |              | || |              | || |              | || |              | |
| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
 '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 
                                                          
      """;
    System.out.println("Hello from\n" + LOGO);
    UI.printText("Hello! I'm Obama\n" + "\tWhat can I do for you?");
  }

  public static void exit() {
    UI.printText("Bye. Hope to see you again soon!");
  }

  public static void chat(TaskManager taskManager) {
    Scanner scanner = new Scanner(System.in);
    int flag = 1;
    while (flag == 1) {
      String userInput = scanner.nextLine().trim();
      try {
        switch (userInput.split(" ")[0]) {
          case "bye":
            flag = 0;
            break;
          case "list":
            UI.printText(taskManager.toString());
            break;
          case "mark":
            ModifyTaskUtil.markTaskDone(taskManager, userInput);
            break;
          case "unmark":
            ModifyTaskUtil.markTaskUndone(taskManager, userInput);
            break;
          case "todo":
            ModifyTaskUtil.addTodo(taskManager, userInput);
            break;
          case "deadline":
            ModifyTaskUtil.addDeadline(taskManager, userInput);
            break;
          case "event":
            ModifyTaskUtil.addEvent(taskManager, userInput);
            break;
          case "remove":
            ModifyTaskUtil.removeTask(taskManager, userInput);
            break;
          default:
            UI.printText(
              "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
            );
        }
        Database.writeToDatabase(taskManager);
      } catch (EmptyDescriptorException e) {
        UI.printText(
          String.format(
            "☹ OOPS!!! The description of a %s cannot be empty.",
            userInput.split(" ")[0]
          )
        );
      } catch (InvalidTaskException e) {
        UI.printText(
          String.format(
            "☹ OOPS!!! There is no task to %s, or the specified task is not valid.",
            userInput.split(" ")[0]
          )
        );
      } catch (NoDueDateException e) {
        UI.printText(
          String.format(
            "☹ OOPS!!! The due date of a %s cannot be empty.",
            userInput.split(" ")[0]
          )
        );
      } catch (NoToFromException e) {
        UI.printText(
          String.format(
            "☹ OOPS!!! The to and from fields of a %s cannot be empty.",
            userInput.split(" ")[0]
          )
        );
      }
    }
    if (scanner != null) {
      scanner.close();
    }
  }

  public static void printText(String text) {
    System.out.printf(
      "\n\t____________________________________________________________\n\t" +
      text +
      "\n\t" +
      "____________________________________________________________\n"
    );
  }
}
