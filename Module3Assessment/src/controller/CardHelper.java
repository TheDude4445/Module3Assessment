package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Card;

public class CardHelper {
    // Create a global instance of EntityManagerFactory
    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Module3Assessment");
    
    // Method to insert a Card
    public void insertCard(Card card) {
        EntityManager em = emfactory.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(card);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    // Method to delete a Card
    public void deleteCard(Card toDelete) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();

        // Create a query to find the Card to delete
        TypedQuery<Card> typedQuery = em.createQuery(
            "SELECT c FROM Card c WHERE c.cardNumber = :selectedCardNumber AND c.cardName = :selectedCardName",
            Card.class
        );

        // Set parameters based on the toDelete card
        typedQuery.setParameter("selectedCardNumber", toDelete.getCardNumber());
        typedQuery.setParameter("selectedCardName", toDelete.getCardName());

        // Limit the query to return only one result (if it exists)
        typedQuery.setMaxResults(1);

        // Get the result (the Card to delete)
        Card result = typedQuery.getSingleResult();

        // Remove the found Card
        em.remove(result);
        
        // Commit the transaction and close the EntityManager
        em.getTransaction().commit();
        em.close();
    }

    // Method to search for cards by cardNumber
    public List<Card> searchForCardByCardNumber(String cardNumber) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Card> typedQuery = em.createQuery(
            "SELECT c FROM Card c WHERE c.cardNumber = :selectedCardNumber",
            Card.class
        );

        typedQuery.setParameter("selectedCardNumber", cardNumber);
        List<Card> foundCards = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();

        return foundCards;
    }

    // Method to search for cards by cardName
    public List<Card> searchForCardByCardName(String cardName) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Card> typedQuery = em.createQuery(
            "SELECT c FROM Card c WHERE c.cardName = :selectedCardName",
            Card.class
        );

        typedQuery.setParameter("selectedCardName", cardName);
        List<Card> foundCards = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();

        return foundCards;
    }

    // Method to search for a Card by ID
    public Card searchForCardById(int idToEdit) {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();

        Card found = em.find(Card.class, idToEdit);

        em.getTransaction().commit();
        em.close();

        return found;
    }

    // Method to update a Card
    public void updateCard(Card toEdit) {
        EntityManager em = emfactory.createEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(toEdit);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void cleanUp() {
        emfactory.close();
    }

 // Method to retrieve all Card objects from the database
    public List<Card> getAllCards() {
        EntityManager em = emfactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Card> typedQuery = em.createQuery("SELECT c FROM Card c", Card.class);
        List<Card> cardList = typedQuery.getResultList();

        em.getTransaction().commit();
        em.close();

        return cardList;
    }
}