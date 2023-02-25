//to parse input by user
public class Parser {
    /**
     * Returns boolean that reflects if target is a
     * substring of reference
     *
     * @param reference String used to test if the target substring exists.
     * @param target String used to test if it is found in the reference substring.
     * @return Boolean that reflects if target is a substring of reference
     */
    public static boolean parse (String reference, String target) {
        return reference.contains(target);
    }
}
