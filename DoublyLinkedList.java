import org.jfugue.player.Player;

/**
 * The DoublyLinkedList class represents a doubly linked list structure
 * to store and manage musical phrases.
 */
public class DoublyLinkedList {

    /** The first node in the list */
    private Node head;
    /** The last node in the list */
    private Node tail;
    /** The current selected node */
    private Node current;

    /**
     * Constructor to initialize an empty doubly linked list.
     */
    public DoublyLinkedList() {
        head = tail = current = null;
    }

    /**
     * Constructor that initializes the list with an array of phrases.
     *
     * @param phrases Array of phrases to be added to the list.
     */
    public DoublyLinkedList (String [] phrases) {
        for (String phrase : phrases)
            addAtEnd(phrase);
    }

    /**
     * Returns the current node in the list.
     *
     * @return The current node.
     */
    public Node getCurrent() {
        return current;
    }

    /**
     * Sets the current node to a specified index.
     *
     * @param index The index to set as the current node.
     */
    public void setCurrent(int index) {
        if (head == null) {
            System.out.print("The list is empty");
            return;
        }
        Node temp = head;
        int count = 0;
        while (temp != null) {
            if (count == index) {
                current = temp;
                return;
            }
            temp = temp.getNext();
            count++;
        }
        System.out.println("Invalid index. Current remains unchanged.");
    }

    /**
     * Returns the head node of the list.
     *
     * @return The first node (head).
     */
    public Node getHead() {
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * @return The last node (tail).
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Moves the current pointer one step forward.
     */
    public void moveForward() {
        if (current != null && current.getNext() != null) {
            current = current.getNext();
        } else {
            System.out.println("Already at the last phrase.");
        }
    }

    /**
     * Moves the current pointer one step backward.
     */
    public void moveBackward() {
        if (current != null && current.getPrev() != null) {
            current = current.getPrev();
        } else {
            System.out.println("Already at the first phrase.");
        }
    }

    /**
     * Adds a new phrase to the end of the list.
     *
     * @param phrase The musical phrase to be added.
     */
    public void addAtEnd (String phrase) {
        Node newNode = new Node(phrase);
        if (head == null)
            head = tail = newNode;
        else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        current = tail;
    }

    /**
     * Adds a new phrase to the beginning of the list.
     *
     * @param phrase The musical phrase to be added.
     */
    public void addAtBeginning (String phrase) {
        Node newNode = new Node(phrase);
        if (head == null)
            head = tail = newNode;
        else {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
        }
        current = head;
    }

    /**
     * Adds a new phrase before the current node.
     *
     * @param phrase The musical phrase to be added.
     */
    public void addAtCurrent (String phrase) {
        if (current == null) {
            addAtEnd(phrase);
            return;
        }
        Node newNode = new Node (phrase, current.getPrev(), current);
        if (current == head)
            head = newNode;
        else
            current.getPrev().setNext(newNode);
        current.setPrev(newNode);
        current = newNode;
    }

    /**
     * Adds a phrase at a specific index.
     *
     * @param phrase The musical phrase to be added.
     * @param index The index at which to insert the phrase.
     */
    public void add (String phrase, int index) {
        Node ptr = head;
        int count = 0;
        while (ptr != null) {
            if (count == index) {
                current = ptr;
                addAtCurrent(phrase);
                return;
            }
            ptr = ptr.getNext();
            count++;
        }
        if (count == index) {
            addAtEnd(phrase);
        }
    }

    /**
     * Copies a phrase at a given index and adds it to the end of the list.
     *
     * @param index The index of the phrase to copy.
     */
    public void repeatPhraseToEnd (int index) {
        Node ptr = head;
        int count = 0;
        while (ptr != null) {
            if (count == index) {
                addAtEnd(ptr.getPhrase());
                return;
            }
            ptr = ptr.getNext();
            count++;
        }
        System.out.println("Invalid index. No phrase copied.");
    }

    /**
     * Removes the current node from the list.
     *
     * @return The removed node.
     */
    public Node removeCurrent () {
        if (current == null) {
            System.out.println("No phrase to remove.");
            return null;
        }
        Node save = current;
        if (current == head && current == tail)
            current = head = tail = null;
        else if (current == head) {
            head = head.getNext();
            head.setPrev(null);
            current = head;
        }
        else if (current == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
            current = tail;
        }
        else {
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            current = current.getNext();
        }
        return save;
    }

    /**
     * Removes a phrase at a specific index.
     *
     * @param index The index of the phrase to remove.
     * @return The removed node.
     */
    public Node remove (int index) {
        Node ptr = head;
        int count = 0;
        while (ptr != null) {
            if (count == index) {
                current = ptr;
                return removeCurrent();
            }
            ptr = ptr.getNext();
            count++;
        }
        System.out.println("Invalid index. No phrase removed.");
        return null;
    }

    /**
     * Moves a phrase from one index to another.
     *
     * @param currentIndex The current index of the phrase.
     * @param newIndex The new index where the phrase should be moved.
     */
    public void move (int currentIndex, int newIndex) {
        Node oldCurrent = remove(currentIndex);
        if (oldCurrent == null) {
            System.out.println("Invalid move: Source index does not exist.");
            return;
        }
        add(oldCurrent.getPhrase(), newIndex);
    }

    /**
     * Plays the entire composition from the beginning.
     */
    public void playComposition() {
        Player player = new Player();
        Node temp = head;
        if (temp == null) {
            System.out.println("No phrases to play.");
            return;
        }
        while (temp != null) {
            player.play(temp.getPhrase());
            System.out.println("Playing: " + temp.getPhrase());
            temp = temp.getNext();
        }
    }

    /**
     * Plays the composition from the current node onward.
     */
    public void playFromCurrent() {
        Player player = new Player();
        Node temp = current;
        if (current == null) {
            System.out.println("No current phrase to play from.");
            return;
        }
        while (temp != null) {
            player.play(temp.getPhrase());
            System.out.println("Playing: " + temp.getPhrase());
            temp = temp.getNext();
        }
    }

    /**
     * Plays a single phrase at a given index.
     *
     * @param index The index of the phrase to play.
     */
    public void playSinglePhrase(int index) {
        Player player = new Player();
        Node ptr = head;
        int count = 0;
        while (ptr != null) {
            if (count == index) {
                player.play(ptr.getPhrase());
                System.out.println("Playing: " + ptr.getPhrase());
                return;
            }
            ptr = ptr.getNext();
            count++;
        }
        System.out.println("Invalid index. No phrase played.");
    }

    /**
     * Displays all phrases in the doubly linked list with their corresponding indices.
     */
    public void showAllPhrases() {
        Node temp = head;
        int index = 0;
        while (temp.getNext() != null) {
            System.out.print(temp.getPhrase() + " ");
            temp = temp.getNext();
            index++;
        }
        System.out.print(temp.getPhrase());
    }
}
