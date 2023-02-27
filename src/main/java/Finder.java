import java.util.ArrayList;

public class Finder {
    /**
     * Finds and prints out the list of tasks that contains the keyword
     *
     * @param commands The commands keyed in to find tasks; contains the keyword
     */
    static void findTasks(String[] commands) {
        ArrayList<Task> listOfTasksMatchingKeyword = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < Duke.tasks.size(); i++) {
            String[] words = Duke.tasks.get(i).getDescription().split(" ");
            for (String word : words) {
                if (word.equals(commands[1])) {
                    listOfTasksMatchingKeyword.add(Duke.tasks.get(i));
                    indexes.add(i + 1);
                }
            }
        }
        for (int i = 0; i < listOfTasksMatchingKeyword.size(); i++) {
            Printer.printWholeTask(listOfTasksMatchingKeyword.get(i), indexes.get(i));
        }
        Greeting.printHorizontalLines(Duke.isSinglish);
    }
}
