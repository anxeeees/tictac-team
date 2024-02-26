module com.tictactoe.tictacteam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tictactoe.tictacteam to javafx.fxml;
    exports com.tictactoe.tictacteam;
}