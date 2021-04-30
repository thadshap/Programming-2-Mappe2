module stud.ntnu.IDATT2001.MappeDel2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;

    opens stud.ntnu.IDATT2001.MappeDel2 to javafx.fxml;
    exports stud.ntnu.IDATT2001.MappeDel2;
}