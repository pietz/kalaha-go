import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * The GameUIController is the JavaFX Controller for the Playing
 * Board of Kalaha.
 */
public class GameUIController implements Initializable, TimerListener {

    private Game game;
    private Button[] btnPit;
    private Timer timer;
    private int chosenIndex;
    private int currentIndex;
    private boolean human;
    private boolean hint = false;
    private boolean animationActive = false;
    private final String blue = "-fx-background-color: #000000,  linear-gradient(#7ebcea, #2f4b8f), linear-gradient(#395cab, #223768);";
    private final String green = "-fx-background-color: #000000, linear-gradient(#8affa9, #2f8f82), linear-gradient(#41c487, #2b8258);";
    private final String red = "-fx-background-color: #000000, linear-gradient(#ff8acc, #a83859), linear-gradient(#de4976, #9c3353);";

    @FXML
    private Button btnPit0;

    @FXML
    private Button btnPit1;

    @FXML
    private Button btnPit2;

    @FXML
    private Button btnPit3;

    @FXML
    private Button btnPit4;

    @FXML
    private Button btnPit5;

    @FXML
    private Button btnPit6;

    @FXML
    private Button btnPit7;

    @FXML
    private Button btnPit8;

    @FXML
    private Button btnPit9;

    @FXML
    private Button btnPit10;

    @FXML
    private Button btnPit11;

    @FXML
    private Button btnPit12;

    @FXML
    private Button btnPit13;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnHint;

    @FXML
    private Label textOut;

    @FXML
    void handleBtn0Click(MouseEvent event) {
        play(event, 0);
    }

    @FXML
    void handleBtn0Press() {
        markBtn(0);
    }

    @FXML
    void handleBtn0Hover(MouseEvent event) {
        hoverBtn(0);
    }

    @FXML
    void handleBtn1Click(MouseEvent event) {
        play(event, 1);
    }

    @FXML
    void handleBtn1Press(MouseEvent event) {
        markBtn(1);
    }

    @FXML
    void handleBtn1Hover(MouseEvent event) {
        hoverBtn(1);
    }

    @FXML
    void handleBtn2Click(MouseEvent event) {
        play(event, 2);
    }

    @FXML
    void handleBtn2Press(MouseEvent event) {
        markBtn(2);
    }

    @FXML
    void handleBtn2Hover(MouseEvent event) {
        hoverBtn(2);
    }

    @FXML
    void handleBtn3Click(MouseEvent event) {
        play(event, 3);
    }

    @FXML
    void handleBtn3Press(MouseEvent event) {
        markBtn(3);
    }

    @FXML
    void handleBtn3Hover(MouseEvent event) {
        hoverBtn(3);
    }

    @FXML
    void handleBtn4Click(MouseEvent event) {
        play(event, 4);
    }

    @FXML
    void handleBtn4Press(MouseEvent event) {
        markBtn(4);
    }

    @FXML
    void handleBtn4Hover(MouseEvent event) {
        hoverBtn(4);
    }

    @FXML
    void handleBtn5Click(MouseEvent event) {
        play(event, 5);
    }

    @FXML
    void handleBtn5Press(MouseEvent event) {
        markBtn(5);
    }

    @FXML
    void handleBtn5Hover(MouseEvent event) {
        hoverBtn(5);
    }

    @FXML
    void handleBtnRelease(MouseEvent event) {
        if (!animationActive) {
            updatePits();
        }
    }

    @FXML
    void handleBtnExit(MouseEvent event) {
        if (!animationActive && !game.isGameOver()) {
            textOut.setText("It's your Turn");
        }
    }

    @FXML
    void handleBtnHint(MouseEvent event) {
        if (!animationActive) {
            hint = !hint;
            if (hint) {
                btnHint.setStyle(green);
            } else {
                btnHint.setStyle(red);
            }
            updatePits();
        }
    }

