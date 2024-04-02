module com.tictactoe.tictacteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.persistence;


    opens com.tictactoe.tictacteam to javafx.fxml;
    exports com.tictactoe.tictacteam;
}