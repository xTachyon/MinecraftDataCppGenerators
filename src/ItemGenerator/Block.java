package ItemGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Block {
    public int id;
    public String displayname;
    public String name;
    public int hardness;
    public int stacksize;
    public boolean diggable;
    public String boundingbox;
    public boolean transparent;
    public int emitlight;
    public int filterlight;

    public Block() {}

    public static Block parse(JSONObject json) {
        Block block = new Block();

        try {
            block.id = json.getInt("id");
            block.displayname = json.getString("displayName");
            block.name = json.getString("name");
            block.hardness = json.getInt("hardness");
            block.stacksize = json.getInt("stackSize");
            block.diggable = json.getBoolean("diggable");
            block.boundingbox = json.getString("boundingBox");
            block.transparent = json.getBoolean("transparent");
            block.emitlight = json.getInt("emitLight");
            block.filterlight = json.getInt("filterLight");
        } catch (JSONException e) {
            System.out.print(e.toString());
            return null;
        }

        return block;
    }

    public static List<Block> parseBlocks(JSONObject json) {
        List<Block> blocks = new ArrayList<>();

        for (Object i : json.getJSONArray("content")){
            JSONObject obj = (JSONObject)i;

            Block block = Block.parse(obj);
            if (block != null) {
                blocks.add(block);
            }
        }

        return blocks;
    }
}
