package tusky.storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import tusky.constants.DateTime;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class DateAdapter implements JsonSerializer<LocalDate> {
    /**
     * Convert a LocalDate object to Json using our specific format.
     * @param src the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context context for serialization
     * @return JsonElement, the converted Json object.
     */
    @Override
    public JsonElement serialize (LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DateTime.DATE_STORE_FORMAT)));
    }
}