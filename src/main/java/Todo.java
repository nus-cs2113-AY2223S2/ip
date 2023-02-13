public class Todo extends Task {

    public Todo(String description) {
        super(description);
       // System.out.println(String.format(" [%s] [%s] %s ", 'T', " ", description));


    }

    @Override
    public char getTypeIcon() {
        return 'T';
    }

}
