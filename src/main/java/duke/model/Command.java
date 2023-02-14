package duke.model;

public class Command {
    protected String type;
    protected Payload payload;
    public Command (String input){
        String[] commandArray = input.split(" ");
        this.type = commandArray[0];
        String payloadString = input.split(this.type)[1];
        payload = new Payload(payloadString.split("/"));
    }

    public String getType() {
        return this.type;
    }
    public Payload getPayload() {
        return this.payload;
    }
}
