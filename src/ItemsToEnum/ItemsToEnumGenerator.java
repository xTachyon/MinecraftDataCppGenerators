package ItemsToEnum;


import Cxx.CppFile;
import Cxx.IncludeFileType;
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

        Pair<String, String>[] pairs = new Pair[items.length];
        int i = 0;
        for (Item item : items) {
            pairs[i] = new Pair<String, String>(item.writename, String.valueOf(item.id));

            ++i;
        }

        header.writeEnum("Items", "std::uint32_t", pairs);

        header.writeNamespacesEnd();
        header.writeGuardEnd();
    }
}
