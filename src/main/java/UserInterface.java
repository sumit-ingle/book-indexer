import java.util.Scanner;

import static java.lang.System.out;

public class UserInterface {
    private static final BookIndexer bookIndexer = new BookIndexer();

    public static void main(String[] args) {
        takeInputAndProcess();
    }

    private static void takeInputAndProcess() {
        displayMenu();
        try {
            Scanner sc = getScanner();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    indexBook();
                    break;
                case 2:
                    searchWord();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Please enter a valid option.");
            }
            takeInputAndProcess();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    private static void displayMenu() {
        String newLine = System.getProperty("line.separator");
        out.println(String.join(newLine, "Please enter an option:", "1. Create index from a book",
                "2. Search from index", "3. Exit"));
    }

    private static void searchWord() {
        Scanner sc = getScanner();
        out.println("Please enter the word:");
        String word = sc.nextLine();
        out.println(bookIndexer.search(word));
    }

    private static void indexBook() {
        Scanner sc = getScanner();
        out.println("Please enter the book content:");
        String book = sc.nextLine();
        bookIndexer.index(book);
    }

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
