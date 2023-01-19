import java.util.*;

public class Duke {

  private static ArrayList<String> list = new ArrayList<String>();

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

  public static void printList() {
    String s = "";
    for (int i = 0; i < list.size(); i++) {
      s += String.format("%d. %s\n\t", i + 1, list.get(i));
    }
    printText(s);
  }

  public static void curateList() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String ret = sc.nextLine();
      if (ret.equals("bye")) {
        break;
      } else if (ret.equals("list")) {
        printList();
      } else {
        printText("added: " + ret);
        list.add(ret);
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
    curateList();
    exit();
  }
}
