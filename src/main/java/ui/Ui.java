package ui;

import constants.Message;

import java.util.Scanner;

/**
 * The UI class is used to render the User Interaction onto the screen.
 * The Singleton pattern is used here so that we will only create one
 * instance of the class throughout the whole application.
 */
public class Ui {
  protected static Ui instance;

  protected Ui() {
  }

  public static Ui getInstance() {
    if (instance == null) {
      instance = new Ui();
    }
    return instance;
  }

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
    System.out.println(Message.GOODBYE);
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
