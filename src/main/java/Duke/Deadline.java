package Duke;

/**
 * Represents deadlines, one subtype of task.
 * It contains deadline information, as well as the duedate.
 */
public class Deadline extends Task {
    private String dueDate;
    public static String TYPE = "deadline";

    private Deadline(String content, String dueDate) {
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
    public static Deadline createDeadline(String[] commandByWord) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {

        if (commandByWord.length < 2) {
            throw new IllegalArgumentException();
        }

        int index = 1;
        String taskToDo = "";
        String dueDate = "";
        while (commandByWord[index].charAt(0) != '/') {
            if (index != 1) {
                taskToDo += " ";
            }

            taskToDo += commandByWord[index];
            ++index;
        }

        for (int i = index + 1; i < commandByWord.length; ++i) {
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
    public String getType() {
        return Deadline.TYPE;
    }

    /**
     * Returns boolean on whether the keyword in contained in the task's information.
     * It looks through the event's information and deadline to determine keyword's relevance
     *
     * @param keyword Word that is looked for in the task.
     * @return Boolean on whether the task contains the keyword.
     */
    public boolean contains(String keyword) {
        return (this.content.contains(keyword) | this.dueDate.contains(keyword));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dueDate + ")";
    }
}
