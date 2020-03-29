package ui.dialogs.savedialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.abstractions.HasData;
import ui.abstractions.Validatable;
import ui.utils.Styles;

public abstract class AbstractSavingDialog<T> extends BorderPane {

    private final HasData<T> layout;
    private final OnSaveAction<T> onSaveListener;
    private Button saveButton;
    private Button closeButton;
    private InnerListener<T> innerListener;

    public AbstractSavingDialog(AbstractSavingDialogLayout<T> layout, String headerText, OnSaveAction<T> onSaveListener) {
        this.layout = layout;
        this.onSaveListener = onSaveListener;
        saveButton = createSaveButton();
        closeButton = createCloseButton();
        HBox buttonsLayout = createButtonsLayout();
        Label label = createHeaderLabel(headerText);
        setCenter(layout);
        setBottom(buttonsLayout);
        setTop(label);
    }

    private Label createHeaderLabel(String headerText) {
        Label label = new Label(headerText);
        label.setStyle(Styles.fontSizePt(15));
        label.setPadding(new Insets(10, 5, 20, 5));
        return label;
    }

    private HBox createButtonsLayout() {
        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(10);
        buttonsLayout.getChildren().addAll(saveButton, closeButton);
        buttonsLayout.setPadding(new Insets(20, 10, 10, 0));
        buttonsLayout.setAlignment(Pos.BOTTOM_RIGHT);
        return buttonsLayout;
    }

    public void openDialog(T value) {
        Stage stage = new Stage();
        layout.mountData(value);
        setInnerListener(new InnerListener<T>() {
            @Override
            public void onSave(T host) {
                if (onSaveListener != null) {
                    onSaveListener.onSave(host);
                    stage.close();
                }
            }
            @Override
            public void onClose() {
                stage.close();
            }
        });
        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.show();
    }

    private Button createCloseButton() {
        Button closeButton = new Button("close");
        closeButton.setTextFill(Color.RED);
        closeButton.setOnAction(actionEvent -> {
            if (innerListener != null) {
                innerListener.onClose();
            }
        });
        return closeButton;
    }

    private Button createSaveButton() {
        Button saveButton = new Button("save");
        saveButton.setTextFill(Color.BLUE);
        saveButton.setOnAction(actionEvent -> {
            if (layout instanceof Validatable && !((Validatable) layout).isValid()) {
                return;
            };
            if (innerListener != null) {
                innerListener.onSave(layout.gatherData());
            }
        });
        return saveButton;
    }

    private void setInnerListener(InnerListener<T> innerListener) {
        this.innerListener = innerListener;
    }

    public interface OnSaveAction<T>{
        void onSave(T host);
    }

    private interface InnerListener<T> {
        void onSave(T t);
        void onClose();
    }


}
