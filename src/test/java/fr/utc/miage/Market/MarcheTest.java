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

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;

public class MarcheTest {
    private final Action ACTION1 = new ActionSimple("Action1");
    private final Action ACTION2 = new ActionSimple("Action2");

    private final Action ACTION3 = new ActionSimple("Action3");
    private final Action ACTION4 = new ActionSimple("Action4");
    private final Action ACTION5 = new ActionSimple("Action5");

    @Test
    void testAfficheActionAvailable() {
        //GIVEN
        Marche marche = new Marche();
        Marche.getActionsAvailable().put(ACTION1, 20);

        // WHEN
        String result = marche.displaysActionAvailable();

        // THEN
        assertTrue(result.contains("Action1 : 20"));
        assertTrue(result.startsWith("Actions disponibles sur le marché"));
        Marche.clearActionsAvailable();
    }

    @Test
    void testAfficheActionAvailableMultiple() {
        //GIVEN
        Marche marche = new Marche();
        Marche.getActionsAvailable().put(ACTION1, 20);
        Marche.getActionsAvailable().put(ACTION2, 50);

        // WHEN
        String result = marche.displaysActionAvailable();

        // THEN
        assertTrue(result.contains("Action1 : 20"));
        assertTrue(result.contains("Action2 : 50"));
        Marche.clearActionsAvailable();
    }

    @Test
    void testAfficheActionAvailableEmpty() {
        //GIVEN
        HashMap<Action, Integer> actionsAvailable = new HashMap<>();
        Marche marche = new Marche(actionsAvailable);

        // WHEN
        String result = marche.displaysActionAvailable();

        // THEN
        assertEquals("Aucune action disponible sur le marché.", result);
        Marche.clearActionsAvailable();
    }

    @Test
    void testClearActionsAvailable() {
        //GIVEN
        Marche.getActionsAvailable().put(ACTION1, 20);
        Marche.getActionsAvailable().put(ACTION2, 50);

        // WHEN
        Marche.clearActionsAvailable();

        // THEN
        assertTrue(Marche.getActionsAvailable().isEmpty());

    }

    @Test
    void testGetActionsAvailable() {
        //GIVEN
        Marche.getActionsAvailable().put(ACTION1, 20);
        Marche.getActionsAvailable().put(ACTION2, 50);

        // WHEN
        HashMap<Action, Integer> actionsAvailable = Marche.getActionsAvailable();

        // THEN
        assertEquals(20, actionsAvailable.get(ACTION1));
        assertEquals(50, actionsAvailable.get(ACTION2));
        Marche.clearActionsAvailable();
    }

    @Test
    void testTriActionsAvalablesWithNameAsc(){
        //GIVEN
        Marche.getActionsAvailable().put(ACTION2, 20);
        Marche.getActionsAvailable().put(ACTION1, 50);
        Marche.getActionsAvailable().put(ACTION3, 10);
        Marche.getActionsAvailable().put(ACTION5, 5);
        Marche.getActionsAvailable().put(ACTION4, 15);

        // WHEN
        ArrayList<Action> actions = Marche.triActionsAvailable("nom",true);

        // THEN
        Assertions.assertAll(
            () -> assertEquals(ACTION1, actions.get(0)),
            () -> assertEquals(ACTION2, actions.get(1)),
            () -> assertEquals(ACTION3, actions.get(2)),
            () -> assertEquals(ACTION4, actions.get(3)),
            () -> assertEquals(ACTION5, actions.get(4))
        );

        Marche.clearActionsAvailable();

    }
    @Test
    void testTriActionsAvalablesWithNameDsc(){
        //GIVEN
        Marche.getActionsAvailable().put(ACTION2, 20);
        Marche.getActionsAvailable().put(ACTION1, 50);
        Marche.getActionsAvailable().put(ACTION3, 10);
        Marche.getActionsAvailable().put(ACTION5, 5);
        Marche.getActionsAvailable().put(ACTION4, 15);

        // WHEN
        ArrayList<Action> actions = Marche.triActionsAvailable("nom",false);

        // THEN
        Assertions.assertAll(
            () -> assertEquals(ACTION1, actions.get(4)),
            () -> assertEquals(ACTION2, actions.get(3)),
            () -> assertEquals(ACTION3, actions.get(2)),
            () -> assertEquals(ACTION4, actions.get(1)),
            () -> assertEquals(ACTION5, actions.get(0))
        );
        Marche.clearActionsAvailable();
    }

