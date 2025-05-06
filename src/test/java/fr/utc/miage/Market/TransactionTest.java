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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import fr.utc.miage.Acteurs.Investisseur;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;

public class TransactionTest {

    private final Action action = new ActionSimple("A");
    private final Investisseur buyer = new Investisseur("Buyer1", "Test", "password", 1000f);
    private final Investisseur seller = new Investisseur("Seller1", "Test", "password", 1000f);
    private final LocalDateTime date = LocalDateTime.of(2025, 5, 6, 10, 0);
    private final int quantity = 100;
    private final float price = 120f; 
    
    @Test
    void testTransactionConstructor(){ 
        // WHEN
        Transaction transaction = new Transaction(action, quantity, date, buyer, seller, price);

        // THEN
        assertAll(
            () -> assertEquals(action, transaction.getAction()),
            () -> assertEquals(quantity, transaction.getQuantity()),
            () -> assertEquals(date, transaction.getDate()),
            () -> assertEquals(buyer, transaction.getBuyer()),
            () -> assertEquals(seller, transaction.getSeller()),
            () -> assertEquals(price, transaction.getPrice())
        );
     }

    @Test
    void testTransactionSetters() {
        // WHEN
        Transaction tx = new Transaction(null, 0, null, null, null, 0f);
        tx.setAction(action);
        tx.setQuantity(quantity);
        tx.setDate(date);
        tx.setBuyer(buyer);
        tx.setSeller(seller);
        tx.setPrice(price);
        // THEN
        assertAll(
            () -> assertEquals(action, tx.getAction()),
            () -> assertEquals(quantity, tx.getQuantity()),
            () -> assertEquals(date, tx.getDate()),
            () -> assertEquals(buyer, tx.getBuyer()),
            () -> assertEquals(seller, tx.getSeller()),
            () -> assertEquals(price, tx.getPrice())
        );
    }

    @Test
    void testToStringContainsAllFields() {
        //WHEN
        Transaction tx = new Transaction(action, quantity, date, buyer, seller, price);
        String result = tx.toString();
        //THEN
        assertAll(
            () -> assertTrue(result.contains("A")),
            () -> assertTrue(result.contains("Buyer1")),
            () -> assertTrue(result.contains("Seller1")),
            () -> assertTrue(result.contains("100")),
            () -> assertTrue(result.contains("120"))
        );
    }

    @Test
    void testEqualsAndHashCodeSameValues() {
        // WHEN
        Transaction tx1 = new Transaction(action, quantity, date, buyer, seller, price);
        Transaction tx2 = new Transaction(action, quantity, date, buyer, seller, price);
        // THEN
        assertAll(
            () -> assertEquals(tx1, tx2),
            () -> assertEquals(tx1.hashCode(), tx2.hashCode())
        );
    }

    @Test
    void testNotEqualsDifferentAction() {
        // WHEN
        Action otherAction = new ActionSimple("B");
        Transaction tx1 = new Transaction(action, quantity, date, buyer, seller, price);
        Transaction tx2 = new Transaction(otherAction, quantity, date, buyer, seller, price);
        // THEN
        assertNotEquals(tx1, tx2);
    }

    @Test
    void testNotEqualsDifferentQuantity() {
        // WHEN
        Transaction tx1 = new Transaction(action, quantity, date, buyer, seller, price);
        Transaction tx2 = new Transaction(action, quantity + 1, date, buyer, seller, price);
        // THEN
        assertNotEquals(tx1, tx2);
    }

    @Test
    void testNotEqualsDifferentPrice() {
        // WHEN
        Transaction tx1 = new Transaction(action, quantity, date, buyer, seller, price);
        Transaction tx2 = new Transaction(action, quantity, date, buyer, seller, price + 1f);
        //THEN
        assertNotEquals(tx1, tx2);
    }
 
}
