package stud.ntnu.IDATT2001.MappeDel2.view;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class FileNameDialog extends Dialog<String> {
    GridPane gridPane = new GridPane();
    TextField fileName = new TextField();

    /**
     * Constructor that initiates file fetching
     */
    public FileNameDialog() {
        fetchFileName();
    }

    /**
     * Dialog box to specify a file name
     */
    private void fetchFileName(){
        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        gridPane.add(new Label("File name"),0,0);
        gridPane.add(fileName,1,0);

        getDialogPane().setContent(gridPane);
        setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return fileName.getText();
            }
            return null;
        });
    }
}