    @Test
    void testTriActionsAvalablesWithQuantityAsc(){
        //GIVEN
        Marche.getActionsAvailable().put(ACTION2, 20);
        Marche.getActionsAvailable().put(ACTION1, 50);
        Marche.getActionsAvailable().put(ACTION3, 10);
        Marche.getActionsAvailable().put(ACTION5, 5);
        Marche.getActionsAvailable().put(ACTION4, 15);

        // WHEN
        ArrayList<Action> actions = Marche.triActionsAvailable("quantite",true);

        // THEN
        Assertions.assertAll(
            () -> assertEquals(ACTION5, actions.get(0)),
            () -> assertEquals(ACTION3, actions.get(1)),
            () -> assertEquals(ACTION4, actions.get(2)),
            () -> assertEquals(ACTION2, actions.get(3)),
            () -> assertEquals(ACTION1, actions.get(4))
        );
        Marche.clearActionsAvailable();
    }

    @Test
    void testTriActionsAvalablesWithQuantityDsc(){
        //GIVEN
        Marche.getActionsAvailable().put(ACTION2, 20);
        Marche.getActionsAvailable().put(ACTION1, 50);
        Marche.getActionsAvailable().put(ACTION3, 10);
        Marche.getActionsAvailable().put(ACTION5, 5);
        Marche.getActionsAvailable().put(ACTION4, 15);

        // WHEN
        ArrayList<Action> actions = Marche.triActionsAvailable("quantite",false);

        // THEN
        Assertions.assertAll(
            () -> assertEquals(ACTION5, actions.get(4)),
            () -> assertEquals(ACTION3, actions.get(3)),
            () -> assertEquals(ACTION4, actions.get(2)),
            () -> assertEquals(ACTION2, actions.get(1)),
            () -> assertEquals(ACTION1, actions.get(0))
        );
        Marche.clearActionsAvailable();
    }

    @Test
    void testTriActionsSimpleAvalablesWithPriceAsc(){
        //GIVEN
        ActionSimple actionSimple1 = new ActionSimple("Action1");
        ActionSimple actionSimple2 = new ActionSimple("Action2");
        ActionSimple actionSimple3 = new ActionSimple("Action3");
        ActionSimple actionSimple4 = new ActionSimple("Action4");
        ActionSimple actionSimple5 = new ActionSimple("Action5");

        actionSimple1.enrgCours(Jour.getActualJour(), 10.0f);
        actionSimple2.enrgCours(Jour.getActualJour(), 20.0f);
        actionSimple3.enrgCours(Jour.getActualJour(), 30.0f);
        actionSimple4.enrgCours(Jour.getActualJour(), 40.0f);
        actionSimple5.enrgCours(Jour.getActualJour(), 50.0f);
        
        Marche.getActionsAvailable().put(actionSimple2, 20);
        Marche.getActionsAvailable().put(actionSimple1, 50);
        Marche.getActionsAvailable().put(actionSimple4, 10);
        Marche.getActionsAvailable().put(actionSimple5, 5);
        Marche.getActionsAvailable().put(actionSimple3, 15);

        // WHEN
        ArrayList<Action> actions = Marche.triActionsAvailable("prix",true);

        // THEN
        Assertions.assertAll(
            () -> assertEquals(actionSimple1, actions.get(0)),
            () -> assertEquals(actionSimple2, actions.get(1)),
            () -> assertEquals(actionSimple3, actions.get(2)),
            () -> assertEquals(actionSimple4, actions.get(3)),
            () -> assertEquals(actionSimple5, actions.get(4))
        );
        Marche.clearActionsAvailable();
    }

    @Test
    void testTriActionsSimpleAvalablesWithPriceDsc(){
        //GIVEN
        ActionSimple actionSimple1 = new ActionSimple("Action1");
        ActionSimple actionSimple2 = new ActionSimple("Action2");
        ActionSimple actionSimple3 = new ActionSimple("Action3");
        ActionSimple actionSimple4 = new ActionSimple("Action4");
        ActionSimple actionSimple5 = new ActionSimple("Action5");

        actionSimple1.enrgCours(Jour.getActualJour(), 10.0f);
        actionSimple2.enrgCours(Jour.getActualJour(), 20.0f);
        actionSimple3.enrgCours(Jour.getActualJour(), 30.0f);
        actionSimple4.enrgCours(Jour.getActualJour(), 40.0f);
        actionSimple5.enrgCours(Jour.getActualJour(), 50.0f);
        
        Marche.getActionsAvailable().put(actionSimple2, 20);
        Marche.getActionsAvailable().put(actionSimple1, 50);
        Marche.getActionsAvailable().put(actionSimple4, 10);
        Marche.getActionsAvailable().put(actionSimple5, 5);
        Marche.getActionsAvailable().put(actionSimple3, 15);

        // WHEN
        ArrayList<Action> actions = Marche.triActionsAvailable("prix",false);

        // THEN
        Assertions.assertAll(
            () -> assertEquals(actionSimple5, actions.get(0)),
            () -> assertEquals(actionSimple4, actions.get(1)),
            () -> assertEquals(actionSimple3, actions.get(2)),
            () -> assertEquals(actionSimple2, actions.get(3)),
            () -> assertEquals(actionSimple1, actions.get(4))
        );
        Marche.clearActionsAvailable();
    }

