package DukeFunctions;


public class Todo {
    String description;
    String type;
    protected boolean isDone;

    public Todo(String contents) {

        this.description = contents;
        this.isDone = false;
        this.type = "T";
    }

    public String getIsDone() {
        String status = " ";

        if (this.isDone == true) {
            status = "X";
        }
        return status;
    }

    public String getType() {

        return this.type;
    }

    public void mark() {
        this.isDone = true;

    }

    public void unMark() {
        this.isDone = false;

    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }


}
