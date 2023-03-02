package Duke;

/**
 * Represents ToDo subtype of tasks
 */
public class ToDo extends Task {
    private ToDo(String content) {
        super(content);
    }
    static String TYPE = "todo";

    /**
     * creates new ToDo
     *
     * @param commandByWord String array the contains deadline data.
     * @return New ToDo made according to informations provided.
     * @throws IllegalArgumentException When information given in insufficient.
     * @throws ArrayIndexOutOfBoundsException When information given in insufficient.
     */
    static ToDo createToDo(String[] commandByWord) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
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

    String getType() {
        return "todo";
    }

    boolean contains(String keyword) {
        return (this.content.contains(keyword));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
