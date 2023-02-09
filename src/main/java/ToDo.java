public class ToDo extends Task {
    private ToDo(String content) {
        super(content);
    }

    static ToDo createToDo(String[] commandByWord) {
        String task = "";
        for (int i = 1; i < commandByWord.length; ++i) {
            if (i != 1) {
                task += " ";
            }
            task += commandByWord[i];
        }
        return new ToDo(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
