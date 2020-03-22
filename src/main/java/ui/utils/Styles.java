package ui.utils;

public class Styles {

    public static String fontSizePt(int size) {
        return "-fx-font-size: " + size + "pt;";
    }

    public static String textFieldBorder(String color) {
        return "-fx-text-box-border: " + color + " ; -fx-focus-color: " + color + " ;";
    }

    public static String backgroundColor(String color) {
        return "-fx-background-color: " + color + ";";
    }
}
