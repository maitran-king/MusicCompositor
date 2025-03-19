/**
 * The Node class represents an individual node in a doubly linked list.
 * Each node contains a musical phrase and references to both the next and previous nodes.
 */
public class Node {

    /** The musical phrase stored in this node */
    private String phrase;
    /** Reference to the next node in the list */
    private Node next;
    /** Reference to the previous node in the list */
    private Node prev;

    /**
     * Constructor to initialize a node with a phrase and references to the previous and next nodes.
     *
     * @param phrase The musical phrase stored in this node.
     * @param prev   The previous node in the list.
     * @param next   The next node in the list.
     */
    public Node (String phrase, Node prev, Node next) {
        this.phrase = phrase;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Constructor to initialize a node with only a phrase.
     * The previous and next references are set to null.
     *
     * @param phrase The musical phrase stored in this node.
     */
    public Node (String phrase) {
        this.phrase = phrase;
        this.next = null;
        this.prev = null;
    }

    /**
     * Returns the musical phrase stored in this node.
     *
     * @return The phrase as a String.
     */
    public String getPhrase() {
        return phrase;
    }

    /**
     * Updates the musical phrase stored in this node.
     *
     * @param phrase The new phrase to store in the node.
     */
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    /**
     * Returns the next node in the list.
     *
     * @return The next node or null if this is the last node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Updates the reference to the next node in the list.
     *
     * @param next The node to set as the next node.
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Returns the previous node in the list.
     *
     * @return The previous node or null if this is the first node.
     */
    public Node getPrev() {
        return prev;
    }

    /**
     * Updates the reference to the previous node in the list.
     *
     * @param prev The node to set as the previous node.
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
