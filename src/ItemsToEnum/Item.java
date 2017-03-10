package ItemsToEnum;

import Utils.Utils;
import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Item {
    private static final String idKey= "id";
    private static final String displaynameKey = "displayName";
    private static final String stacksizeKey= "stackSize";
    private static final String nameKey = "name";

    public int id;
    public String displayname;
    public int stacksize;
    public String name;
    public String writename;

    public Item() {}

    public String formatItemName() {
        return WordUtils.capitalize(name, " _".toCharArray()).replace("_", "");
    }

    public static Item parse(JSONObject json) {
        Item item = new Item();

        try {
            item.id = json.getInt(idKey);
            item.displayname = json.getString(displaynameKey);
            item.stacksize = json.getInt(stacksizeKey);
            item.name = json.getString(nameKey);
            item.writename = item.formatItemName();
        }
        catch (JSONException e) {
            System.out.println(e);
            return null;
        }

        return item;
    }
}
