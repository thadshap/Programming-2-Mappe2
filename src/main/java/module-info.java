module stud.ntnu.IDATT2001.MappeDel2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;

    opens stud.ntnu.IDATT2001.MappeDel2 to javafx.fxml;
    exports stud.ntnu.IDATT2001.MappeDel2;

    opens stud.ntnu.IDATT2001.MappeDel2.task5 to eclipselink;
    exports stud.ntnu.IDATT2001.MappeDel2.task5;
}