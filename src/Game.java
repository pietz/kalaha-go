import java.io.*;

/**
 * The Game represents the actual Game that is going on between two Players. 
 * It is not a representation of Kalahas logic or rules. See Board.java 
 * for this purpose.
 */
public class Game {

    private Tree tree;
    private int difficulty;
    private boolean logCreated = false;
    private boolean io_error = false;

    /**
     * Gets difficulty.
     *
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return this.getTree().getBoard();
    }

    /**
     * Gets tree.
     *
     * @return the tree
     */
    public Tree getTree() {
        return tree;
    }

    /**
     * Sets tree.
     *
     * @param tree the tree
     */
    public void setTree(Tree tree) {
        this.tree = tree;
    }

    /**
     * Instantiates a new Game.
     *
     * @param difficulty the difficulty or Depth of the Tree
     * @param stones     the number of stones
     * @param pits       the number of pits for each player
     * @param start      defines who starts the game
     * @pre Number of all Stones cannot be greater than the maximum Byte Value
     */
    public Game(int difficulty, int stones, int pits, int start) {
        // Sum of all stones has to be representable by Byte
        assert(pits*stones*2 < Byte.MAX_VALUE);
        this.difficulty = difficulty;
        boolean human;
        switch (start) {
            case -1: human = false; break;
            case 0: human = randomStart(); break;
            case 1: human = true; break;
            default: human = randomStart();
        }
        this.tree = new Tree(difficulty, stones, pits, human);
        this.logCreated = false;
    }

    /**
     * Randomizes who starts the Game
     *
     * @return true if Human starts
     */
    public boolean randomStart() {
        return Math.random() < 0.5;
    }

    /**
     * Is game over boolean.
     *
     * @return the boolean
     */
    public boolean isGameOver() {
        return this.getBoard().gameOver();
    }

    /**
     * Is humans turn boolean.
     *
     * @return the boolean
     */
    public boolean isHumansTurn() {
        return this.getBoard().isHuman();
    }

    /**
     * Executes a Turn based on the specified Index
     *
     * @param i the specified Index
     * @pre i < Length of Next-Array
     */
    public void takeTurn(int i) {
        assert(i < this.tree.getNext().length);
        if (getBoard().getPit(i + getBoard().getOffset()) != 0) {
//            this.writeLog(i);
            this.setTree(this.tree.getNext()[i]);
            this.getTree().buildTree(this.getDifficulty());
//            if (this.isGameOver())
//                this.writeLog(0);
        }
    }

    /**
     * Executes a Turn based on the bestTurn() Method
     */
    public void takeTurn() {
        takeTurn(bestTurn());
    }

    /**
     * Gets the best possible Turn based on the GameTree
     *
     * @return the index of the best possible move between 0 and 5
     */
    public int bestTurn() {
        return this.getTree().getIndexOfBestScore();
    }

    /**
     * Writes a log file during the Game and saves it into a Text File.
     *
     * @param i the
     */
    public void writeLog(int i) {
        if (!io_error) {
            try{
                Writer out = new FileWriter("KalahaGo_Log.txt", logCreated);
                if (!logCreated)
                    out.write(System.getProperty("line.separator"));
                String prefix = "";
                // Call for recursive Print Method
                getTree().printNode(out, prefix);
                out.write("----------");
                out.write(System.getProperty("line.separator"));
                if (!this.isGameOver()) {
                    out.write(Integer.toString(i + ((getBoard().isHuman()) ? 0 : getBoard().getPitList().length/2)));
                    out.write(System.getProperty("line.separator"));
                }
                out.close();
            } catch (IOException e) {
                System.err.println("Error: IO Operation unsuccessful");
                io_error = true;
            }
            if (!logCreated) 
                logCreated = true;
        }
    }
}
