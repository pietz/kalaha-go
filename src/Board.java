/**
 * The Board represents the Playing Board and all the elements on it. 
 * It's methods contain the entire logic and rules of the Game Kalaha.
 */
public final class Board {

    private byte[] pitList;
    private boolean human;

    /**
     * Checks if its Humans turn
     *
     * @return true if it is Humans turn
     */
    public boolean isHuman() {
        return human;
    }

    /**
     * Instantiates a new Board based on a certain number of stones, number of pits and who can make a move first
     *
     * @param stones the stones inside each pit
     * @param pits   the number of playing pits for each player
     * @param h      is true if Human can make the first move.
     */
    public Board(int stones, int pits, boolean h) {
        this.pitList = new byte[2*pits+2];
        for (int j = 0; j < pits; j++) {
            setPit(j, (byte) stones);
            setPit(j+pits+1, (byte) stones);
        }
        setPit(pits, 0);
        setPit(2*pits+1, 0);
        this.human = h;
    }

    /**
     * Initiates a new Board that creates a deep copy of another Board
     * @param b The board thats to be copied
     */
    public Board(Board b) {
        setPitList(new byte[b.getPitList().length]);
        for (int i = 0; i < b.getPitList().length; ++i) {
            setPit(i, b.getPit(i));
        }
        setHuman(b.isHuman());
    }


    /**
     * Gets the List of Pits
     *
     * @return the Array of Pits
     */
    public byte[] getPitList() {
        return pitList;
    }

    /**
     * Sets the List of Pits
     *
     * @param pitList the pit list
     */
    public void setPitList(byte[] pitList) {
        this.pitList = pitList;
    }

    /**
     * Sets one specific Pit inside a PitList
     *
     * @param index   the Index of the Pit that is to be set
     * @param value the new amount of Stones inside the Pit
     */
    public void setPit(int index, int value) {
        this.pitList[index] = (byte) value;
    }

    /**
     * Gets the number of stones inside a pit
     *
     * @param i the index of the Pit
     * @return the number of stones in a certain pit
     */
    public byte getPit(int i) {
        return this.getPitList()[i];
    }

    /**
     * Sets who's turn it is
     *
     * @param human is true if it's Humans turn
     */
    public void setHuman(boolean human) {
        this.human = human;
    }


    /**
     * Gets the current Score
     *
     * @return the number of Stones ahead from the view of the Human Player
     */
    public byte getScore() {
        return (byte) (getPit(humanPit()) - getPit(robotPit()));
    }

    /**
     * Gets the index of Humans Win Pit
     *
     * @return the index of Humans Win Pit
     */
    public int humanPit() {
        return this.getPitList().length/2-1;
    }

    /**
     * Gets the index of Robots Win Pit
     *
     * @return the index of Robots Win Pit
     */
    public int robotPit() {
        return this.getPitList().length-1;
    }

    /**
     * Gets the Offset between Humans and Robots Pit indices
     *
     * @return the Offset between the indices
     */
    public int getOffset() {
        return (this.isHuman()) ? 0 : this.getPitList().length/2;
    }


    /**
     * Makes a play move and adjusts the Board accordingly
     *
     * @param i the Index of the Pit that was selected for the Move.
     * @return a copy of the Board if the Board was changed. The initial Board remains unchanged.
     */
    public Board makeMove(int i) {
        Board b = new Board(this);
        int stones = getPit(i);
        b.setPit(i, 0);
        while (stones != 0) {
            i = b.nextPit(i);
            b.setPit(i, b.getPit(i)+1);
            --stones;
        }
        b.capture(i);
        if (b.gameOver())
            b.getLastStones();
        if (!b.isWinPit(i))
            b.switchPlayer();
        return b;
    }

    /**
     * Make multiple moves in a row
     *
     * @param array the array containing the chosen indices
     * @return the changed board. The initial Board remains unchanged.
     */
    public Board makeMoves(int[] array) {
        Board b = new Board(this);
        for (int i : array) {
            b = b.makeMove(i);
        }
        return b;
    }

