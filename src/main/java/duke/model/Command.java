package duke.model;

public class Command {
    protected String type;
    protected Payload payload;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload getPayload() {
        return this.payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

}
