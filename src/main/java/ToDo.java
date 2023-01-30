public class ToDo extends Task{
    public ToDo(int Index, String description) {
        super(Index, description);
    }

    public String toString() {
        String status = null;
        if (isDone) {
            status = "X";
        } else {
            status = " ";
        }
        return super.toString() + "[T]" + "[" + status + "]" + description;
    }


}
