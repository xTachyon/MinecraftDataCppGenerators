package ItemsToEnum;


import Cxx.CppFile;
import Cxx.IncludeFileType;
import Cxx.Type;
import Utils.Pair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ItemsToEnumGenerator {
    private JSONObject json;
    private String[] namespaces;
    private Item[] items;
    private StringBuilder str;
    private CppFile header;

    public ItemsToEnumGenerator(JSONObject json, String[] namespaces) {
        this.json = json;
        this.namespaces = namespaces;
    }

    public void generate() {
        generateList();
        generateString();
    }

    public String getResult() {
        return str.toString();
    }

    private void generateList() {
        List<Item> items = new ArrayList<>();

        for (Object i : json.getJSONArray("content")){
            JSONObject obj = (JSONObject)i;

            Item item = Item.parse(obj);
            if (item != null) {
                items.add(item);
            }
        }

        this.items = items.toArray(new Item[items.size()]);
    }

    private void generateString() {
        str = new StringBuilder();
        header = new CppFile(str, namespaces, "Items");

        header.writeGuardBegin();
        header.writeInclude("cstdint", IncludeFileType.Standard);
        header.writeNewline();
        header.writeNamespacesBegin();
        header.writeStructBegin("ItemTypeEnum");

        for ( Item i : items) {
            header.writeStaticConstexpr(Type.Int, i.writename, String.valueOf(i.id));
        }

        header.writeStructEnd();
        header.writeNamespacesEnd();
        header.writeGuardEnd();
    }
}
