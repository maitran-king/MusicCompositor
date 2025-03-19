import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the DoublyLinkedList implementation.
 * It tests adding, removing, navigation, and playback functionalities.
 */
public class DoublyLinkedListTest {

    /** A list initialized with sample phrases */
    private DoublyLinkedList list;
    /** An empty list for testing edge cases */
    private DoublyLinkedList emptyList;

    /**
     * Initializes test data before each test.
     */
    @BeforeEach
    public void setUp() {
        String[] sample = {"1","2","3","4"};
        list = new DoublyLinkedList(sample);
        emptyList = new DoublyLinkedList();
    }

    /**
     * Tests adding a phrase at the end of the list.
     */
    @Test
    public void testAddAtEnd() {
        list.addAtEnd("5");
        assertEquals("5", list.getTail().getPhrase());
        assertEquals("5", list.getCurrent().getPhrase());

        emptyList.addAtEnd("5");
        assertEquals("5", emptyList.getTail().getPhrase());
        assertEquals("5", emptyList.getHead().getPhrase());
        assertEquals("5", emptyList.getCurrent().getPhrase());
    }

    /**
     * Tests adding a phrase at the beginning of the list.
     */
    @Test
    public void testAddAtBeginning() {
        list.addAtBeginning("0");
        assertEquals("0", list.getHead().getPhrase());
        assertEquals("0", list.getCurrent().getPhrase());

        emptyList.addAtBeginning("0");
        assertEquals("0", emptyList.getHead().getPhrase());
        assertEquals("0", emptyList.getTail().getPhrase());
        assertEquals("0", emptyList.getCurrent().getPhrase());
    }

    /**
     * Tests adding a phrase at the current position.
     */
    @Test
    public void testAddAtCurrent() {
        list.setCurrent(1);
        list.addAtCurrent("0.5");
        assertEquals("0.5", list.getCurrent().getPhrase());
        assertEquals("0.5",list.getHead().getNext().getPhrase());

        list.setCurrent(0);
        list.addAtCurrent("-1");
        assertEquals("-1", list.getCurrent().getPhrase());
        assertEquals("-1",list.getHead().getPhrase());

        list.setCurrent(5);
        list.addAtCurrent("3.5");
        assertEquals("3.5", list.getCurrent().getPhrase());
        assertEquals("3.5",list.getTail().getPrev().getPhrase());

        emptyList.addAtCurrent("0");
        assertEquals("0", emptyList.getHead().getPhrase());
        assertEquals("0", emptyList.getTail().getPhrase());
        assertEquals("0", emptyList.getCurrent().getPhrase());
    }

    /**
     * Tests inserting a phrase at a specific index.
     */
    @Test
    public void testAdd() {
        list.add("-1",0);
        assertEquals("-1", list.getHead().getPhrase());
        assertEquals("-1",list.getCurrent().getPhrase());

        list.add("0",1);
        assertEquals("0", list.getHead().getNext().getPhrase());
        assertEquals("0",list.getCurrent().getPhrase());
    }

    /**
     * Tests removing the current phrase.
     */
    @Test
    public void testRemoveCurrent() {
        list.setCurrent(1);
        Node remove = list.removeCurrent();
        assertEquals("2", remove.getPhrase());
        assertEquals("3", list.getCurrent().getPhrase());

        list.setCurrent(0);
        Node remove2 = list.removeCurrent();
        assertEquals("1", remove2.getPhrase());
        assertEquals("3", list.getCurrent().getPhrase());
        assertEquals("3", list.getHead().getPhrase());

        list.setCurrent(1);
        Node remove3 = list.removeCurrent();
        assertEquals("4", remove3.getPhrase());
        assertEquals("3", list.getCurrent().getPhrase());
        assertEquals("3", list.getTail().getPhrase());
    }

    /**
     * Tests removing a phrase at a specific index.
     */
    @Test
    public void testRemove() {
        Node remove = list.remove(0);
        assertEquals("1", remove.getPhrase());
        assertEquals("2", list.getHead().getPhrase());

        Node remove2 = list.remove(2);
        assertEquals("4", remove2.getPhrase());
        assertEquals("3", list.getCurrent().getPhrase());
    }

    /**
     * Tests moving a phrase from one index to another.
     */
    @Test
    public void testMove() {
        list.move(0,1);
        assertEquals("2",list.getHead().getPhrase());
        assertEquals("1",list.getHead().getNext().getPhrase());

        list.move(0,3);
        assertEquals("2",list.getTail().getPhrase());
        assertEquals("4",list.getTail().getPrev().getPhrase());
    }

    /**
     * Tests moving forward in the list.
     */
    @Test
    public void testMoveForward() {
        list.setCurrent(0);
        assertEquals("1", list.getCurrent().getPhrase());
        list.moveForward();
        assertEquals("2", list.getCurrent().getPhrase());
    }

    /**
     * Tests moving backward in the list.
     */
    @Test
    public void testMoveBackward() {
        list.setCurrent(2);
        assertEquals("3", list.getCurrent().getPhrase());
        list.moveBackward();
        assertEquals("2", list.getCurrent().getPhrase());
    }

    /**
     * Tests repeating a phrase and adding it to the end.
     */
    @Test
    public void testRepeatPhraseToEnd() {
        list.repeatPhraseToEnd(2);
        assertEquals("3", list.getCurrent().getPhrase());
        assertEquals("3", list.getTail().getPhrase());
        assertEquals("4", list.getTail().getPrev().getPhrase());
    }
}
