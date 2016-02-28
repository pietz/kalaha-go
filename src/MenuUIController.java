import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The MenuUIController is the JavaFX Controller for the Start Menu
 * of the Game.
 */
public class MenuUIController {

    private static int difficulty = 3;
    private static int stones = 6;
    private static int start = 0;
    private static int speed = 2;

    public static void setDifficulty(int difficulty) {
        MenuUIController.difficulty = difficulty;
    }

    public static void setStones(int stones) {
        MenuUIController.stones = stones;
    }

    public static void setStart(int start) {
        MenuUIController.start = start;
    }

    public static void setSpeed(int speed) {
        MenuUIController.speed = speed;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static int getStones() {
        return stones;
    }

    public static int getStart() {
        return start;
    }

    public static int getSpeed() {
        return speed;
    }

    @FXML
    private Button btnStart;

    @FXML
    private Button labelDif;

    @FXML
    private Button btnDifEasy;

    @FXML
    private Button btnDifMedium;

    @FXML
    private Button btnDifHard;

    @FXML
    private Button labelNum;

    @FXML
    private Button btnNum4;

    @FXML
    private Button btnNum5;

    @FXML
    private Button btnNum6;

    @FXML
    private Button labelFirst;

    @FXML
    private Button btnFirstHuman;

    @FXML
    private Button btnFirstRandom;

    @FXML
    private Button btnFirstRobot;

    @FXML
    private Button labelSpeed;

    @FXML
    private Button btnSpeedSlow;

    @FXML
    private Button btnSpeedMedium;

    @FXML
    private Button btnSpeedFast;

    @FXML
    private Pane labelLogo;

    @FXML
    void handleBtnStart(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("gameUI.fxml"));
            stage.setScene(new Scene(root, 700, 300));
            stage.setMinWidth(700);
            stage.setMinHeight(300);
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    void handleBtnDifEasy(ActionEvent event) {
        difficulty = 1;
        selectBtn(0, true);
        selectBtn(1, false);
        selectBtn(2, false);
    }

    @FXML
    void handleBtnDifMedium(ActionEvent event) {
        difficulty = 3;
        selectBtn(0, false);
        selectBtn(1, true);
        selectBtn(2, false);
    }

    @FXML
    void handleBtnDifHard(ActionEvent event) {
        difficulty = 5;
        selectBtn(0, false);
        selectBtn(1, false);
        selectBtn(2, true);
    }

    @FXML
    void handleBtnFirstHuman(ActionEvent event) {
        start = 1;
        selectBtn(6, true);
        selectBtn(7, false);
        selectBtn(8, false);
    }

    @FXML
    void handleBtnFirstRandom(ActionEvent event) {
        start = 0;
        selectBtn(6, false);
        selectBtn(7, true);
        selectBtn(8, false);
    }

    @FXML
    void handleBtnFirstRobot(ActionEvent event) {
        start = -1;
        selectBtn(6, false);
        selectBtn(7, false);
        selectBtn(8, true);
    }

    @FXML
    void handleBtnNum4(ActionEvent event) {
        stones = 4;
        selectBtn(3, true);
        selectBtn(4, false);
        selectBtn(5, false);
    }

    @FXML
    void handleBtnNum5(ActionEvent event) {
        stones = 5;
        selectBtn(3, false);
        selectBtn(4, true);
        selectBtn(5, false);
    }

    @FXML
    void handleBtnNum6(ActionEvent event) {
        stones = 6;
        selectBtn(3, false);
        selectBtn(4, false);
        selectBtn(5, true);
    }

    @FXML
    void handleBtnSpeedFast(ActionEvent event) {
        speed = 1;
        selectBtn(9, false);
        selectBtn(10, false);
        selectBtn(11, true);
    }

    @FXML
    void handleBtnSpeedMedium(ActionEvent event) {
        speed = 2;
        selectBtn(9, false);
        selectBtn(10, true);
        selectBtn(11, false);
    }

    @FXML
    void handleBtnSpeedSlow(ActionEvent event) {
        speed = 4;
        selectBtn(9, true);
        selectBtn(10, false);
        selectBtn(11, false);
    }

    public void selectBtn(int index, boolean highlight) {
        String blue = "-fx-background-color: #000000,  linear-gradient(#7ebcea, #2f4b8f), linear-gradient(#395cab, #223768);";
        String green = "-fx-background-color: #000000, linear-gradient(#8affa9, #2f8f82), linear-gradient(#41c487, #2b8258);";
        String red = "-fx-background-color: #000000, linear-gradient(#ff8acc, #a83859), linear-gradient(#de4976, #9c3353);";
        String select = (highlight) ? green : blue;

        switch (index) {
            case 0: btnDifEasy.setStyle(select); break;
            case 1: btnDifMedium.setStyle(select); break;
            case 2: btnDifHard.setStyle(select); break;
            case 3: btnNum4.setStyle(select); break;
            case 4: btnNum5.setStyle(select); break;
            case 5: btnNum6.setStyle(select); break;
            case 6: btnFirstHuman.setStyle(select); break;
            case 7: btnFirstRandom.setStyle(select); break;
            case 8: btnFirstRobot.setStyle(select); break;
            case 9: btnSpeedSlow.setStyle(select); break;
            case 10: btnSpeedMedium.setStyle(select); break;
            case 11: btnSpeedFast.setStyle(select);
        }
    }

}

