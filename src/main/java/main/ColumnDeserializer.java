package main;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ColumnDeserializer implements JsonDeserializer<Column> {
    @Override
    public Column deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Column col = new Column();
        //if( !json.isJsonObject() )throw new JsonParseException( "json object required for Column" );
        JsonObject obj = json.getAsJsonObject();

        String typeName = obj.getAsJsonPrimitive("type" ).getAsString();
        col.type = Types.valueOf(typeName);

        col.data = new Data();
        //fill array
        JsonArray arr = obj.getAsJsonArray("data");
        for(int i=0;i<arr.size();i++){
            col.data.add( col.type.cast(arr.get(i).getAsString()) );
        }

        return col;

    }

}
