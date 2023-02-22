package duke.filemanager;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Custom gson serializer for LocalDateTime
 */
class LocalDateAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    /**
     * Deserializes the formatted LocalDateTime from json file
     *
     * @param jsonElement                the json element to deserialize
     * @param jsonDeserializationContext context for deserialization
     * @return the LocalDateTime parsed from json element
     * @throws JsonParseException occurs when invalid json format is given
     */
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement,
                                     Type type,
                                     JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * @param localDateTime the localDateTime to be serialized
     * @return the serialized json element from localDateTime
     */
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(
                localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
    }
}
