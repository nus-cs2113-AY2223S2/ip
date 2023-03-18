import java.util.ArrayList;
import java.time.LocalDateTime;

public class Task {
    public static enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private static ArrayList<String> items = new ArrayList<String>();
    private static ArrayList<Boolean> marked = new ArrayList<Boolean>();
    private static ArrayList<TaskType> tasks = new ArrayList<TaskType>();
    private static ArrayList<LocalDateTime> dateTimeFrom = new ArrayList<LocalDateTime>();
    private static ArrayList<LocalDateTime> dateTimeTo = new ArrayList<LocalDateTime>();

    public Task() {
    }

    // setters

    // this is a setter to set the marked status of a task to true
    // to indicate that it is done
    // then performs a sanity check to ensure that the index is within the range
    // before setting the marked status to true
    public void setDone(int num) {
        if (num > items.size() || num < 1) {
            Ui.printInvalidIndex(num);
        } else {

            marked.set(num - 1, true);
            String dateOut = Parser.formatDateOut(num - 1);
            Ui.printMarkDone(num, dateOut);
        }
        Ui.printseparator();
    }

    // this is a setter to set the marked status of a task to false
    // to indicate that it is not done
    // then performs a sanity check to ensure that the index is within the range
    // before setting the marked status to false
    public void setNotDone(int num) {
        if (num > items.size() || num < 1) {
            Ui.printInvalidIndex(num);
        } else {

            marked.set(num - 1, false);
            String dateOut = Parser.formatDateOut(num - 1);
            Ui.printMarkNotDone(num, dateOut);
        }
        Ui.printseparator();
    }

    // this is a setter to delete a task from the list
    // it performs a sanity check to ensure that the index is within the range
    // before deleting the task and its corresponding details
    // from each of the ArrayLists
    public void delete(int num) {
        if (num > items.size() || num < 1) {
            Ui.printInvalidIndex(num);
        } else {
            String dateOut = Parser.formatDateOut(num - 1);
            Ui.printDelete(num, dateOut);
            items.remove(num - 1);
            marked.remove(num - 1);
            tasks.remove(num - 1);
            dateTimeFrom.remove(num - 1);
            dateTimeTo.remove(num - 1);
        }
        Ui.printseparator();
    }

    // this is a setter to add datetime to datetimefrom arraylist
    public static void setAddDateFromArray(LocalDateTime input) {
        dateTimeFrom.add(input);
    }

    // this is a setter to add datetime to datetimeto arraylist
    public static void setAddDateToArray(LocalDateTime input) {
        dateTimeTo.add(input);
    }

    // this is a setter to add tasktype to tasks arraylist
    public static void setAddTypeArray(TaskType input) {
        tasks.add(input);
    }

    // this is a setter to add boolean(done/not done) to marked arraylist
    public static void setAddMarkArray(Boolean input) {
        marked.add(input);
    }

    // this is a setter to add description to items arraylist
    public static void setAddDescArray(String input) {
        items.add(input);
    }

    // getters
    // this is a getter to get the items in the list
    // it performs a sanity check to ensure that the list is not empty
    // before printing the items in the list
    public void getItems() {
        if (items.size() == 0) { // sanity check
            Ui.printNoTask();
        } else {

            for (int i = 0; i < items.size(); i++) {
                String dateOut = Parser.formatDateOut(i);
                Ui.printItem(i + 1, dateOut, i);
            }
        }
        Ui.printseparator();
    }

    // this is a getter to get the description of a task
    public static String getDesc(int num) {
        return items.get(num);
    }

    // this is a getter to get the marked status of a task
    public static Boolean getMark(int num) {
        return marked.get(num);
    }

    // this is a getter to get the task type of a task
    public static TaskType getType(int num) {
        return tasks.get(num);
    }

    // this is a getter to get the start datetime of a task
    public static LocalDateTime getDateTimeFrom(int num) {
        return dateTimeFrom.get(num);
    }

    // this is a getter to get the end/deadline datetime of a task
    public static LocalDateTime getDateTimeTo(int num) {
        return dateTimeTo.get(num);
    }

    // this is a getter to get the size of the list
    public static int getSize() {
        return items.size();
    }

    // normal functions

    // this is a function to display task due before and after a certain date
    // it performs a sanity check to ensure that the list is not empty
    // then it loops through the list 2 times,
    // the first time it prints the items that are due before the date input by the
    // user
    // the second time it prints the items that are due after the date input by the
    // user
    public void getDue(LocalDateTime dateTime) {
        if (items.size() == 0) {
            Ui.printNoTask();
            return;
        }
        Ui.printDueBeforeText(dateTime);
        int count = 1;
        for (int i = 0; i < items.size(); i++) {
            if (getDateTimeTo(i) == null) {
                continue;
            }
            String dateOut = Parser.formatDateOut(i);
            if (dateTime.equals(getDateTimeTo(i)) || dateTime.isAfter(getDateTimeTo(i))) {
                Ui.printItem(count, dateOut, i);
                count++;
            }
        }
        if (count == 1) {
            Ui.printNoDueBefore(dateTime);
        }
        Ui.printseparator();
        count = 1;
        Ui.printDueAfterText(dateTime);
        for (int i = 0; i < items.size(); i++) {
            if (getDateTimeTo(i) == null) {
                continue;
            }
            String dateOut = Parser.formatDateOut(i);
            if (dateTime.isBefore(getDateTimeTo(i))) {
                Ui.printItem(count, dateOut, i);
                count++;
            }
        }
        if (count == 1) {
            Ui.printNoDueAfter(dateTime);
        }
        Ui.printseparator();
    }

    // this is a function to find tasks in the list by input string from user
    // it performs a sanity check to ensure that the list is not empty
    // then it loops through the list to find the items that contain the string
    // input by the user
    public void find(String input) {
        if (items.size() == 0) {
            Ui.printNoTask();
        } else {

            int count = 1;
            for (int i = 0; i < items.size(); i++) {
                String dateOut = Parser.formatDateOut(i);
                if (items.get(i).contains(input)) {
                    Ui.printItem(count, dateOut, i);
                    count++;
                }
            }
            if (count == 1) {
                Ui.printNotFound();
            }
        }
        Ui.printseparator();

    }

    // this is a function to print the most recently added task
    public void print() {
        int size = items.size() - 1;
        String item = Task.items.get(size);
        String dateOut = Parser.formatDateOut(size);
        Ui.printAddTask(size, dateOut, item);
        Ui.printseparator();
    }

}
