import java.util.ArrayList;

public class Lists {
  private static ArrayList<String> list = new ArrayList<String>();

  public static void addItem(String item) {
    Messages.line();
    list.add(item);
    System.out.println("Added: " + item);
    Messages.line();
  }

  public static void printList() {
    Messages.line();
    for (int i = 0; i < list.size(); i++) {
      System.out.println(Integer.toString(i + 1) + ". " + list.get(i));
    }
    Messages.line();
  }
}
