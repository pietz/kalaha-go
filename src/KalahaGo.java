import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 * The Main Class starts the applications and manages a Game that is launched
 * in console mode.
 */
public class KalahaGo extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Font.loadFont(KalahaGo.class.getResource("Futura-Medium.ttf").toExternalForm(), 12);
        Font.loadFont(KalahaGo.class.getResource("Futura-CondensedMedium.ttf").toExternalForm(), 12);
        Parent root = FXMLLoader.load(getClass().getResource("menuUI.fxml"));
        stage.setTitle("Kalaha");
        stage.setScene(new Scene(root, 700, 300));
        stage.setMinWidth(700);
        stage.setMinHeight(300);
        stage.getIcons().add(new Image("icon.png"));
        stage.show();

    }


    public static void main(String[] args) {
        switch (args.length) {
            case 0: launch(args); break;
            case 1: playCommandLine(args[0].split(",")); break;
            default: System.err.println("Error: Multiple Arguments are not allowed");
        }
    }

    /**
     * Method for Playing in the Command Line
     *
     * @param array the array of Input Arguments
     */
    public static void playCommandLine(String[] array) {
        try {
            Game game = new Game(3, 6, 6, 1);
            for (String i : array) {
                int number = Integer.parseInt(i);
                if (number >= 0 && number < game.getBoard().humanPit() && game.getBoard().getPit(number) != 0) {
                    game.takeTurn(number);
                } else {
                    throw new NumberFormatException();
                }
                while (!game.isHumansTurn()) {
                    game.takeTurn();
                }
            }
            game.getBoard().printOneLiner();
        } catch (NumberFormatException e) {
            System.err.println("Error: At least one of the parameters was invalid");
        }
    }
}
