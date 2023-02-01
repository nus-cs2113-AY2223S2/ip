import java.util.ArrayList;

public class ToDo extends Task{
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String getClassSymbol() {
        return "T";
    }

}
