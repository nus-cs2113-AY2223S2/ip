package duke;

public class Ui {

    public void showWelcomeMessage() {
        horizontalLine();
        System.out.println("Hello! I'm Duke");
        horizontalLine();
    }

    public void showGoodbyeMessage() {
        System.out.println("I have saved your tasks.");
        System.out.println("Bye. Hope to see you again soon!");
        horizontalLine();
    }

    public void showSavedTasksRetrievalMessage() {
        System.out.println("Please wait, I am attempting to retrieve your files...");
    }

    public void showFileNotFoundMessage() {
        System.out.println("Hmm.. I can't seem to locate a saved file...");
    }

    public void showNewFileCreationMessage() {
        System.out.println("No worries, let me create a new file :D");
    }

    public void showIncorrectCommandWarning() {
        System.out.println("I'm sorry, but I don't know what that means");
        horizontalLine();
    }

    public void showMissingAttributesMessage() {
        System.out.println("Hmm... some details appear to be missing. Please try again.");
        horizontalLine();
    }

    public void showTaskAddedMessage() {
        System.out.println("Sure, I've added this task: ");
    }

    public void showTaskRemovedMessage() {
        System.out.println("Alright, I have removed this task: ");
    }

    public void showDisplayListHeaderMessage() {
        System.out.println("Here are the tasks in your list: ");
    }

    public void showNoTasksToDisplayMessage() {
        System.out.println("There are no tasks to display.");
    }

    public void horizontalLine() {
        System.out.println("________________________________________");
    }
}
