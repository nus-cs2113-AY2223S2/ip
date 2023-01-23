import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static boolean isCompleted = false;
    private static final List<Task> userList = new ArrayList<>();

    public void welcomeMessage() {
        separator();
        formatMessage("Hello! I'm Duke");
        formatMessage("What can I do for you?");
        separator();
    }

    public void endingMessage() {
        formatMessage("Bye. Hope to see you again soon!");
    }

    public void separator() {
        String separatorLine = "-".repeat(60);
        System.out.println(separatorLine);
    }

    public void formatMessage(String message) {
        String outputMessage = String.format("| %-57s|", message);
        System.out.println(outputMessage);
    }

    public void checkInput(String inputMessage) {
        if (inputMessage.equalsIgnoreCase("bye")) {
            isCompleted = true;
            separator();
            endingMessage();
            separator();
        } else if (inputMessage.equalsIgnoreCase("list")) {
            separator();
            displayList();
            separator();
        } else if (inputMessage.startsWith("mark")) {
            String[] message = inputMessage.split(" ");
            int itemNumber = Integer.parseInt(message[1]);
            if (itemNumber > userList.size()) {
                String errorMessage = String.format("List only has %d items!", userList.size());
                separator();
                formatMessage(errorMessage);
                separator();
                return;
            }

            separator();
            String outputMessage = String.format("Nice! I've marked task %d as done:", itemNumber);
            formatMessage(outputMessage);
            markItem(itemNumber);
            formatMessage(displayItem(itemNumber));
            separator();
        } else if (inputMessage.startsWith("unmark")) {
            String[] message = inputMessage.split(" ");
            int itemNumber = Integer.parseInt(message[1]);
            if (itemNumber > userList.size()) {
                String errorMessage = String.format("List only has %d items!", userList.size());
                separator();
                formatMessage(errorMessage);
                separator();
                return;
            }

            separator();
            String outputMessage = String.format("OK, I've marked task %d as not done:",
                itemNumber);
            formatMessage(outputMessage);
            unmarkItem(itemNumber);
            formatMessage(displayItem(itemNumber));
            separator();
        } else {
            separator();
            addItem(inputMessage);
            separator();
        }
    }

    public void addItem(String item) {
        Task t = new Task(item);
        userList.add(t);
        String outputMessage = String.format("added: %s", item);
        formatMessage(outputMessage);
    }

    public void displayList() {
        int numItems = userList.size();
        if (numItems == 0) {
            formatMessage("List is empty!");
        } else {
            for (int i = 0; i < numItems; i++) {
                String item = displayItem(i + 1);
                String outputMessage = String.format("%d.%s", i + 1, item);
                formatMessage(outputMessage);
            }
        }
    }

    public String displayItem(int index) {
        String itemStatus = userList.get(index - 1).getStatusIcon();
        String itemDescription = userList.get(index - 1).getDescription();
        return String.format("[%s] %s", itemStatus, itemDescription);
    }

    public void markItem(int index) {
        userList.get(index - 1).setAsDone();
    }

    public void unmarkItem(int index) {
        userList.get(index - 1).setAsNotDone();
    }

    public static class Task {

        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return ((this.isDone) ? "X" : " ");
        }

        public String getDescription() {
            return this.description;
        }

        public void setAsDone() {
            this.isDone = true;
        }

        public void setAsNotDone() {
            this.isDone = false;
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.welcomeMessage();

        String line;
        Scanner in = new Scanner(System.in);

        do {
            line = in.nextLine();
            chatBot.checkInput(line);
        } while (!isCompleted);
    }
}
