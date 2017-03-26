package Cxx;

public enum ClassType {
    Class("class"),
    Struct("struct");

    private String str;

    ClassType(String s) {
        str = s;
    }

    public String toString() {
        return str;
    }
}
