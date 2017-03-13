package Cxx;

public enum Type {
    Int("int"),
    Int32("std::int32_t");

    private String name;

    private Type(String s) {
        name = s;
    }

    public String toString() {
        return name;
    }
}
