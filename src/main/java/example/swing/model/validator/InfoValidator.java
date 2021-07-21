package example.swing.model.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoValidator {
    public static boolean isValidEmail(String email) {

        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern patternEmail = Pattern.compile(EMAIL_PATTERN);
        Matcher matcherEmail = patternEmail.matcher(email);

        return matcherEmail.matches();
    }

    public static boolean isValidName(String name) {
        String NAME_PATTERN = "(\\w+\\s{0,1}){0,4}";

        Pattern patternName = Pattern.compile(NAME_PATTERN);
        Matcher matcherName = patternName.matcher(name);

        return matcherName.matches();
    }
}