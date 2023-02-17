import misc.UI;
import storage.Database;
import tasks.TaskManager;

/**
 * Main Class function
 */
public class Obama {

  private static TaskManager taskManager = Database.loadDatabase();

  public static void main(String[] args) {
    UI.greet();
    UI.chat(taskManager);
    UI.exit();
  }
}
