import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class TreeTest {

    @Test
    public void testBuildTree0() throws Exception {
        Tree tree = new Tree(0, 6, 6, true);
        assertEquals("[6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0]", Arrays.toString(tree.getBoard().getPitList()));
    }

    @Test
    public void testBuildTree1() throws Exception {
        Tree tree = new Tree(1, 6, 6, true);
        assertEquals("[0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0]", Arrays.toString(tree.getNext()[0].getBoard().getPitList()));
        assertEquals("[6, 0, 7, 7, 7, 7, 1, 7, 6, 6, 6, 6, 6, 0]", Arrays.toString(tree.getNext()[1].getBoard().getPitList()));
        assertEquals("[6, 6, 0, 7, 7, 7, 1, 7, 7, 6, 6, 6, 6, 0]", Arrays.toString(tree.getNext()[2].getBoard().getPitList()));
        assertEquals("[6, 6, 6, 0, 7, 7, 1, 7, 7, 7, 6, 6, 6, 0]", Arrays.toString(tree.getNext()[3].getBoard().getPitList()));
        assertEquals("[6, 6, 6, 6, 0, 7, 1, 7, 7, 7, 7, 6, 6, 0]", Arrays.toString(tree.getNext()[4].getBoard().getPitList()));
        assertEquals("[6, 6, 6, 6, 6, 0, 1, 7, 7, 7, 7, 7, 6, 0]", Arrays.toString(tree.getNext()[5].getBoard().getPitList()));

    }

    @Test
    public void testBuildTree2() throws Exception {
        Tree tree = new Tree(2, 6, 6, true);

        assertEquals(null, tree.getNext()[0].getNext()[0]);
        assertEquals("[0, 0, 8, 8, 8, 8, 2, 7, 7, 6, 6, 6, 6, 0]", Arrays.toString(tree.getNext()[0].getNext()[1].getBoard().getPitList()));
        assertEquals("[0, 7, 0, 8, 8, 8, 2, 7, 7, 7, 6, 6, 6, 0]", Arrays.toString(tree.getNext()[0].getNext()[2].getBoard().getPitList()));
        assertEquals("[0, 7, 7, 0, 8, 8, 2, 7, 7, 7, 7, 6, 6, 0]", Arrays.toString(tree.getNext()[0].getNext()[3].getBoard().getPitList()));
        assertEquals("[0, 7, 7, 7, 0, 8, 2, 7, 7, 7, 7, 7, 6, 0]", Arrays.toString(tree.getNext()[0].getNext()[4].getBoard().getPitList()));
        assertEquals("[0, 7, 7, 7, 7, 0, 2, 7, 7, 7, 7, 7, 7, 0]", Arrays.toString(tree.getNext()[0].getNext()[5].getBoard().getPitList()));

        assertEquals("[7, 0, 7, 7, 7, 7, 1, 0, 7, 7, 7, 7, 7, 1]", Arrays.toString(tree.getNext()[1].getNext()[0].getBoard().getPitList()));
        assertEquals("[7, 0, 7, 7, 7, 7, 1, 7, 0, 7, 7, 7, 7, 1]", Arrays.toString(tree.getNext()[1].getNext()[1].getBoard().getPitList()));
        assertEquals("[7, 1, 7, 7, 7, 7, 1, 7, 6, 0, 7, 7, 7, 1]", Arrays.toString(tree.getNext()[1].getNext()[2].getBoard().getPitList()));
        assertEquals("[7, 1, 8, 7, 7, 7, 1, 7, 6, 6, 0, 7, 7, 1]", Arrays.toString(tree.getNext()[1].getNext()[3].getBoard().getPitList()));
        assertEquals("[7, 1, 8, 8, 7, 7, 1, 7, 6, 6, 6, 0, 7, 1]", Arrays.toString(tree.getNext()[1].getNext()[4].getBoard().getPitList()));
        assertEquals("[7, 1, 8, 8, 8, 7, 1, 7, 6, 6, 6, 6, 0, 1]", Arrays.toString(tree.getNext()[1].getNext()[5].getBoard().getPitList()));
    }

    @Test
    public void testBuildTree3() throws Exception {
        Tree tree = new Tree(3, 6, 6, true);
        assertEquals(2, tree.getRating());
    }
    
    @Test
    public void testBuildTree4() throws Exception {
        Tree tree = new Tree(4, 6, 6, true);
        assertEquals(1, tree.getRating());
    }

    @Test
    public void testBuildTree5() throws Exception {
        Tree tree = new Tree(5, 6, 6, true);
        assertEquals(2, tree.getRating());
    }
    
    @Test
    public void testBuildTree6() throws Exception {
        Tree tree = new Tree(6, 6, 6, true);
        assertEquals(1, tree.getRating());
    }

    @Test
    public void testBuildTree7() throws Exception {
        Tree tree = new Tree(7, 6, 6, true);
        assertEquals(2, tree.getRating());
    }

    @Test
    public void testgetIndexOfBestScore() throws Exception {
        Tree tree1 = new Tree(1, 6, 6, true);
        assertEquals(0, tree1.getIndexOfBestScore());
        Tree tree2 = new Tree(2, 6, 6, true);
        assertEquals(0, tree2.getIndexOfBestScore());
        Tree tree3 = new Tree(3, 6, 6, true);
        assertEquals(0, tree3.getIndexOfBestScore());
        Tree tree4 = new Tree(4, 6, 6, true);
        assertEquals(0, tree4.getIndexOfBestScore());
        Tree tree5 = new Tree(5, 6, 6, true);
        assertEquals(0, tree5.getIndexOfBestScore());
    }

}