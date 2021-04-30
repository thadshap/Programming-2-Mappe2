package stud.ntnu.IDATT2001.MappeDel2.factoryPattern;

import javafx.scene.Node;

public class NodeFactory {
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
