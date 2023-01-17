import java.util.Scanner;

public class Duke {
  /**
   * Variadic print function wrapping System.out.println, printing the
   * lines out surrounded by 2 dividers and indented
   *
   * @param lines The text to be printed out. Each argument denotes a piece of
   *              text to be printed out
   *              on a new line
   */
  public static void printMessage(String... lines) {
    System.out.println("\t────────────────────────────────────────────────────────────");
    for (String line : lines) {
      System.out.println("\t" + line);
    }
    System.out.println("\t────────────────────────────────────────────────────────────");
  }

  public static void main(String[] args) {
    printMessage("Hello! I'm Duke", "What can I do for you?");
    // try-with-resources to close the scanner automatically, preventing resource
    // leaks
    try (Scanner sc = new Scanner(System.in)) {
      String line;
      while (true) {
        switch (line = sc.nextLine()) {
          case "bye":
            printMessage("Bye. Hope to see you again soon!");
            return;
          default:
            printMessage(line);
        }
      }
    }
  }
}
