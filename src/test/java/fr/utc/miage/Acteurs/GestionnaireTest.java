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


package fr.utc.miage.Acteurs;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

import fr.utc.miage.Market.Transaction;
import fr.utc.miage.Market.Marche;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;

public class GestionnaireTest {

    private final String NAME_GESTIONNAIRE1 = "Gestionnaire1";
    private final String FIRST_NAME_GESTIONNAIRE1 = "FirstNameGestionnaire1";
    private final String PASSWORD_GESTIONNAIRE1 = "Password";

    private final Action ACTION1 = new ActionSimple("Action1");

    private final int NEW_QUANTITY = 5;


    @Test
    void TestConstructeurGestionnaire(){
        //WHEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1,FIRST_NAME_GESTIONNAIRE1,PASSWORD_GESTIONNAIRE1);
        //THEN
        Assertions.assertAll(
            () -> Assertions.assertEquals(NAME_GESTIONNAIRE1, gestionnaire.getName()),
            () -> Assertions.assertEquals(FIRST_NAME_GESTIONNAIRE1, gestionnaire.getFirstName())
        );
    }

    @Test
    void testCreateActionWithNonExistantAction() {
        //GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        //WHEN 
        gestionnaire.createAction(ACTION1, 10);

        //THEN

        Assertions.assertAll(
            () -> Assertions.assertTrue(Marche.getActionsAvailable().containsKey(ACTION1)),
            () -> Assertions.assertEquals(10, Marche.getActionsAvailable().get(ACTION1))
        );

        Marche.clearActionsAvailable(); // Clear the actions available on the market for the next test

    }

    @Test
    void testCreateActionWithExistantAction() {

        //GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        gestionnaire.createAction(ACTION1, 5);

        //WHEN 

        gestionnaire.createAction(ACTION1, 10);

        //THEN

        Assertions.assertAll(
            () -> Assertions.assertTrue(Marche.getActionsAvailable().containsKey(ACTION1)),
            () -> Assertions.assertEquals(15, Marche.getActionsAvailable().get(ACTION1))
        );
        Marche.clearActionsAvailable(); // Clear the actions available on the market for the next test

    }

    @Test
    void testDeleteActionWhenActionExists() {
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        gestionnaire.createAction(ACTION1, 10);

        // WHEN
        boolean result = gestionnaire.deleteAction(ACTION1);

        // THEN
        Assertions.assertAll(
            () -> Assertions.assertTrue(result),
            () -> Assertions.assertFalse(Marche.getActionsAvailable().containsKey(ACTION1))
        );

        Marche.clearActionsAvailable();
    }

    @Test
    void testDeleteActionWhenActionDoesNotExist() {
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        // WHEN
        boolean result = gestionnaire.deleteAction(ACTION1);

        // THEN
        Assertions.assertFalse(result);

        Marche.clearActionsAvailable();
    }

   @Test
    void testUpdateActionWhenActionExists() {
        // GIVEN 
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        Marche.getActionsAvailable().put(ACTION1, 0);


        // WHEN 
        boolean result = gestionnaire.updateAction(ACTION1, NEW_QUANTITY);

        // THEN 
        assertAll(
            () -> assertTrue(result),
            () -> assertEquals(NEW_QUANTITY, Marche.getActionsAvailable().get(ACTION1))
        );

        Marche.clearActionsAvailable();
    }

    @Test
    void testUpdateActionWhenActionDoesNotExist() {
        // GIVEN 
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        // WHEN 
        boolean result = gestionnaire.updateAction(ACTION1, NEW_QUANTITY);

        // THEN 
        assertFalse(result);

        Marche.clearActionsAvailable();
    }

    @Test
    void testUpdateActionWithNull() {
         // GIVEN 
         Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        // WHEN 
        boolean result = gestionnaire.updateAction(null, NEW_QUANTITY);

        // THEN 
        assertFalse(result);

        Marche.clearActionsAvailable();
    }

    @Test
    void testConsultBestActionByDay() {
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        Action A = new ActionSimple("A");
        Action B = new ActionSimple("B");
    
        List<Transaction> transactions = List.of(
            new Transaction(A, 100, LocalDateTime.of(2025, 5, 6, 10, 0), null, null, 120f),
            new Transaction(B, 80, LocalDateTime.of(2025, 5, 6, 11, 0), null, null, 200f),
            new Transaction(A, 50, LocalDateTime.of(2025, 5, 6, 15, 0), null, null, 130f)
        );
    
        // WHEN
        String result = gestionnaire.consultBestAction(transactions, "jour");
    
        // THEN
        assertAll(
            () -> assertTrue(result.contains("2025-05-06")),
            () -> assertTrue(result.contains("A")),
            () -> assertTrue(result.contains("150 unités"))
        );
    }
    

    @Test
    void testConsultBestActionByMonth() {
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        Action A = new ActionSimple("A");
        Action B = new ActionSimple("B");
    
        List<Transaction> transactions = List.of(
            new Transaction(B, 120, LocalDateTime.of(2025, 6, 10, 10, 0), null, null, 250f),
            new Transaction(A, 90, LocalDateTime.of(2025, 6, 12, 14, 0), null, null, 160f)
        );
    
        // WHEN
        String result = gestionnaire.consultBestAction(transactions, "mois");
    
        // THEN
        assertAll(
            () -> assertTrue(result.contains("2025-06")),
            () -> assertTrue(result.contains("B")),
            () -> assertTrue(result.contains("120 unités"))
        );
    }
    

    @Test
    void testConsultBestActionByYear(){
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        Action A = new ActionSimple("A");
        Action B = new ActionSimple("B");

        List<Transaction> transactions = List.of(
            new Transaction(B, 150, LocalDateTime.of(2024, 3, 5, 9, 30), null, null, 300f),
            new Transaction(A, 100, LocalDateTime.of(2024, 1, 1, 12, 0), null, null, 140f)
        );

        // WHEN
        String result = gestionnaire.consultBestAction(transactions, "annee");

        // THEN
        assertAll(
            () -> assertTrue(result.contains("2024")),
            () -> assertTrue(result.contains("B")),
            () -> assertTrue(result.contains("150 unités"))
        );
    }

    @Test
    void testConsultBestActionEmptyList(){
         // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        List<Transaction> emptyList = List.of();

        // WHEN
        String result = gestionnaire.consultBestAction(emptyList, "jour");

        // THEN
        assertEquals("No transactions found.", result);
    }
   
}
