public class Todos extends Task {

    public Todos (String description) {
        super(description);
    }

    @Override
    public String getTypeOfTask() {
        return "T";
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

}
