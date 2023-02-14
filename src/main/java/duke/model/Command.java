package duke.model;

public class Command {
    protected String type;
    protected Payload payload;

    public Command(String input) {
        String[] commandArray = input.split(" ");
        this.type = commandArray[0].trim();
        String[] payloadStringArray = input.split(this.type);
        if (payloadStringArray.length > 1) {
            payload = new Payload(trimStringArray(payloadStringArray[1].split("/")));
        } else {
            payload = new Payload();
        }
    }

    public String getType() {
        return this.type;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public String[] trimStringArray(String[] stringArray) {
        String[] trimmedStringArray = new String[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            trimmedStringArray[i] = stringArray[i].trim();
        }
        return trimmedStringArray;
    }
}
