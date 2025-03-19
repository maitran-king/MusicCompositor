import java.util.Scanner;

/**
 * The Composer class provides an interactive system to compose, modify, and play
 * a musical composition using a doubly linked list to store phrases.
 */
public class Composer {

    /** Stores the musical phrases */
    private DoublyLinkedList composition;
    /** Scanner for user input */
    private Scanner scanner;

    /**
     * Constructor initializes the composition with a sample melody and sets up input handling.
     */
    public Composer() {
        String[] melody = {
                "C", "C", "G", "G",
                "A", "A", "G",
                "F", "F", "E", "E",
                "D", "D", "C"
        };
        composition = new DoublyLinkedList(melody);
        scanner = new Scanner(System.in);
    }

    /**
     * Runs the interactive menu loop, allowing the user to modify and play the composition.
     */
    public void run() {
        while (true) {
            System.out.println("\n1. Add phrase\n2. Remove phrase \n3. Repeat phrase\n4. Navigate\n5. Change position\n6. Playback\n7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1)
                addPhrase();
            else if (choice == 2)
                removePhrase();
            else if (choice == 3)
                repeatPhrase();
            else if (choice == 4)
                navigate();
            else if (choice == 5)
                changePosition();
            else if (choice == 6)
                playback();
            else if (choice == 7) {
                System.out.println("Exiting.");
                return;
            }
            else
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Displays the current phrase being edited or played.
     */
    private void showCurrent() {
        if (composition.getCurrent() == null)
            System.out.println("\nCurrent phrase: None");
        else
            System.out.println("\nCurrent phrase:" + composition.getCurrent().getPhrase());
    }

    /**
     * Allows the user to add a new musical phrase at various positions in the composition.
     */
    private void addPhrase() {
        System.out.print("Enter a musical phrase: ");
        String phrase = scanner.nextLine();
        System.out.println("Where would you like to add the phrase?");
        composition.showAllPhrases();
        showCurrent();
        System.out.println("1. At the end\n2. At the beginning\n3. At current location\n4. At specific index");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        if (choice == 1)
            composition.addAtEnd(phrase);
        else if (choice == 2)
            composition.addAtBeginning(phrase);
        else if (choice == 3)
            composition.addAtCurrent(phrase);
        else if (choice == 4) {
            System.out.print("Enter index: ");
            int index = scanner.nextInt();
            composition.add(phrase, index);
        }
        else
            System.out.println("Invalid choice.");
        composition.showAllPhrases();
    }

    /**
     * Allows the user to remove a phrase from the composition.
     */
    private void removePhrase() {
        System.out.println("Where would you like to remove the phrase?");
        composition.showAllPhrases();
        showCurrent();
        System.out.println("1. Remove current phrase\n2. Remove phrase at specific index");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        if (choice == 1)
            composition.removeCurrent();
        else if (choice == 2) {
            System.out.print("Enter index: ");
            int index = scanner.nextInt();
            composition.remove(index);
        }
        else
            System.out.println("Invalid choice.");

        composition.showAllPhrases();
    }


    /**
     * Allows the user to copy a phrase from a given index and append it to the end.
     */
    private void repeatPhrase() {
        composition.showAllPhrases();
        System.out.print("\nEnter the index of the phrase to copy: ");
        int index = scanner.nextInt();
        composition.repeatPhraseToEnd(index);
        composition.showAllPhrases();
    }

    /**
     * Allows the user to navigate through the composition by moving forward or backward.
     */
    private void navigate() {
        composition.showAllPhrases();
        showCurrent();
        System.out.println("1. Move forward\n2. Move backward");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            composition.moveForward();
            showCurrent();
        }
        else if (choice == 2) {
            composition.moveBackward();
            showCurrent();
        }
        else
            System.out.println("Invalid choice.");
    }

    /**
     * Allows the user to change the position of a phrase within the composition.
     */
    private void changePosition() {
        composition.showAllPhrases();
        System.out.print("\nEnter the index of the phrase to change: ");
        int index = scanner.nextInt();
        System.out.print("Enter the new index position: ");
        int newIndex = scanner.nextInt();
        composition.move(index, newIndex);
        composition.showAllPhrases();
    }

    /**
     * Provides playback options for the user to listen to the composition.
     */
    private void playback() {
        composition.showAllPhrases();
        showCurrent();
        System.out.println("1. Play entire composition\n2. Play from current\n3. Play single phrase");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        if (choice == 1)
            composition.playComposition();
        else if (choice == 2)
            composition.playFromCurrent();
        else if (choice == 3) {
            System.out.print("Enter index: ");
            int index = scanner.nextInt();
            scanner.nextLine();
            composition.playSinglePhrase(index);
        }
        else
            System.out.println("Invalid choice.");
    }

    /**
     * Main method to initialize and run the composer application.
     */
    public static void main(String[] args) {
        Composer composer = new Composer();
        composer.run();
    }
}
