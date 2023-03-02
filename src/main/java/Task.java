public class Task {
    private String contents;
    private Boolean isDone = false;

    Task(String userInput) {
        contents = userInput;
    }


    @Override
    public String toString() {
        if (isDone) {
            return "[O] " + contents;
        }
        return "[ ] " + contents;
    }

    public String getContents() {
        return contents;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String showTask(){
        if (isDone) {
            return "[O] " + contents;
        }
        return "[ ] " + contents;
    }
}
