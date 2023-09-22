import java.util.List;
import java.util.Scanner;
import controller.CardHelper;
import model.Card;

public class StartProgram {
    static Scanner in = new Scanner(System.in);
    static CardHelper cardHelper = new CardHelper();

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        boolean goAgain = true;
        System.out.println("--- Welcome to the Card Management System! ---");
        while (goAgain) {
            System.out.println("*  Select an action:");
            System.out.println("*  1 -- Add a Card");
            System.out.println("*  2 -- Edit a Card");
            System.out.println("*  3 -- Delete a Card");
            System.out.println("*  4 -- View the Card List");
            System.out.println("*  5 -- Exit the program");
            System.out.print("*  Your selection: ");
            int selection = in.nextInt();
            in.nextLine();

            switch (selection) {
                case 1:
                    addCard();
                    break;
                case 2:
                    editCard();
                    break;
                case 3:
                    deleteCard();
                    break;
                case 4:
                    viewCardList();
                    break;
                case 5:
                    cardHelper.cleanUp();
                    System.out.println("   Goodbye!   ");
                    goAgain = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter a card number: ");
        String cardNumber = in.nextLine();
        System.out.print("Enter a card name: ");
        String cardName = in.nextLine();

        Card card = new Card(cardNumber, cardName);
        cardHelper.insertCard(card);
    }

    private static void editCard() {
        System.out.print("Enter the card number to edit: ");
        String cardNumber = in.nextLine();
        System.out.print("Enter the card name to edit: ");
        String cardName = in.nextLine();

        List<Card> foundCards = cardHelper.searchForCardByCardNumber(cardNumber);

        if (!foundCards.isEmpty()) {
            System.out.println("Found Results:");
            for (Card card : foundCards) {
                System.out.println(card.getId() + " : " + card.getCardNumber() + " - " + card.getCardName());
            }

            System.out.print("Which ID to edit: ");
            int idToEdit = in.nextInt();
            in.nextLine();

            Card toEdit = cardHelper.searchForCardById(idToEdit);

            System.out.println("Retrieved Card: " + toEdit.getCardNumber() + " - " + toEdit.getCardName());
            System.out.print("Enter the new card number: ");
            String newCardNumber = in.nextLine();
            System.out.print("Enter the new card name: ");
            String newCardName = in.nextLine();

            toEdit.setCardNumber(newCardNumber);
            toEdit.setCardName(newCardName);

            cardHelper.updateCard(toEdit);
        } else {
            System.out.println("No results found for the given card number.");
        }
    }

    private static void deleteCard() {
        System.out.print("Enter the card number to delete: ");
        String cardNumber = in.nextLine();
        System.out.print("Enter the card name to delete: ");
        String cardName = in.nextLine();

        List<Card> foundCards = cardHelper.searchForCardByCardNumber(cardNumber);

        if (!foundCards.isEmpty()) {
            System.out.println("Found Results:");
            for (Card card : foundCards) {
                System.out.println(card.getId() + " : " + card.getCardNumber() + " - " + card.getCardName());
            }

            System.out.print("Which ID to delete: ");
            int idToDelete = in.nextInt();
            in.nextLine();

            Card toDelete = cardHelper.searchForCardById(idToDelete);

            cardHelper.deleteCard(toDelete);
        } else {
            System.out.println("No results found for the given card number.");
        }
    }

    private static void viewCardList() {
        List<Card> cardList = cardHelper.getAllCards();

        if (!cardList.isEmpty()) {
            System.out.println("Card List:");
            for (Card card : cardList) {
                System.out.println(card.getId() + " : " + card.getCardNumber() + " - " + card.getCardName());
            }
        } else {
            System.out.println("No cards in the list.");
        }
    }
}