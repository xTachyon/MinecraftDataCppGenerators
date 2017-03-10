package Utils;


public class Utils {

    public static String[] getNamespaces(String str) {
        return str.split(" ,;");
    }

    public static String toUpperWithUnderscores(String str) {
        return str.toUpperCase().replace(' ', '_').replace('.', '_');
    }
}
