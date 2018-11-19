package main;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ColumnDeserializer implements JsonDeserializer<Column> {
    @Override
    public Column deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();

        String typeName = obj.getAsJsonPrimitive("type" ).getAsString();
        Column col = new Column(typeName);
        //fill array
        JsonArray arr = obj.getAsJsonArray("data");
        try {
            for (int i = 0; i < arr.size(); i++) {
                col.data.add(col.type.cast(arr.get(i).getAsString()));
            }
        }catch (DBError e){
            throw new JsonParseException(e.getMessage(),e);
        }
        return col;

    }

}
