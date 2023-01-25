public class Messages {

  public static void logo() {
    String logo = " ____        _        \n"
    + "|  _ \\ _   _| | _____ \n"
    + "| | | | | | | |/ / _ \\\n"
    + "| |_| | |_| |   <  __/\n"
    + "|____/ \\__,_|_|\\_\\___|\n";

    System.out.println(logo + "\n");
  }

  public static void line() {
    System.out.println("____________________________________________________________");
  }

  public static void hello() {
    Messages.line();
    Messages.logo();
    System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    Messages.line();
  }

  public static void bye() {
    System.out.println("Bye. Hope to see you again soon!");
  }

  public static void echo(String userInput) {
    Messages.line();
    System.out.println(userInput);
    Messages.line();
  }
}
