package Duke;

public class ToDo extends Task {
    private ToDo(String content) {
        super(content);
    }
    static String TYPE = "todo";

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