    /**
     * Gets the next Pit for the placement of stones depending on who's turn it is.
     *
     * @param i the Index of the current Pit
     * @return the Index of the Pit where the next stone will fall into.
     */
    public int nextPit(int i) {
        return nextPit(i, this.isHuman());
    }

    public int nextPit(int i, boolean h) {
        i = (i == robotPit()) ? 0 : i+1;
        if (h && i == robotPit()) i = 0;
        if (!h && i == humanPit()) i = humanPit()+1;
        return i;
    }


    /**
     * Checks for and executes the Capture Operation,
     * which steals Stones from the opposite Pit under certain circumstances
     *
     * @param i the Index of Pit that the last Stone fell into
     */
    public void capture(int i) {
        // Checks if all conditions for the capture move are met
        if (!isWinPit(i) && getPit(i) == 1 && (isHuman() && i < humanPit() || !isHuman() && i > humanPit())) {
            setPit(humanPit()+getOffset(), getPit(humanPit()+getOffset()) + getPit(i) + getPit(oppositePit(i)));
            setPit(i, 0);
            setPit(oppositePit(i), 0);
        }
    }

    /**
     * Checks if the Pit of a certain index is empty
     *
     * @param i the index of the specified Pit
     * @return true if the Pit is empty (equal to 0)
     */
    public boolean isEmpty(int i) {
        return (getPit(i) == 0);
    }


    /**
     * Gets the Pit opposite to a certain Pit
     *
     * @param i the Index of a certain Pit
     * @return the Index of the Pit opposite of it
     */
    public int oppositePit(int i) {
        return 2*humanPit()-i;
    }


    /**
     * Checks if a certain Pit is a Win Pit
     *
     * @param i Index of a certain Pit
     * @return true if it's a Win Pit
     */
    public boolean isWinPit(int i) {
        return i == humanPit() || i == robotPit();
    }


    /**
     * Switches Players
     */
    public void switchPlayer() {
        setHuman(!isHuman());
    }


    /**
     * Checks if the Game is over
     *
     * @return true if the Game is over
     */
    public boolean gameOver() {
        int h = 0, r = 0;
        for (int i = 0; i < humanPit(); i++) {
            h += getPit(i);
            r += getPit(i+humanPit()+1);
        }
        return (h == 0 || r == 0);
    }

    /**
     * Gets the index of the Pit where the last stone will fall into, if a move is being made on this Pit
     *
     * @param pit the Pit on which a move is being made
     * @return the index of the Pit where the last stone will fall into
     */
    public int indexOfLastStone(int pit) {
        for (int stones = this.getPit(pit); stones > 0; --stones) {
            pit = nextPit(pit);
        }
        return pit;
    }

    /**
     * Puts all the stones from playing pits to the win pit of the respective player
     */
    public void getLastStones() {
        for (int i = 0; i < humanPit(); ++i) {

            setPit(humanPit(), getPit(humanPit())+getPit(i));
            setPit(i, 0);
            setPit(robotPit(), getPit(robotPit())+getPit(i+humanPit()+1));
            setPit(i+humanPit()+1, 0);
        }
    }

    /**
     * Prints the current Board to the console. For Test purposes only
     */
    public void printBoard() {
        if (human) {
            System.out.printf("It's Humans Turn\n");
        } else {
            System.out.printf("It's Robots Turn\n");
        }

        for (int i = robotPit(); i > humanPit(); --i) {
            System.out.printf("%3d", getPit(i));
        }
        System.out.print("\n   ");
        for (int i = 0; i <= humanPit(); ++i) {
            System.out.printf("%3d", getPit(i));
        }
        System.out.print("\n\n");
    }

    /**
     * Prints the Board to the console in one line
     */
    public void printOneLiner() {

        for (int i = 0; i < robotPit(); ++i) {
            System.out.printf("%d,", this.getPitList()[i]);
        }
        System.out.printf("%d\n", this.getPitList()[robotPit()]);
    }
}
