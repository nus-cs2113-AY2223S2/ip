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

import duke.item.Item;
import duke.todo.Todo;
import duke.deadline.Deadline;
import duke.event.Event;

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

            // Check the class type and write accordingly
            if (item instanceof Deadline) {
                out.name("datemark").value(((Deadline) item).getDate());
            } else if (item instanceof Event) {
                out.name("from").value(((Event) item).getFromDate());
                out.name("to").value(((Event) item).getToDate());
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

        private Item parseItem(HashMap<String, String> itemMap) throws IOException {
            Item item = null;

            // Load all the variables that all Items have
            String type = itemMap.get("type");
            String description = itemMap.get("description");
            boolean isDone = Boolean.parseBoolean(itemMap.get("isDone"));

            // Check the class type and load their variables accordingly
            switch (type) {
                case "Todo": {
                    item = new Todo(description, isDone);
                    break;
                }
                case "Deadline": {
                    LocalDateTime datemark = LocalDateTime.parse(itemMap.get("datemark"));
                    item = new Deadline(description, datemark, isDone);
                    break;
                }
                case "Event": {
                    LocalDateTime from = LocalDateTime.parse(itemMap.get("from"));
                    LocalDateTime to = LocalDateTime.parse(itemMap.get("to"));
                    item = new Event(description, from, to, isDone);
                    break;
                }
                default:
                    // When an unknown class type is received
                    throw new IOException();
            }

            return item;
        }
    };
    
}
