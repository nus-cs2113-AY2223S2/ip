package duke.data;

public enum FileNames {
    DIRECTORY("data"),
    FILE_PATH("data\\duke.txt");

    public final String NAME;
    FileNames(String name) {
        NAME = name;
    }
}
