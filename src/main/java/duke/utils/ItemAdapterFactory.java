package duke.utils;

import java.util.HashMap;
import java.io.IOException;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import duke.deadline.Deadline;
import duke.event.Event;
import duke.item.Item;
import duke.todo.Todo;

public class ItemAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        if(Item.class.isAssignableFrom(typeToken.getRawType())) {
            return (TypeAdapter<T>) itemTypeAdapter;          
        }
        return null;
    }

    private TypeAdapter<Item> itemTypeAdapter = new TypeAdapter<Item>() {
        @Override
        public void write(JsonWriter out, Item item) throws IOException {
            serializeItem(out, item);
        }

        @Override
        public Item read(JsonReader in) throws IOException {
            HashMap<String, String> itemMap = mapJsonObject(in);
            Item item = parseItem(itemMap);

            return item;
        }

        private void serializeItem(JsonWriter out, Item item) throws IOException {
            out.beginObject();

            // Write all the variables that all Items have
            out.name("type").value(item.getClass().getSimpleName());
            out.name("description").value(item.getDescription());
            out.name("isDone").value(Boolean.toString(item.getDone()));

            if (item instanceof Deadline) {
                out.name("datemark").value(((Deadline) item).getDate());
            } else if (item instanceof Event) {
                
            }

            out.endObject();
        }

        private HashMap<String, String> mapJsonObject(JsonReader in) throws IOException {
            HashMap<String, String> itemMap = new HashMap<String, String>();

            // Dump all json property into a Map
            in.beginObject();
            while (in.hasNext()) {
                itemMap.put(in.nextName(), in.nextString());
            }
            in.endObject();

            return itemMap;
        }

        private Item parseItem(HashMap<String, String> itemMap) {
            Item item = null;

            String type = itemMap.get("type");
            String description = itemMap.get("description");
            boolean isDone = Boolean.parseBoolean(itemMap.get("isDone"));

            switch (type) {
                case "Todo": {
                    item = new Todo(description, isDone);
                    break;
                }
                case "Deadline": {
                    item = new Deadline(description, LocalDateTime.parse(itemMap.get("datemark")), isDone);
                    break;
                }
                case "Event": {
                    break;
                }
                default:
                    //give some err here
            }

            return item;
        }
    };
    
}
