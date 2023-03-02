package Duke;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String content, String dueDate) {
        super(content);
        this.dueDate = dueDate;
    }

    static Deadline createDeadline(String[] commandByWord) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

        if (commandByWord.length < 2) {
            throw new IllegalArgumentException();
        }

        int index = 1;
        String taskToDo = "";
        String dueDate = "";
        // traverse array until /by is found
        while (commandByWord[index].charAt(0) != '/') {
            // add space between words
            if (index != 1) {
                taskToDo += " ";
            }

            taskToDo += commandByWord[index];
            ++index;
        }

        for (int i = index + 1; i < commandByWord.length; ++i) {
            // add space between words
            if (i != index + 1) {
                dueDate += " ";
            }
            dueDate += commandByWord[i];
        }

        return new Deadline(taskToDo, dueDate);
    }

    String getType() {
        return "deadline";
    }

    boolean contains(String keyword) {
        return (this.content.contains(keyword) | this.dueDate.contains(keyword));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
