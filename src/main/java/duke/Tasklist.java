package duke;

import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;

public class Tasklist {
  private static final String STORAGE_FILE_NAME = "./data/Duke.txt";

  private static ArrayList<Tasks> listOfTasks = new ArrayList<Tasks>();
  private static int counter = 0;

  public static void print_action() {
    System.out.println("\n");
    System.out.println("I have added this task: ");
    System.out.println(listOfTasks.get(counter - 1).toString());

    System.out.println("You now have " + counter + " tasks in the list.");
  }

  public static void todo(String description) {
    try {
      Tasks t = new ToDo(description);
      listOfTasks.add(t);
      counter++;
      print_action();
    } catch (IndexOutOfBoundsException e) {
      System.out.println(":( There is an error (Index is out of bounds/negative)");
    }
  }

  public static void deadline(String description, String by) {
    try {
      Deadline d = new Deadline(description, by);
      listOfTasks.add(d);
      counter++;
      Tasklist.print_action();
    } catch (IndexOutOfBoundsException e) {
      System.out.println(":( There is an error (Index is out of bounds/negative)");
    }
  }

  public static void event(String description, String start, String end) {
    try {
      Event e = new Event(description, start, end);
      listOfTasks.add(e);
      counter++;
      Tasklist.print_action();
    } catch (IndexOutOfBoundsException e) {
      System.out.println(":( There is an error (Index is out of bounds/negative)");
    }

  }

  /**
   * Prints out the tasks in the list
   */
  public static void list() {
    if (listOfTasks.isEmpty()) {
      System.out.println("There are no tasks listed currently");
    } else {
      System.out.println("Here are all of your " + counter + " tasks: ");
      for (int i = 0; i < counter; i++) {
        System.out.println(i + 1 + "." + listOfTasks.get(i).toString());
      }
    }
  }

  /**
   * Deletes a task according to its index in the list
   *
   * @param index The index of the task.
   * @throws IndexOutOfBoundsException If index provided is not within the list
   */
  public static void delete(int index) {
    try {
      Tasks currentTask = listOfTasks.get(index - 1);
      System.out.println("\n");
      System.out.println("Noted. I have deleted this task");
      System.out.println(currentTask.toString());
      listOfTasks.remove(index - 1);
      counter--;
      System.out.println("You now have " + counter + " tasks in the list.");
    } catch (IndexOutOfBoundsException e) {
      System.out.println(":( There is an error (Index is out of bounds/negative)");
    }
  }

  /**
   * Find tasks that matches a specific keyword in you
   *
   * @param keyword The keyword of the t
   * 
   */
  public static void find(String keyword) {
    if (listOfTasks.isEmpty()) {
      System.out.println("There are no tasks listed currently");
    } else {
      int index = 1;
      for (int i = 0; i < listOfTasks.size(); i++) {
        if (listOfTasks.get(i).description.contains(keyword)) {
          if (index == 1) {
            System.out.println("Here are the matching tasks in your list: ");
          }
          System.out.println(index + "." + listOfTasks.get(i).toString());
          index++;
        }
      }
    }
  }

  public static void markTask(int index) {
    try {
      listOfTasks.get(index - 1).markAsDone();
      System.out.println("Nice! This task is completed");
      System.out.println(listOfTasks.get(index - 1).toString());
    } catch (IndexOutOfBoundsException e) {
      System.out.println(":( There is an error (Index is out of bounds/negative)");
    }
  }

  public static void unmarkTask(int index) {
    try {
      listOfTasks.get(index - 1).markAsUnDone();
      System.out.println("Alright, this task has yet to be complete");
      System.out.println(listOfTasks.get(index - 1).toString());
    } catch (IndexOutOfBoundsException e) {
      System.out.println(":( There is an error (Index is out of bounds/negative)");
    }
  }

  public static void saveFile() {
    try {
      FileWriter writer = new FileWriter(STORAGE_FILE_NAME);
      for (int i = 0; i < listOfTasks.size(); i++) {
        writer.write(listOfTasks.get(i).toString() + '\n');
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("Error: Tasks were not saved. Please try again");
    }
  }

}