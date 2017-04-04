package ItemGenerator;

import Cxx.IConstChar;

public class ItemConstCharGen implements IConstChar {
    @Override
    public int getConstCharID(Object o) {
        if (o instanceof Item) {
            return ((Item)o).id;
        }
        return ((Block)o).id;
    }

    @Override
    public String getConstCharValue(Object o) {
        return "";
    }
}
