package duke;


import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static boolean shouldContinue = true;
    public static int taskCount = 0;

    public static void main(String[] args) {
        Ui.greetUser();
        ArrayList<Task> tasks = new ArrayList<>();
        TaskData file = new TaskData("./duke.txt");
        tasks = file.loadData(tasks, file);
        taskCount = tasks.size();
        Scanner in = new Scanner(System.in);
        String action;
        while (shouldContinue) {
            action = in.nextLine();
            try {
                tasks = Parser.handleAction(tasks, action, file);
            } catch (DukeException e) {
                Ui.printWrongCommand();
            } catch (DukeException.TaskEmpty e) {
                Ui.printEmptyContent();
            } catch (StringIndexOutOfBoundsException e) {
                Ui.printWrongFormat();
            } catch (NumberFormatException e) {
                Ui.printWrongNumber();
            } catch (IndexOutOfBoundsException e) {
                Ui.printWrongNumber();
            }
        }
    }
}
