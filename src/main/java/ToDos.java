public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }
    public String getIcon() {
        return StrIntLib.toDoIcon;
    }
}
