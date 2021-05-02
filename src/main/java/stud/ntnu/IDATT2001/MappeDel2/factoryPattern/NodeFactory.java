package stud.ntnu.IDATT2001.MappeDel2.factoryPattern;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * NodeFactory is a factory class that created different node objects.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class NodeFactory {

    /**
     * Creats a chosen node object
     * @param nodeText name of the requested node object
     * @return a new requested node object
     */
    public static Node getNode(String nodeText){
        if (nodeText == null) return null;
        switch (nodeText){
            case "Button" : return new Button();
            case "ImageView" : return new ImageView();
            case "Label" : return new Label();
            case "MenuBar" : return new MenuBar();
            case "Pane" : return new Pane();
            case "TableView" : return new TableView();
            case "TextField" : return new TextField();
            case "ToolBar" : return new ToolBar();
            case "VBox" : return new VBox();
            default: return null;
        }
    }
}
