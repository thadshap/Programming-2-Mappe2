package stud.ntnu.IDATT2001.MappeDel2.factoryPattern;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.Locale;

/**
 * NodeFactory is a factory class that created different node objects.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class NodeFactory {

    /**
     * Creates a chosen node object
     * @param nodeText name of the requested node object
     * @return a new requested node object
     */
    public static Node getNode(String nodeText){
        if (nodeText == null) return null;
        switch (nodeText.toLowerCase()){
            case "button" : return new Button();
            case "imageview" : return new ImageView();
            case "label" : return new Label();
            case "menubar" : return new MenuBar();
            case "pane" : return new Pane();
            case "tableview" : return new TableView();
            case "textfield" : return new TextField();
            case "toolbar" : return new ToolBar();
            case "vbox" : return new VBox();
            default: return null;
        }
    }
}
