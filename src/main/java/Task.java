import java.util.ArrayList;

public class Task {
    private String content;
    private boolean isMarked;

    public Task(String content) {
        this.content = content;
        this.isMarked = false;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unMark() {
        this.isMarked = false;
    }

    public String getMarkingStatus() {
        return isMarked ? "X" : " ";
    }

    public String getContent() {
        return this.content;
    }

    public String getClassSymbol() {
        return "";
    }

    public String getDate() {
        return "";
    }

    public String getBeginDate() {
        return "";
    }

    public String getEndDate() {
        return "";
    }

}
