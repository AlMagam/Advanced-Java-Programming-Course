module gaborets.lab3_fx {
    exports gaborets.lab3_fx.taskthree;
    exports gaborets.lab3_fx.taskvear;
    exports gaborets.lab3_fx.taskone;
    exports gaborets.lab3_fx.tasktwo;

    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;

    opens gaborets.lab3_fx.taskone to xstream;
    opens gaborets.lab3_fx to javafx.fxml;
}