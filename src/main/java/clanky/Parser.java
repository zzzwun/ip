package clanky;

public class Parser {
    public static String[] parse(String input) {
        return input.trim().split("\\s+", 2);
    }
}
