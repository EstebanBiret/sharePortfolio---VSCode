/*
 * Copyright 2025 Esteban BIRET-TOSCANO Flavien DIAS;
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

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.utc.miage.Market.Marche;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Portefeuille;

class InvestisseurTest {

    private Investisseur investisseur;
    private ActionSimple action;
    private ActionSimple actionNoValue;
    private Marche marche;
    private Jour jour;

    @BeforeEach
    void setUp() {
        investisseur = new Investisseur("John", "Doe", "password", 1000);
        action = new ActionSimple("Tisseo");
        actionNoValue = new ActionSimple("airbus");
        jour = Jour.getActualJour();

        // Enregistre la valeur de l'action pour ce jour
        action.enrgCours(jour, 150);

        // Ajoute l'action au marché avec 10 exemplaires
        HashMap<Action, Integer> actions = new HashMap<>();
        actions.put(action, 10);
        actions.put(actionNoValue, 10);
        marche = new Marche(actions);
    }

    @Test 
    void constructorShouldWork() {
        assertAll(
                () -> assertEquals("John", investisseur.getName()),
                () -> assertEquals("Doe", investisseur.getFirstName()),
                () -> assertEquals("password", investisseur.getPassword()),
                () -> assertEquals(1000, investisseur.getBalance())
        );
    }

    @Test
    void constructorWithWalletTest() {
        Portefeuille wallet = new Portefeuille();
        investisseur = new Investisseur("John", "Doe", "password", 1000, wallet);
        assertAll(
                () -> assertEquals("John", investisseur.getName()),
                () -> assertEquals("Doe", investisseur.getFirstName()),
                () -> assertEquals("password", investisseur.getPassword()),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertEquals(wallet, investisseur.getWallet())
        );
    }

    @Test
    void testBuyActionSuccess() {
        boolean result = investisseur.buyActionSimple(action, 5);
        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(1000 - 150 * 5, investisseur.getBalance()),
                () -> assertEquals(5, investisseur.getWallet().getActions().get(action)),
                () -> assertEquals(5, marche.getActionsAvailable().get(action))
        );
    }

   @Test
    void testBuyActionInsufficientQuantityMarket() {
        boolean result = investisseur.buyActionSimple(action, 20);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(action)),
                () -> assertEquals(10, marche.getActionsAvailable().get(action))
        );
    }

    @Test
    void testBuyActionNotValueForActualDay() {
        boolean result = investisseur.buyActionSimple(actionNoValue, 5);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(actionNoValue)),
                () -> assertEquals(10, marche.getActionsAvailable().get(actionNoValue))
        );
    }

    @Test
    void testBuyActionInsufficientBalance() {
        investisseur.setBalance(100);
        boolean result = investisseur.buyActionSimple(action, 5);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(100, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(action)),
                () -> assertEquals(10, marche.getActionsAvailable().get(action))
        );
    }

}
