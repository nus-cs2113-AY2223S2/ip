package duke;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Storage {

  private static final String STORAGE_FILE_NAME = "./data/Duke.txt";

  public static void findFile() {
    try {
      File file = new File(STORAGE_FILE_NAME);
      Scanner scan = new Scanner(file);
      while (scan.hasNext()) {
        System.out.println(scan.nextLine());
      }
      scan.close();
    } catch (FileNotFoundException | NoSuchElementException e) {
      System.out.println("Error: File not found. Please try again");
    }
  }

}
