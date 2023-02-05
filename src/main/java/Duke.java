import exceptions.*;
import java.util.Scanner;
import tasks.*;
import utils.PrintUtil;

public class Duke {

  private static TaskManager taskManager = new TaskManager();

  public static void chat() {
    Scanner sc = new Scanner(System.in);
    int flag = 1;
    while (flag == 1) {
      String userInput = sc.nextLine().trim();
      try {
        switch (userInput.split(" ")[0]) {
          case "bye":
            flag = 0;
            break;
          case "list":
            PrintUtil.printText(taskManager.toString());
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
          default:
            PrintUtil.printText(
              "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
            );
        }
      } catch (EmptyDescriptorException e) {
        PrintUtil.printText(
          String.format(
            "☹ OOPS!!! The description of a %s cannot be empty.",
            userInput.split(" ")[0]
          )
        );
      } catch (InvalidTaskException e) {
        PrintUtil.printText(
          String.format(
            "☹ OOPS!!! There is no task to %s, or the specified task is not valid.",
            userInput.split(" ")[0]
          )
        );
      } catch (NoDueDateException e) {
        PrintUtil.printText(
          String.format(
            "☹ OOPS!!! The due date of a %s cannot be empty.",
            userInput.split(" ")[0]
          )
        );
      } catch (NoToFromException e) {
        PrintUtil.printText(
          String.format(
            "☹ OOPS!!! The to and from fields of a %s cannot be empty.",
            userInput.split(" ")[0]
          )
        );
      }
    }
  }

  public static void main(String[] args) {
    PrintUtil.greet();
    chat();
    PrintUtil.exit();
  }
}
