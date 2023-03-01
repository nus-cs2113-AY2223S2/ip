package util;

import java.util.ArrayList;

import errors.ErrorMessages;
import errors.FindTaskError;
import tasks.Task;

/**
 * Carries out the function of finding a task within a list using a keyword
 */
public class Finder extends ErrorMessages {
    private static final String CHAR_SPACE = " ";

    /**
     * Finds a task with a certain keyword given as input
     * 
     * @param listOfTasks The list of tasks to search for keyword
     * @param input       The input containing the keyword
     */
    public void findTaskFromList(ArrayList<Task> listOfTasks, String input) {
        String[] inputString = input.split(CHAR_SPACE, 2);
        String keyword;
        OutputUI outputUI = new OutputUI();
        try {
            if (inputString.length != 2) {
                throw new FindTaskError(provideNoTaskKeywordInput());
            } else if (inputString[1].equals(BLANK)) {
                throw new FindTaskError(provideNoTaskKeywordInput());
            } else {
                keyword = inputString[1];
                ArrayList<Task> output = new ArrayList<>();
                for (int i = 0; i < listOfTasks.size(); i++) {
                    if (listOfTasks.get(i).description.contains(keyword)) {
                        output.add(listOfTasks.get(i));
                    }
                }
                if (output.size() == 0) {
                    outputUI.printNoTaskWithKeyword();
                } else {
                    outputUI.printTaskListWithKeyword(output);
                }
            }
        } catch (FindTaskError e) {
            System.out.println(e.getMessage());
        }
    }
}
