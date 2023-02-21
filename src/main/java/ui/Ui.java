package ui;
import constants.Message;
import java.util.Scanner;

/**
 * A class that deals with the user interaction.
 */
public class Ui {

  protected final Scanner scanner = new Scanner(System.in);

  /**
   * Prints the welcome message upon start up.
   */
  public void printWelcomeMessage() {
    System.out.println(Message.WELCOME);
  }

  /**
   * Prints the goodbye message upon termination.
   */
  public void printGoodbyeMessage() {
    System.out.println(Message.GOODBYE.message);
  }

  /**
   * Prints the error message
   */
  public void printMessage(String message) {
    System.out.println(message);
  }

  /**
   * Reads the input from the user
   *
   * @return The user input as a string
   */
  public String readInput() {
    String input = scanner.nextLine().trim();
    return input;
  }

  /**
   * Mimics the printf
   *
   * @param format The string format
   * @param args The arguments
   */
  public void printf(String format, Object... args) {
    System.out.printf(format, args);
  }
}
