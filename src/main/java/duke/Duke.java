package duke;

public class Duke {

    private UI ui;

    public void duke() {
        ui = new UI();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.ui.UI();
        System.exit(0);
    }
}
