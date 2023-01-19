import java.util.Scanner;

public class Duke {

  private static Task taskManager = new Task();

  public static void printText(String text) {
    System.out.printf(
      "\n\t____________________________________________________________\n\t" +
      text +
      "\n\t" +
      "____________________________________________________________\n"
    );
  }

  public static void greet() {
    printText("Hello! I'm Obama\n" + "\tWhat can I do for you?");
  }

  public static void chat() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String ret = sc.nextLine();
      if (ret.equals("bye")) {
        break;
      } else if (ret.equals("list")) {
        taskManager.printList();
      } else if (ret.split(" ")[0].equals("mark")) {
        printText(
          "Nice! I've marked this task as done:\n\t" +
          taskManager.markDone(Integer.parseInt(ret.split(" ")[1]))
        );
      } else if (ret.split(" ")[0].equals("unmark")) {
        printText(
          "OK, I've marked this task as not done yet:\n\t" +
          taskManager.markUndone(Integer.parseInt(ret.split(" ")[1]))
        );
      } else {
        printText("added: " + ret);
        taskManager.add(ret);
      }
    }
  }

  public static void exit() {
    printText("Bye. Hope to see you again soon!");
  }

  public static void main(String[] args) {
    String logo =
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
    System.out.println("Hello from\n" + logo);
    greet();
    chat();
    exit();
  }
}
