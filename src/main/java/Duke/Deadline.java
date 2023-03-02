package Duke;

/**
 * Represents deadlines, one subtype of task.
 * It contains deadline information, as well as the duedate.
 */
public class Deadline extends Task {
    private String dueDate;

    public Deadline(String content, String dueDate) {
        super(content);
        this.dueDate = dueDate;
    }

    /**
     * Creates new deadline.
     *
     * @param commandByWord String array the contains deadline data.
     * @return New deadline made according to informations provided.
     * @throws IllegalArgumentException When information given in insufficient.
     * @throws ArrayIndexOutOfBoundsException When information given in insufficient.
     */
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

    /**
     * @return type of task. i.e. deadline
     */
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
