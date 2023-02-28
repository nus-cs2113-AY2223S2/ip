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
    @Override
    public JsonElement serialize (LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(DateTimeFormatter.ofPattern(DateTime.DATE_STORE_FORMAT)));
    }
}