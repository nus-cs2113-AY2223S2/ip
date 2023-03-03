package Duke;

/**
 * Represents ToDo subtype of tasks
 */
public class ToDo extends Task {
    public static String TYPE = "todo";

    private ToDo(String content) {
        super(content);
    }

    /**
     * creates new ToDo
     *
     * @param commandByWord String array the contains deadline data.
     * @return New ToDo made according to informations provided.
     * @throws IllegalArgumentException When information given in insufficient.
     * @throws ArrayIndexOutOfBoundsException When information given in insufficient.
     */
    public static ToDo createToDo(String[] commandByWord) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        String task = "";

        if (commandByWord.length < 2) {
            throw new IllegalArgumentException();
        }

        for (int i = 1; i < commandByWord.length; ++i) {
            if (i != 1) {
                task += " ";
            }
            task += commandByWord[i];
        }
        return new ToDo(task);
    }

    public String getType() {
        return ToDo.TYPE;
    }

    /**
     * Returns boolean on whether the keyword in contained in the task's information.
     * It looks through the event's information to determine keyword's relevance
     *
     * @param keyword Word that is looked for in the task.
     * @return Boolean on whether the task contains the keyword.
     */
    public boolean contains(String keyword) {
        return (this.content.contains(keyword));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
