package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import errors.ErrorMessages;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Manages Storing Data on exit
 * and Loading Data upon starting Duke
 * */
public class Storage {
    private static final Boolean LOAD_FROM_SAVE_DATA = true;
    private static final String MARK = "mark ";
    private static final String FILE_LOCATION = "listData.txt";
    private static final String BLANK = " ";
    private static final String MARKED = "1";
    private static final String UNMARKED = "0";

    private static final String EMPTY = "";


    /**
    * Finds the data file and pushes data into the current Array
    * */
    public void loadData(ArrayList<Task> taskList) {
        File listData = new File(FILE_LOCATION);
        checkForListData(listData);
        pushDataToTaskList(taskList, listData);
    }

    /**
    * Reads every line of data in listData and adds each of them into
    * the current ArrayList of tasks
    * If there are any corrupted/incorrect inputs in the data file,
    * they will not be added and an error will be shown for each line of data corrupted
    * */
    private void pushDataToTaskList(ArrayList<Task> taskList, File listData) {
        try {
            Scanner listDataScanner = new Scanner(listData);
            TaskAdder taskAdder = new TaskAdder();
            Marker marker = new Marker();
            while (listDataScanner.hasNext()) {
                String[] currentTaskInput = listDataScanner.nextLine().split(BLANK, 2);
                taskAdder.addTaskToList(taskList, currentTaskInput[1], LOAD_FROM_SAVE_DATA);
                try {
                    if (Integer.parseInt(currentTaskInput[0]) == 1) {
                        marker.markOrUnamrkTask(taskList, MARK + (taskList.size()), LOAD_FROM_SAVE_DATA);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ErrorMessages.errorCorruptDataErrorText());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(ErrorMessages.errorUnableToFindListData());
        }
    }


    private void checkForListData(File listData) {
        if (!listData.exists()) {
            System.out.println("Data file does not exist, creating a new one");
            try {
                if (listData.createNewFile()) {
                    System.out.println("Created a new listData file");
                }
            } catch (IOException e) {
                System.out.println("We can't create a file for some reason :<, exiting program");
                System.exit(1);
            }
        }
    }

    /**
    * Updates the listData.txt file with every single current task inside
    * the task list.
    * It is sorted out as if it were an normal input with a value of 1 or 0 infront of it
    * E.g.
    * 0 todo do a task
    * */
    public void saveList(ArrayList<Task> taskList) {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println(ErrorMessages.errorDeleteContentErrorText());
        }
        for (Task task : taskList) {
            String currentLine;
            if (task instanceof Todo) {
                currentLine = parseString((Todo) task) + System.lineSeparator();
            } else if (task instanceof Deadline) {
                currentLine = parseString((Deadline) task) + System.lineSeparator();
            } else if (task instanceof Event) {
                currentLine = parseString((Event) task) + System.lineSeparator();
            } else {
                System.out.println(ErrorMessages.errorLoadInvalidTaskErrorText());
                continue;
            }
            try {
                appendToFile(currentLine);
            } catch (IOException e) {
                System.out.println(ErrorMessages.errorUnableToWriteToFileText());
            }
        }
    }

    private static String parseString(Todo todo) {
        String done = MARKED;
        if (todo.getDone()) {
            done = MARKED;
        }
        return done + " todo " + todo.getDescription();
    }

    private static String parseString(Deadline deadline) {
        String done = MARKED;
        if (deadline.getDone()) {
            done = MARKED;
        }
        return done + " deadline " + deadline.getDescription() + " /by " + deadline.getDeadline();
    }

    private static String parseString(Event event) {
        String marked = UNMARKED;
        if (event.getDone()) {
            marked = MARKED;
        }
        return marked + " event " + event.getDescription() + " /from " + event.getStartDate()
                + " /to " + event.getEndDate();
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(Storage.FILE_LOCATION);
        fw.write(EMPTY);
        fw.close();
    }

    private static void appendToFile(String textToAppend) throws IOException {
        // create a FileWriter in append mode
        FileWriter fw = new FileWriter(Storage.FILE_LOCATION, true);
        fw.write(textToAppend);
        fw.close();
    }

}
