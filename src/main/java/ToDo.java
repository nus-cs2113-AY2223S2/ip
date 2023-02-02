public class ToDo extends Task {
    protected String toDo;

    public ToDo(String description, boolean isDone, String toDo) {
        super(description, isDone);
        this.toDo = toDo;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }
}
