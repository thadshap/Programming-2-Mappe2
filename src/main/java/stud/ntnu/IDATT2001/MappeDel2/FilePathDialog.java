package stud.ntnu.IDATT2001.MappeDel2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class FilePathDialog extends Dialog<PathContent> {

    TextField fileName = new TextField();

    TextField directoryName = new TextField();

    public FilePathDialog() {
        exportDataDialog();
    }

    private void exportDataDialog(){
        GridPane gridPane = new GridPane();

        gridPane.setHgap(30);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);


        Button browse = new Button("Browse");
        browse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final DirectoryChooser dirchooser = new DirectoryChooser();
                File file = dirchooser.showDialog(null);
                String dirName = file.getAbsolutePath(); //.replaceAll("/", Matcher.quoteReplacement("\\\\")); // /Users/Thadsha/Programmering2
                if(file != null){
                    directoryName.setText(file.getAbsolutePath());
                }
            }
        });

        gridPane.add(new Label("File name"),0,0);
        gridPane.add(fileName,1,0);

        gridPane.add(new Label("Directory"),0,1);
        gridPane.add(directoryName,1,1);
        gridPane.add(browse,2,1);

        getDialogPane().setContent(gridPane);

        setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                return new PathContent(directoryName.getText(),fileName.getText());
            }
            return null;
        });
    }
}
