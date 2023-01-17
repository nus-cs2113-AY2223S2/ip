import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {

  private ArrayList<String> tasks = new ArrayList<>();

  /**
   * Variadic print function wrapping System.out.println, printing the
   * lines out surrounded by 2 dividers and indented
   *
   * @param lines The text to be printed out. Each argument denotes a piece of
   *              text to be printed out
   *              on a new line
   */
  public void printMessage(String... lines) {
    System.out.println("\t────────────────────────────────────────────────────────────");
    for (String line : lines) {
      System.out.println("\t" + line);
    }
    System.out.println("\t────────────────────────────────────────────────────────────");
  }

  public String[] getTasksFormatted() {
    return IntStream.rangeClosed(1, tasks.size()).mapToObj(idx -> String.format("%d: %s", idx, tasks.get(idx - 1)))
        .toArray(String[]::new);
  }

  public void addTask(String task) {
    tasks.add(task);
  }

  public void run() {
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
          case "list":
            printMessage(getTasksFormatted());
            break;
          default:
            addTask(line);
            printMessage("added: " + line);
        }
      }
    }
  }

  public static void main(String[] args) {
    Duke duke = new Duke();
    duke.run();
  }
}