    @Test
    void testSetActionsAvailable() {
        // GIVEN
        HashMap<Action, Integer> newActions = new HashMap<>();
        newActions.put(ACTION1, 100);
        newActions.put(ACTION2, 200);

        // WHEN
        Marche marche = new Marche();
        marche.setActionsAvailable(newActions);

        // THEN
        HashMap<Action, Integer> actions = Marche.getActionsAvailable();
        assertEquals(100, actions.get(ACTION1));
        assertEquals(200, actions.get(ACTION2));
        Marche.clearActionsAvailable();
    }

    @Test
    void testIsActionAvailableWithQuantityTrue() {
        // GIVEN
        Marche.getActionsAvailable().put(ACTION1, 30);

        // WHEN
        boolean result = Marche.isActionAvailableWithQuantity(ACTION1, 20);

        // THEN
        assertTrue(result);
        Marche.clearActionsAvailable();
    }

    @Test
    void testIsActionAvailableWithQuantityFalseNotEnough() {
        // GIVEN
        Marche.getActionsAvailable().put(ACTION1, 10);

        // WHEN
        boolean result = Marche.isActionAvailableWithQuantity(ACTION1, 20);

        // THEN
        Assertions.assertFalse(result);
        Marche.clearActionsAvailable();
    }

    @Test
    void testGetActionByLibelle() {
        //GIVEN
        Marche marche = new Marche();
        Marche.getActionsAvailable().put(ACTION1, 20);
        Marche.getActionsAvailable().put(ACTION2, 50);

        // WHEN
        Action action = Marche.getActionByLibelle("Action1");

        // THEN
        assertEquals(ACTION1, action);
        Marche.clearActionsAvailable();
    }
  
    @Test
    void testIsActionAvailableWithQuantityFalseNotPresent() {
        // GIVEN
        // Ne pas insérer d’action dans le marché

        // WHEN
        boolean result = Marche.isActionAvailableWithQuantity(ACTION1, 10);

        // THEN
        Assertions.assertFalse(result);
        Marche.clearActionsAvailable();
    }

    @Test
    void testGetActionByLibelleNotFound() {
        //GIVEN
        Marche marche = new Marche();
        Marche.getActionsAvailable().put(ACTION1, 20);
        Marche.getActionsAvailable().put(ACTION2, 50);

        // WHEN
        Action action = Marche.getActionByLibelle("Action3");

        // THEN
        assertEquals(null, action);
        Marche.clearActionsAvailable();
    }

    @Test
    void testUpdateActionQuantityIncrease() {
        // GIVEN
        Marche.getActionsAvailable().put(ACTION1, 10);

        // WHEN
        boolean updated = Marche.updateActionQuantity(ACTION1, 5, true);

        // THEN
        assertTrue(updated);
        assertEquals(15, Marche.getActionsAvailable().get(ACTION1));
        Marche.clearActionsAvailable();
    }

    @Test
    void testUpdateActionQuantityDecreaseSuccess() {
        // GIVEN
        Marche.getActionsAvailable().put(ACTION1, 20);

        // WHEN
        boolean updated = Marche.updateActionQuantity(ACTION1, 10, false);

        // THEN
        assertTrue(updated);
        assertEquals(10, Marche.getActionsAvailable().get(ACTION1));
        Marche.clearActionsAvailable();
    }

    @Test
    void testUpdateActionQuantityDecreaseFail() {
        // GIVEN
        Marche.getActionsAvailable().put(ACTION1, 5);

        // WHEN
        boolean updated = Marche.updateActionQuantity(ACTION1, 10, false);

        // THEN
        Assertions.assertFalse(updated);
        assertEquals(5, Marche.getActionsAvailable().get(ACTION1)); // Pas de changement
        Marche.clearActionsAvailable();
    }

    @Test
    void testUpdateActionQuantityActionNotPresent() {
        // GIVEN
        // Aucun ajout préalable

        // WHEN
        boolean updated = Marche.updateActionQuantity(ACTION1, 5, true);

        // THEN
        Assertions.assertFalse(updated);
        assertTrue(Marche.getActionsAvailable().isEmpty());
        Marche.clearActionsAvailable();
    }

    @Test
    public void testTriActionsAvailableInvalidMethod() {
        ArrayList<Action> result = Marche.triActionsAvailable("invalide", true);
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Le résultat devrait être une liste vide pour une méthode de tri invalide.");
    }

}
