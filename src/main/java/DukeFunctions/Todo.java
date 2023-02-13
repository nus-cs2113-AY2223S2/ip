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
        System.out.println("目標達成！- [" + this.getIsDone() + "] " + this.description);

    }

    public void unMark() {
        this.isDone = false;
        System.out.println("ええ。。。噓つき。- [" + this.getIsDone() + "] " + this.description);

    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "description: " + description;
    }


}
