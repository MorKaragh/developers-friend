package ui.terminal;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class TerminalView extends Group {

    public TerminalView(){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.BLACK);
        getChildren().add(rectangle);
    }

    public void print(String toPrint) {
        Text text = new Text(toPrint);
        text.setFill(Color.WHITE);
        getChildren().add(text);
    }

}
