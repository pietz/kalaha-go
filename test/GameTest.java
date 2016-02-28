import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by pietz on 15/10/15.
 */
public class GameTest {

    @Test
    public void testBuildTreeFromGame() throws Exception {

        Game game = new Game(5, 6, 6, 1);
        assertEquals("[6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]", Arrays.toString(game.getBoard().getPitList()));
    }
    
    @Test
    public void testTakeTurnHuman() throws Exception {

        Game game = new Game(5, 6, 6, 1);
        assertEquals("[6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]", Arrays.toString(game.getBoard().getPitList()));
        game.takeTurn(0);
        game.takeTurn(1);
        assertEquals("[0, 0, 8, 8, 8, 8, 2, 7, 7, 6, 6, 6, 6, 0]", Arrays.toString(game.getBoard().getPitList()));
    }

    @Test
    public void testTakeTurnRobot() throws Exception {

        Game game = new Game(5, 6, 6, 1);
        assertEquals("[6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]", Arrays.toString(game.getBoard().getPitList()));
        game.takeTurn();
        game.takeTurn();
        assertEquals("[0, 0, 8, 8, 8, 8, 2, 7, 7, 6, 6, 6, 6, 0]", Arrays.toString(game.getBoard().getPitList()));
    }

    @Test
    public void simulateGame1() throws Exception {

        Game g = new Game(1, 6, 6, 1);
        int moveCounter = 0;
        while (!g.getBoard().gameOver()) {
            g.takeTurn();
            moveCounter++;
        }
        assertEquals(6, g.getBoard().getScore());
        assertEquals(39, moveCounter);
    }

    @Test
    public void simulateGame3() throws Exception {

        Game g = new Game(3, 6, 6, 1);
        int moveCounter = 0;
        while (!g.getBoard().gameOver()) {
            g.takeTurn();
            moveCounter++;
        }
        assertEquals(-18, g.getBoard().getScore());
        assertEquals(37, moveCounter);
    }

    @Test
    public void simulateGame5() throws Exception {

        Game g = new Game(5, 6, 6, 1);
        int moveCounter = 0;
        while (!g.getBoard().gameOver()) {
            g.takeTurn();
            moveCounter++;
        }
        assertEquals(-14, g.getBoard().getScore());
        assertEquals(66, moveCounter);
    }

    @Test
    public void simulateGameOtherSizes1() throws Exception {

        Game g = new Game(3, 2, 12, 1);
        int moveCounter = 0;
        while (!g.getBoard().gameOver()) {
            g.takeTurn();
            moveCounter++;
        }
        assertEquals(-2, g.getBoard().getScore());
        assertEquals(31, moveCounter);
    }

    @Test
    public void simulateGameOtherSizes2() throws Exception {

        Game g = new Game(3, 20, 3, -1);
        int moveCounter = 0;
        while (!g.getBoard().gameOver()) {
            g.takeTurn();
            moveCounter++;
        }
        assertEquals(-16, g.getBoard().getScore());
        assertEquals(50, moveCounter);
    }

    @Test
    public void simulateGameOtherSizes3() throws Exception {

        Game g = new Game(1, 1, 1, 1);
        int moveCounter = 0;
        while (!g.getBoard().gameOver()) {
            g.takeTurn();
            moveCounter++;
        }
        assertEquals(0, g.getBoard().getScore());
        assertEquals(1, moveCounter);
    }
}