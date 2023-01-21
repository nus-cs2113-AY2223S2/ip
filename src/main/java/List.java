import com.sun.source.util.TaskListener;

public class List {
    private int numItems = 0;
    private Task[] taskList;

    public List() {
        taskList = new Task[100]; // Note: only initialise array of obj, but not individual obj
    }

    public void listDisplay() {
        for (int i = 0; i < numItems; i += 1) {
            System.out.print(" " + (i + 1) + ".");

            // checkbox
            if (taskList[i].getIsComplete()) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }

            System.out.println(taskList[i].getTaskName());
        }
    }

    public void listAdd(String sentence) {
        taskList[numItems] = new Task(sentence);
        numItems += 1;

        System.out.print(" added: ");
        System.out.println(sentence);
    }

    public void markTask(String index) {
        int i;
        // ensure its a number
        // https://stackoverflow.com/questions/1486077/good-way-to-encapsulate-integer-parseint
        try {
            i = Integer.parseInt(index);
            i -= 1; // convert to 0-index

            if (i >= numItems) {
                System.out.println(" Invalid task number!");
                return;
            }

            taskList[i].setIsComplete(true);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.print("   [X] ");
            System.out.println(taskList[i].getTaskName());
        } catch (NumberFormatException e) {
            System.out.println("NOT A NUMBER!");
        }
    }

    public void unmarkTask(String index) {
        int i;
        // ensure its a number
        // https://stackoverflow.com/questions/1486077/good-way-to-encapsulate-integer-parseint
        try {
            i = Integer.parseInt(index);
            i -= 1; // convert to 0-index

            if (i >= numItems) {
                System.out.println(" Invalid task number!");
                return;
            }
            taskList[i].setIsComplete(false);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.print("   [ ] ");
            System.out.println(taskList[i].getTaskName());
        } catch (NumberFormatException e) {
            System.out.println("NOT A NUMBER!");
        }
    }
}
