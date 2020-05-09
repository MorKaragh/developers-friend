package ui.utils;

public class Styles {

    public final static String DEFAULT_COLOR = "#5dade2";
    public static final String TERMINAL = "-fx-control-inner-background:#000000; -fx-text-fill: #ffffff;";
    public static final String TERMINAL_INVALID = "-fx-control-inner-background:#000000; -fx-text-fill:#ffffff; -fx-text-box-border:red;";

    public static String fontSizePt(int size) {
        return "-fx-font-size: " + size + "pt;";
    }

    public static String textFieldBorder(String color) {
        return "-fx-text-box-border: " + color + " ; -fx-focus-color: " + color + " ;";
    }

    public static String backgroundColor(String color) {
        return "-fx-background-color: " + color + ";";
    }

    public static String buttonColor(String color) {
        return "-fx-font: 14 arial; -fx-base: " + color + ";";
    }
}
