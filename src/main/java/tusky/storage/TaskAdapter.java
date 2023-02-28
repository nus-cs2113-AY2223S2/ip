package tusky.storage;

import com.google.gson.*;
import tusky.tasks.Deadline;
import tusky.tasks.Event;
import tusky.tasks.ToDo;
import tusky.tasks.Task;
import tusky.ui.Ui;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskAdapter implements JsonDeserializer<Task> {
    private final Ui ui;

    public TaskAdapter (Ui ui) {
        this.ui = ui;
    }

    @Override
    public Task deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        try {
            String type = jsonObject.get("taskType").getAsString();
            switch (type) {
            case "TODO":
                return new ToDo(
                        jsonObject.get("isDone").getAsString(),
                        jsonObject.get("description").getAsString()
                );
            case "EVENT":
                try {
                    return new Event(
                            jsonObject.get("isDone").getAsString(),
                            jsonObject.get("description").getAsString(),
                            jsonObject.get("from").getAsString(),
                            jsonObject.get("to").getAsString()
                    );
                } catch (DateTimeParseException e) {
                    ui.showInvalidDate(jsonObject.get("description").getAsString());
                    return new Event(
                            jsonObject.get("isDone").getAsString(),
                            jsonObject.get("description").getAsString(),
                            LocalDate.now(),
                            LocalDate.now()
                    );
                }
            case "DEADLINE":
                try {
                    return new Deadline(
                            jsonObject.get("isDone").getAsString(),
                            jsonObject.get("description").getAsString(),
                            jsonObject.get("by").getAsString()
                    );
                } catch (DateTimeParseException e) {
                    ui.showInvalidDate(jsonObject.get("description").getAsString());

                    return new Deadline(
                            jsonObject.get("isDone").getAsString(),
                            jsonObject.get("description").getAsString(),
                            LocalDate.now()
                    );
                }
            default:
                return null;
            }
        } catch (Exception e) {
            ui.showUnknownException(e);
            e.printStackTrace();
        }
        return null;
    }


}

