package stud.ntnu.IDATT2001.MappeDel2.factoryPattern;

import javafx.scene.Node;

/**
 * The GUI elements which could be used for this application were stored in separate classes, and they inherited from the Node class. Instances of those objects are created
 * central in this NodeFactory class.
 *
 * @author Thadshajini
 * @version 2020-05-05
 */

public class NodeFactory {

    /**
     * Gitt ønsket Gui element returneres korresponderende objeckt fra vårt interne fabrikk klasse
     * @param nodeText er navnet på det gui elementet som ønskes å bruke
     * @return instans av det utvalgte gui objektet
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
