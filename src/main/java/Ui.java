public class Ui {
    public Ui() {}

    public void showGreetingMessage() {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
    }

    public void showDukeInputError() {
        System.out.println("Unable to read inputs! Please try again!!");
    }

    public void showDatabaseLoadingError() {
        System.out.println("Oops! Unable to create or find database!");
    }

    public void exitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showAddingTaskToDatabaseError() {
        System.out.println("Unable to add task to database!");
    }

    public void showDeletingInvalidTaskError() {
        System.out.println("Please delete only valid tasks! List out your tasks if you are unsure!!");
    }

    public void showDeletingTaskFromDatabaseError() {
        System.out.println("Unable to delete properly!");
    }

    public void showMarkingInvalidTaskError() {
        System.out.println("Please mark only valid tasks! List out your tasks if you are unsure!!");
    }

    public void showMarkingTaskInDatabaseError() {
        System.out.println("Unable to mark properly!");;
    }

    public void showUnmarkingInvalidTaskError() {
        System.out.println("Please unmark only valid tasks! List out your tasks if you are unsure!!");
    }

    public void showUnmarkingTaskInDatabaseError() {
        System.out.println("Unable to unmark properly!");;
    }

    public void showListHeaderMessage() {
        System.out.println(" Here are the tasks in your list");
    }

    public void showEmptyListMessage() {
        System.out.println(" Oops! It looks like your list is empty!!");
    }
}
