import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    String filePath = "Duke.txt";
    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<String> done = new ArrayList<>();
    ArrayList<String> type = new ArrayList<>();
    ArrayList<LocalDateTime> dates_and_times = new ArrayList<>();
    ArrayList<String> filecontents = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    int i = 0;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        i = tasks.size();
        String response = duke.getResponse(input, filecontents, filePath, type, done, tasks, dates_and_times, formatter, i);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (input.startsWith("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }

    public void windowOpened() {
        String text = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        text = "Hello from\n" + text + "What can I do for you?\n";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(text, dukeImage)
        );
        try {
            Duke.readFileContents(filecontents, filePath, i, dates_and_times, formatter, tasks, done, type);
            i = tasks.size();
        } catch (FileNotFoundException | DukeException d) {
            i = 0;
        }
    }
}