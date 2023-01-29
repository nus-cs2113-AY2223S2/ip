//mark tasks as done/undone
public class TaskManager {
    private String aboutTask;
    private boolean isDone;

    public TaskManager(String aboutTask) {
        this.aboutTask = aboutTask;
        //unless explicitly marked by user with Mark command, false by default
        this.isDone = false;
    }

    public String getAboutTask() {
        return aboutTask;
    }

    public boolean markAsDone() {
        //if it has already been marked once, don't remark it again. it makes the program's output strange
        if (this.isDone == true) {
            return false;
        } else {
            this.isDone = true;
            return true;
        }
    }

    public boolean markAsUndone() {
        if (this.isDone == false) {
            return false;
        }
        this.isDone = false;
        return true;
    }

    public char setCheckMark() {
        //false is empty box, too lazy to do the [X]
        char icon = '□';
        if (this.isDone == true) {
            icon = '√';
        }
        return icon;
    }

    //Acknowledgement: https://www.baeldung.com/java-printstream-printf
    public void printTaskAndStatus(int index) {
        System.out.printf("%d: %c %s %n", index + 1, setCheckMark(), getAboutTask());

    }
}
