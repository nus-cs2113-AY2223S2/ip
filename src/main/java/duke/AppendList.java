package duke;
public class AppendList extends Task {
    public AppendList(String description) {
        super(description);
    }
    @Override
    public String toString(){
        return description;
    }

}
