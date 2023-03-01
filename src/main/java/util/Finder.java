package util;

import java.util.ArrayList;

import errors.ErrorMessages;
import errors.FindTaskError;
import tasks.Task;



/**
* Carries out the function of finding a task within a list using a keyword
* */
public class Finder extends ErrorMessages {
    private static final String CHAR_SPACE = " ";

    /**
    * Finds a task with a certain keyword given as input
    * @param listOfTasks The list of tasks to search for keyword
    * @param input The input containing the keyword
    * */
    public void findTaskFromList(ArrayList<Task> listOfTasks, String input) {
        String[] inputString = input.split(CHAR_SPACE, 2);
        String keyword;
        try {
            if (inputString.length != 2) {
                throw new FindTaskError();
            }
        } catch (FindTaskError e) {
            System.out.println(e.getMessage());
        }
        keyword = inputString[1];
        ArrayList<Task> output = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getDescription().contains(keyword)) {
                output.add(listOfTasks.get(i));
            }
        }
        if (output.size() == 0) {
            System.out.println("No descriptions containing the keyword is found, Pikapi tried his best");
        } else {
            System.out.println("Pikapi has found  list of things pertaining to your keyword");
            OutputUI outputUI = new OutputUI();
            outputUI.printList(output, output.size());
        }
    }
}