    @FXML
    void handleBtnReset(MouseEvent event) {
        if (animationActive) {
            timer.stop();
        }
        MenuUIController.setDifficulty(3);
        MenuUIController.setSpeed(2);
        MenuUIController.setStart(0);
        MenuUIController.setStones(6);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("menuUI.fxml"));
            stage.setScene(new Scene(root, 700, 300));
            stage.setMinWidth(600);
            stage.setMinHeight(300);
            stage.show();
        } catch (IOException e) {
        }
    }

    public void markBtn(int index) {
        if(!animationActive && game.getBoard().getPit(index) != 0) {
            btnPit[game.getBoard().indexOfLastStone(index)].setStyle(red);
        }
    }

    public void hoverBtn(int i) {
        if (!animationActive && hint && game.getBoard().getPit(i) != 0) {
            textOut.setText(Byte.toString(game.getTree().getNext()[i].getRating()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnPit = new Button[14];
        btnPit[0] = btnPit0;
        btnPit[1] = btnPit1;
        btnPit[2] = btnPit2;
        btnPit[3] = btnPit3;
        btnPit[4] = btnPit4;
        btnPit[5] = btnPit5;
        btnPit[6] = btnPit6;
        btnPit[7] = btnPit7;
        btnPit[8] = btnPit8;
        btnPit[9] = btnPit9;
        btnPit[10] = btnPit10;
        btnPit[11] = btnPit11;
        btnPit[12] = btnPit12;
        btnPit[13] = btnPit13;
        this.game = new Game(MenuUIController.getDifficulty(), MenuUIController.getStones(), 6,
                MenuUIController.getStart());
        this.timer = new Timer(MenuUIController.getSpeed(), this);
        play();
    }

    public void play() {
        textOut.setText("It's your Turn");
        updatePits();
        if (!game.isHumansTurn() && !game.isGameOver()) {
            textOut.setText("It's Robots Turn");
            int offset = btnPit.length/2;
            this.human = game.isHumansTurn();
            chosenIndex = game.bestTurn() + offset;
            currentIndex = chosenIndex;
            timer.go(game.getBoard().getPit(chosenIndex));
            game.takeTurn(chosenIndex - offset);
        }

        if (game.isGameOver()) {
            if (game.getBoard().getScore() > 0) {
                textOut.setText("You won!");
            } else if (game.getBoard().getScore() < 0) {
                textOut.setText("You lost!");
            } else {
                textOut.setText("It's a tie!");
            }
        }
    }

    public void play(MouseEvent event, int i) {
        if (!animationActive && event.getClickCount() == 2) {
            btnPit[game.bestTurn()].setStyle(green);
            textOut.setText("It's your Turn");
            this.human = game.isHumansTurn();
            this.chosenIndex = i;
            this.currentIndex = i;
            this.timer.go(game.getBoard().getPit(i));
            this.game.takeTurn(i);
        }
    }

    public void updatePits() {
        int offset = btnPit.length/2;
        for (int i = 0; i < offset; ++i) {
            btnPit[i].setText(Integer.toString(game.getBoard().getPit(i)));
            btnPit[i+offset].setText(Integer.toString(game.getBoard().getPit(i+offset)));
            btnPit[i].setStyle(green);
            btnPit[i+offset].setStyle(blue);
        }
        if (hint && !game.isGameOver() && game.isHumansTurn()) {
            btnPit[game.bestTurn()].setStyle(red);
        }
    }

    @Override
    public void timerStarted() {
        btnPit[chosenIndex].setText(Integer.toString(0));
        animationActive = true;
    }

    @Override
    public void timerFinished() {
        animationActive = false;
        play();
    }

    @Override
    public void timerUpdate() {
        currentIndex = game.getBoard().nextPit(currentIndex, this.human);
        // Button text has to be parsed to int added by one and converted back to String to set the Button
        btnPit[currentIndex].setText(Integer.toString(Integer.parseInt(btnPit[currentIndex].getText())+1));
    }

}
