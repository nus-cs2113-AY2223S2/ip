package duke;

public class Ui {

  public void greet_user() {
    System.out.println("Hello! I'm Duke \n");
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println(logo);
    System.out.println("How can i help u? \n");
  }

  public void goodbye_user() {
    System.out.println("Goodbye. Hope to see u again :) \n");
  }

}
