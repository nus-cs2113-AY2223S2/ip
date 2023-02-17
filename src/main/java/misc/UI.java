package misc;

import exceptions.EmptyDescriptorException;
import exceptions.InvalidTaskException;
import exceptions.NoDueDateException;
import exceptions.NoFindTermException;
import exceptions.NoToFromException;
import java.util.Scanner;
import storage.Database;
import tasks.ModifyTaskUtil;
import tasks.TaskManager;

/**
 * Handles the main UI functionalities like printing, greeting and chatting.
 */
public class UI {

  /**
   * This method is used just to print the logo and entrance speech.
   */
  public static void greet() {
    System.out.println("Hello from\n" + Constants.LOGO);
    UI.printText("Hello! I'm Obama\n" + "\tWhat can I do for you?");
  }

  /**
   * This method is used just to print exit speech.
   */
  public static void exit() {
    UI.printText("Bye. Hope to see you again soon!");
  }

  /**
   * This method is the main function that the user uses to interact with Obama.
   * @param taskManager This is the taskManager instance that stores all the
   * tasks.
   */
  public static void chat(TaskManager taskManager) {
    Scanner scanner = new Scanner(System.in);
    int toContinueFlag = 1;
    while (toContinueFlag == 1) {
      String userInput = scanner.nextLine().trim();
      try {
        switch (userInput.split(" ")[0]) {
          case "bye":
            toContinueFlag = 0;
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
          case "find":
            ModifyTaskUtil.findTask(taskManager, userInput);
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
      } catch (NoFindTermException e) {
        UI.printText(
          String.format(
            "☹ OOPS!!! There is no find term provided.",
            userInput.split(" ")[0]
          )
        );
      }
    }
    if (scanner != null) {
      scanner.close();
    }
  }

  /**
   * This method is used to print the text with the fancy lines.
   * @param text The text to be printed.
   */
  public static void printText(String text) {
    System.out.printf(
      "\n\t____________________________________________________________\n\t" +
      text +
      "\n\t" +
      "____________________________________________________________\n"
    );
  }
}
