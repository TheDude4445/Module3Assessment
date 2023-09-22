package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;
    
    @Column(name = "CARD_NUMBER")
    private String cardNumber;
    
    @Column(name = "CARD_NAME")
    private String cardName;

    // Default, no-arg constructor
    public Card() {
        super();
    }

    // Constructor with cardNumber and cardName parameters
    public Card(String cardNumber, String cardName) {
        super();
        this.cardNumber = cardNumber;
        this.cardName = cardName;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for cardNumber
    public String getCardNumber() {
        return cardNumber;
    }

    // Setter for cardNumber
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Getter for cardName
    public String getCardName() {
        return cardName;
    }

    // Setter for cardName
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    // Helper method to return card details as a formatted string
    public String returnCardDetails() {
        return "Card Number: " + this.cardNumber + ", Card Name: " + this.cardName;
    }
}