package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlModifier {
    public static String extractUrl(String message) {
        String regex = "(?i)\\b(https?://)([a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5})(:[0-9]{1,5})?(/([\\w\\-\\.!~#?&=+\\*\\'(),;/:@\\[\\]%\\$]+))*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        StringBuilder sb = new StringBuilder();
        boolean edited = false;
        while (matcher.find() && !edited) {
            String protocol = matcher.group(1);
            String domain = matcher.group(2);
            String path = matcher.group(5);
            String modifiedURL = protocol + "vx" + domain + path;
            edited = true;
            matcher.appendReplacement(sb, modifiedURL);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}