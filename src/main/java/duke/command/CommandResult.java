package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandResult {
    private final List<String> resultMessages = new ArrayList<>();

    public CommandResult(String resultMessage) {
        resultMessages.add(resultMessage);
    }

    public CommandResult(Collection<String> resultMessages) {
        this.resultMessages.addAll(resultMessages);
    }

    public CommandResult(String... resultMessages) {
        this.resultMessages.addAll(Arrays.asList(resultMessages));
    }

    public List<String> getResultMessages() {
        return resultMessages;
    }
}
