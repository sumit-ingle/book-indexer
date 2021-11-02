package constant;

public class Regex {
    public static final String ANY_WORD;

    static {
        String letterFromAnyLanguage = "\\pL";
        String alphanumericCharacter = "\\w";
        String singleQuoteAndDash = "'-";
        ANY_WORD = "[" + alphanumericCharacter + letterFromAnyLanguage + singleQuoteAndDash + "]+";
    }
}
