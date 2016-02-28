import java.io.*;

/**
 * The Tree contains all possible Moves up to a certain depth into the future. 
 * It's used by the Robot to determine the next Move.
 */
public final class Tree {

    private final Board board;
    private final Tree[] next;
    private byte rating;

    /**
     * Getter for the Board
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for the Array of Next-Elements
     *
     * @return the Tree Array
     */
    public Tree[] getNext() {
        return next;
    }

    /**
     * Sets The Next Array
     *
     * @param index the index of the Tree element
     * @param element the element itself
     */
    public void setNextElement(int index, Tree element) {
        this.next[index] = element;
    }

    /**
     * Getter for the Rating of each Tree Node
     *
     * @return the rating
     */
    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public Tree(int depth, int stones, int pits, boolean human) {
        this.board = new Board(stones, pits, human);
        this.next = new Tree[pits];
        this.buildTree(depth);
    }

    /**
     * Instantiates a new GameTree based on a certain Board and the chosen Difficulty
     *
     * @param b     the Board on which the Tree should be build
     * @param depth the depth of the Tree stands in direct correlation to the chosen Difficulty
     */
    public Tree(Board b, int depth) {
        this.board = new Board(b);
        this.next = new Tree[this.board.humanPit()];
        this.buildTree(depth);
    }

    /**
     * Builds a tree with a specified Depth and also get sets the correct Ratings for each element
     *
     * @param depth The Depth of the Tree which also represents its Difficulty
     * @return The Rating of the current Node
     */
    public byte buildTree(int depth) {
        if (depth > 0) {
            int offset = (this.getBoard().isHuman()) ? 0 : this.getNext().length+1;
            // Sets the current score to the smallest or biggest byte number, to make sure its being overwritten
            setRating(getBoard().isHuman() ? Byte.MIN_VALUE : Byte.MAX_VALUE);
            for (int i = 0; i < this.getNext().length; ++i) {
                if (!this.getBoard().isEmpty(i + offset)) {
                    if (this.getNext()[i] == null) {
                        setNextElement(i, new Tree(this.getBoard().makeMove(i+offset), 0));
                    }
                    // Saves the better of the two scores and starts the recursive call
                    setRating(betterScore(this.rating, this.getNext()[i].buildTree(depth-(playersSwitched(i) ? 1:0))));
                }
            }
            // If true, no Rating have been found and the Bottom of the tree is reached
            if (getRating() == Byte.MIN_VALUE || getRating() == Byte.MAX_VALUE)
                setRating(getBoard().getScore());
        } else {
            setRating(getBoard().getScore());
        }
        return getRating();
    }

    public int getIndexOfBestScore() {
        // Sets the current score to the smallest or biggest byte number, to make sure its being overwritten
        byte bestScore = ((this.getBoard().isHuman()) ? Byte.MIN_VALUE : Byte.MAX_VALUE);
        int indexOfBestScore = 0;
        byte tmp;
        for (int i = 0; i < this.getNext().length; ++i) {
            if (this.getNext()[i] != null) {
                tmp = bestScore;
                bestScore = betterScore(bestScore, this.getNext()[i].getRating());
                if (tmp != bestScore) indexOfBestScore = i;
            }
        }
        // is between 0 and 5
        return indexOfBestScore;
    }

    /**
     * Calculates the better of the two scores depending on whose turn it is.
     *
     * @param s1    the first score
     * @param s2    the second score
     * @return the better of the two scores
     */
    public byte betterScore(byte s1, byte s2) {
        if (this.getBoard().isHuman()) {
            return (s2 > s1) ? s2 : s1;
        } else {
            return (s2 < s1) ? s2 : s1;
        }
    }

    /**
     * Checks if the bottom of the Tree is reached
     *
     * @return true if the bottom of the Tree is reached
     */
    public boolean isBottom() {
        for (int i = 0; i < this.getNext().length; ++i) {
            if (this.getNext()[i] != null) 
                return false;
        }
        return true;
    }

    /**
     * Checks if the active player switched between the active Board and one of the specified Next-Elements
     *
     * @param i the index of the Next-Element that's to be checked
     * @return true if the players switched
     */
    public boolean playersSwitched(int i) {
        return (this.getBoard().isHuman() != this.getNext()[i].getBoard().isHuman());
    }

    /**
     * The recursive addition to the writeLog() Method from the Game class. It goes through the tree
     * ans writes each state to the specified file parameter
     *
     * @param file   the logfile where the text is written into
     * @param prefix the prefix that's being used to specify the position in the tree
     * @throws IOException if the text can't be written into the file
     */
    public void printNode(Writer file, String prefix) throws IOException {
        String out = prefix;
        if (prefix.length() != 0) out += " ";
        out += ((this.getBoard().isHuman()) ? "A " : "B ") + this.getRating() + " ";
        for (int i = 0; i < this.getBoard().getPitList().length; ++i) {
            out += this.getBoard().getPitList()[i] + " ";
        }
        file.write(out);
        file.write(System.getProperty("line.separator"));
        if (!this.isBottom()) {
            for (int i = 0; i < this.getNext().length; ++i) {
                String tmp = prefix + Integer.toString(i) + ".";
                if (this.getNext()[i] == null) {
                file.write(tmp);
                file.write(System.getProperty("line.separator"));
                } else {
                    // Recursive Call
                    this.getNext()[i].printNode(file, tmp + "");
                }
            }
        }
    }
}
