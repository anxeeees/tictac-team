module com.tictactoe.tictacteam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.tictactoe.tictacteam to javafx.fxml;
    exports com.tictactoe.tictacteam;
}