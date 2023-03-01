package shizuka;

public class Command {
    private String keyword;
    private String[] args;

    public Command(String keyword, String[] args) {
        this.keyword = keyword;
        this.args = args;
    }
}
