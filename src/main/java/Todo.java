public class Todo extends Tasks {



    public Todo(String item, boolean isMarked) {
        super(item, isMarked);
    }
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
