
/*
 * Copyright 2025 Segot-Laberou Maxime, Junfang He;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package fr.utc.miage.Market;

import java.time.LocalDateTime;
import java.util.Objects;


import fr.utc.miage.Acteurs.Investisseur;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;
/**
 * Represents a transaction on the stock market.
 * A transaction records the exchange of a quantity of an Action between two investors at a specific date and price.
 * 
 * @author Junfang He，Segot-Laberou Maxime
 */
public class Transaction {
    /** 
     * The action (stock) involved in the transaction
     */
    private Action action;
    /*
     * The quantity of the action being transacted
     */
    private int quantity;
    /*
     * The date and time of the transaction
     */
    private LocalDateTime date;
    /*
     * The investor who buys the action
     */
    private Investisseur buyer;
    /*
     * The investor who sells the action
     */
    private Investisseur seller;
    /*
     * The price per unit of the action at the time of transaction
     */
    private float price;

    /**
     * Constructs a new Transaction.
     *
     * @param action the action being exchanged
     * @param quantity the quantity exchanged
     * @param date the date and time of the transaction
     * @param buyer the investor buying the action
     * @param seller the investor selling the action
     * @param price the price per unit of the action
     */
    public Transaction(Action action, int quantity, LocalDateTime date, Investisseur buyer, Investisseur seller, float price) {
        this.action = action;
        this.quantity = quantity;
        this.date = date;
        this.buyer = buyer;
        this.seller = seller;
        this.price = price;
    }

    // --- Getters ---

     /**
     * Gets the action involved in the transaction.
     * @return the action
     */
    public Action getAction() {
        return action;
    }
    /**
     * Gets the quantity of action exchanged.
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Gets the date and time of the transaction.
     * @return the transaction date
     */
    public LocalDateTime getDate() {
        return date;
    }
    /**
     * Gets the buyer in the transaction.
     * @return the buyer
     */
    public Investisseur getBuyer() {
        return buyer;
    }
    /**
     * Gets the seller in the transaction.
     * @return the seller
     */
    public Investisseur getSeller() {
        return seller;
    }
    /**
     * Gets the price per unit of the action.
     * @return the unit price
     */
    public float getPrice() {
        return price;
    }

    // --- Setters ---
    /**
     * Sets the action involved in the transaction.
     * @param action the action
     */
    public void setAction(Action action) {
        this.action = action;
    }
    /**
     * Sets the quantity of action exchanged.
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the date and time of the transaction.
     * @param date the transaction date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Sets the buyer in the transaction.
     * @param buyer the buyer
     */
    public void setBuyer(Investisseur buyer) {
        this.buyer = buyer;
    }

    /**
     * Sets the seller in the transaction.
     * @param seller the seller
     */
    public void setSeller(Investisseur seller) {
        this.seller = seller;
    }

    /**
     * Sets the price per unit of the action.
     * @param price the unit price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the transaction.
     * @return a string describing the transaction
     */
    @Override
    public String toString() {
        return "Transaction{" +
               "action=" + action.getLibelle() +
               ", quantity=" + quantity +
               ", date=" + date +
               ", buyer=" + buyer.getName() +
               ", seller=" + seller.getName() +
               ", price=" + price +
               '}';
    }

    /**
     * Checks equality between this transaction and another object.
     * @param o the object to compare
     * @return true if all fields are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return quantity == that.quantity &&
               Float.compare(that.price, price) == 0 &&
               Objects.equals(action, that.action) &&
               Objects.equals(date, that.date) &&
               Objects.equals(buyer, that.buyer) &&
               Objects.equals(seller, that.seller);
    }

    /**
     * Computes the hash code for this transaction.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(action, quantity, date, buyer, seller, price);
    }

   


    
}
