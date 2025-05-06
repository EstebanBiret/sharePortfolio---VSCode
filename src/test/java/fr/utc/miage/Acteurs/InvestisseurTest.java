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
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.utc.miage.Market.Marche;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionCompose;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Portefeuille;

class InvestisseurTest {

    private Investisseur investisseur;
    private ActionSimple action;
    private ActionSimple actionDejaPossedee;
    private ActionSimple actionNoValue;
    @SuppressWarnings("unused")
    private Marche marche;
    private Jour jour;
    private Portefeuille portefeuille;

    @BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        HashMap<Action, Integer> actionsPossedees = new HashMap<>();
        actionDejaPossedee = new ActionSimple("france2");
        actionsPossedees.put(actionDejaPossedee, 5);
        portefeuille = new Portefeuille(actionsPossedees);
        investisseur = new Investisseur("John", "Doe", "password", 1000, portefeuille);
        action = new ActionSimple("Tisseo");
        actionNoValue = new ActionSimple("airbus");
        jour = Jour.getActualJour();

        // Enregistre la valeur de l'action pour ce jour
        action.enrgCours(jour, 150);
        actionDejaPossedee.enrgCours(jour, 50);

        // Ajoute l'action au marché avec 10 exemplaires
        HashMap<Action, Integer> actions = new HashMap<>();
        actions.put(action, 10);
        actions.put(actionDejaPossedee, 10);
        actions.put(actionNoValue, 10);
        marche = new Marche(actions);
    }

    @Test 
    void constructorShouldWork() {
        Investisseur investisseurTest = new Investisseur("John", "Doe", "password", 1000);
        assertAll(
                () -> assertEquals("John", investisseurTest.getName()),
                () -> assertEquals("Doe", investisseurTest.getFirstName()),
                () -> assertEquals("password", investisseurTest.getPassword()),
                () -> assertEquals(1000, investisseurTest.getBalance())
        );
    }

    @Test
    void constructorWithWalletShouldWork() {
        Portefeuille wallet = new Portefeuille();
        Investisseur investisseurTest = new Investisseur("John", "Doe", "password", 1000, wallet);
        assertAll(
                () -> assertEquals("John", investisseurTest.getName()),
                () -> assertEquals("Doe", investisseurTest.getFirstName()),
                () -> assertEquals("password", investisseurTest.getPassword()),
                () -> assertEquals(1000, investisseurTest.getBalance()),
                () -> assertEquals(wallet, investisseurTest.getWallet())
        );
    }

    @Test
    void testSetWallet() {
        Portefeuille newWallet = new Portefeuille();
        investisseur.setWallet(newWallet);
        assertEquals(newWallet, investisseur.getWallet());
    }

    @Test
    void testBuyActionSuccess() {
        boolean result = investisseur.buyActionSimple(action, 5);
        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(1000 - 150 * 5, investisseur.getBalance()),
                () -> assertEquals(5, investisseur.getWallet().getActions().get(action)),
                () -> assertEquals(5, Marche.getActionsAvailable().get(action))
        );
    }

   @Test
    void testBuyActionInsufficientQuantityMarket() {
        boolean result = investisseur.buyActionSimple(action, 20);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(action)),
                () -> assertEquals(10, Marche.getActionsAvailable().get(action))
        );
    }

    @Test
    void testBuyActionNotValueForActualDay() {
        boolean result = investisseur.buyActionSimple(actionNoValue, 5);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(actionNoValue)),
                () -> assertEquals(10, Marche.getActionsAvailable().get(actionNoValue))
        );
    }

    @Test
    void testBuyActionInsufficientBalance() {
        investisseur.setBalance(100);
        boolean result = investisseur.buyActionSimple(action, 5);
        assertFalse(result);
    }

    @Test
    void testSellActionSuccess() {
        boolean result = investisseur.sellActionSimple(actionDejaPossedee, 3);
        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(1000 + 3 * actionDejaPossedee.valeur(jour), investisseur.getBalance()),
                () -> assertEquals(2, investisseur.getWallet().getActions().get(actionDejaPossedee)),
                () -> assertEquals(13, Marche.getActionsAvailable().get(actionDejaPossedee))
        );
    }

    @Test
    void testSellActionNotOwnedOrInsufisantQuantity() {
        boolean result = investisseur.sellActionSimple(action, 3);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(action)),
                () -> assertEquals(10, Marche.getActionsAvailable().get(action))
        );
    }

    @Test
    void testSellActionNotValueForActualDay() {
        investisseur.getWallet().addAction(actionNoValue, 5);
        boolean result = investisseur.sellActionSimple(actionNoValue, 5);
        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertTrue(investisseur.getWallet().getActions().containsKey(actionNoValue)),
                () -> assertEquals(10, Marche.getActionsAvailable().get(actionNoValue))
        );
    }

    @Test
    void testGetValueOfActionForGivenDayShouldWork() {
        float value = investisseur.getValueOfActionForGivenDay(action, jour);
        assertEquals(150, value);
    }

    @Test
    void testGetValueOfActionForInexistingDay() {
        float value = investisseur.getValueOfActionForGivenDay(actionNoValue, new Jour(2023, 367));
        assertEquals(0, value);
    }

    @Test
    void testGetTotalValueOfWallet() {
        investisseur.getWallet().addAction(action, 1);
        float totalValue = investisseur.getTotalValueOfWallet();

        assertEquals(400, totalValue);
    }

    @Test
    void testGetTotalValueOfEmptyWallet() {
        Investisseur investisseurPauvre = new Investisseur("John", "Doe", "password", 0);
        float totalValue = investisseurPauvre.getTotalValueOfWallet();
        assertEquals(0, totalValue);
    }

    @Test
    void testBuyActionComposeSuccess() {
        ActionSimple comp1 = new ActionSimple("comp1");
        ActionSimple comp2 = new ActionSimple("comp2");

        comp1.enrgCours(jour, 100);
        comp2.enrgCours(jour, 200);

        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(comp1, 50.0);
        composition.put(comp2, 50.0);
        ActionCompose actioncompose = new ActionCompose("PanierMix", composition);

        // Ajout au marché
        Marche.getActionsAvailable().put(actioncompose, 10);

        boolean result = investisseur.buyActionCompose(actioncompose, 2);

        float valeurCompose = actioncompose.valeur(jour); // moyenne pondérée : 100*0.5 + 200*0.5 = 150

        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(1000 - valeurCompose * 2, investisseur.getBalance()),
                () -> assertEquals(2, investisseur.getWallet().getActions().get(actioncompose)),
                () -> assertEquals(8, Marche.getActionsAvailable().get(actioncompose))
        );
    }
    @Test
    void testBuyActionComposeInsufficientMarketQuantity() {
        ActionSimple comp1 = new ActionSimple("comp1");
        comp1.enrgCours(jour, 100);

        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(comp1, 100.0);
        ActionCompose actioncompose = new ActionCompose("Mono", composition);

        Marche.getActionsAvailable().put(actioncompose, 2);

        boolean result = investisseur.buyActionCompose(actioncompose, 3);

        assertAll(
                () -> assertFalse(result),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(actioncompose)),
                () -> assertEquals(2, Marche.getActionsAvailable().get(actioncompose))
        );
    }

    @Test
    void testBuyActionComposeInsufficientBalance() {
        ActionSimple comp1 = new ActionSimple("comp1");
        comp1.enrgCours(jour, 100);

        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(comp1, 100.0);
        ActionCompose actioncompose = new ActionCompose("Mono", composition);

        investisseur.setBalance(50); //pas assez de flouze

        Marche.getActionsAvailable().put(actioncompose, 5);

        boolean result = investisseur.buyActionCompose(actioncompose, 1);

        assertAll(
                () -> assertFalse(result),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(actioncompose)),
                () -> assertEquals(5, Marche.getActionsAvailable().get(actioncompose))
        );
    }

    @Test
    void testBuyActionComposeNoValueForDay() {
        ActionSimple comp1 = new ActionSimple("comp1");
        // aucune valeur enregistrée

        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(comp1, 100.0);
        ActionCompose actioncompose = new ActionCompose("NoVal", composition);

        Marche.getActionsAvailable().put(actioncompose, 5);

        boolean result = investisseur.buyActionCompose(actioncompose, 1);

        assertAll(
                () -> assertFalse(result),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(actioncompose)),
                () -> assertEquals(5, Marche.getActionsAvailable().get(actioncompose))
        );
    }
    @Test
    void testSellActionComposeSuccess() {
        ActionSimple comp1 = new ActionSimple("comp1");
        comp1.enrgCours(jour, 100);
        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(comp1, 100.0);

        ActionCompose actioncompose = new ActionCompose("Solo", composition);
        investisseur.getWallet().addAction(actioncompose, 3);
        Marche.getActionsAvailable().put(actioncompose, 2); // avant vente

        boolean result = investisseur.sellActionCompose(actioncompose, 2);

        float valeur = actioncompose.valeur(jour);

        assertAll(
                () -> assertTrue(result),
                () -> assertEquals(1000 + 2 * valeur, investisseur.getBalance()),
                () -> assertEquals(1, investisseur.getWallet().getActions().get(actioncompose)),
                () -> assertEquals(4, Marche.getActionsAvailable().get(actioncompose))
        );
    }
    @Test
    void testSellActionComposeNotOwned() {
        ActionSimple comp1 = new ActionSimple("comp1");
        comp1.enrgCours(jour, 100);
        Map<ActionSimple, Double> composition = new HashMap<>();
        composition.put(comp1, 100.0);

        ActionCompose actioncompose = new ActionCompose("Solo", composition);
        Marche.getActionsAvailable().put(actioncompose, 5);

        boolean result = investisseur.sellActionCompose(actioncompose, 1);

        assertAll(
                () -> assertFalse(result),
                () -> assertEquals(1000, investisseur.getBalance()),
                () -> assertFalse(investisseur.getWallet().getActions().containsKey(actioncompose)),
                () -> assertEquals(5, Marche.getActionsAvailable().get(actioncompose))
    );
    }

}