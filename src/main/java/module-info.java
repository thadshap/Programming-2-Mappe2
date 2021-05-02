module stud.ntnu.IDATT2001.MappeDel2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;

    opens stud.ntnu.IDATT2001.MappeDel2 to javafx.fxml;
    exports stud.ntnu.IDATT2001.MappeDel2;
}