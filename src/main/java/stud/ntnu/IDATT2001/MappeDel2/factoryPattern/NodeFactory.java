package stud.ntnu.IDATT2001.MappeDel2.factoryPattern;

public class NodeFactory {
    public static Node getNode(String nodeText){
        if (nodeText == null) return null;
        switch (nodeText){
            case "Button" : return new Button();
            case "ImageView" : return new ImageView();
            case "Label" : return new Label();
            case "Menu" : return new Menu();
            case "MenuBar" : return new MenuBar();
            case "MenuItem" : return new MenuItem();
            case "Pane" : return new Pane();
            case "SeparatorMenuItem" : return new SeparatorMenuItem();
            case "TableColumn" : return new TableColumn();
            case "TableView" : return new TableView();
            case "TextField" : return new TextField();
            case "ToolBar" : return new ToolBar();
            case "VBox" : return new VBox();
            default: return null;
        }
    }
}
