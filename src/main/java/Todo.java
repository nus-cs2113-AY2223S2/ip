public class Todo extends Task {

    public Todo(String description) {
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

    @Override
    public String getDetailsToSave() {
        return super.description;
    }

}
