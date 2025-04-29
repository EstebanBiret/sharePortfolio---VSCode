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

package fr.utc.miage.shares;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
 
public class PortefeuilleTest {

    private static final ActionSimple ACTION1 = new ActionSimple("France2");
    private static final ActionSimple ACTION2 = new ActionSimple("Tesla");
    private static final int QUANTITY1 = 10;
    private static final int QUANTITY2 = 20;
    private static final Map<Action, Integer> ACTIONS = Map.of(ACTION1, QUANTITY1, ACTION2, QUANTITY2);
    private static final ActionSimple ASTERION_LE_BON = new ActionSimple("Asterion");
    /*
     * Test of constructor, of class Portefeuille.
     */
    @Test
    public void testPortefeuilleShouldWork() {
        Portefeuille instance = new Portefeuille();
        assertEquals(0, instance.getActions().size());
    }

    /*
     * Test of constructor, of class Portefeuille with actions.
     */
    @Test
    public void testPortefeuilleMapShouldWork() {
        Map<Action, Integer> actions = Map.of(ACTION1, QUANTITY1, ACTION2, QUANTITY2);
        Portefeuille instance = new Portefeuille(actions);
        assertEquals(2, instance.getActions().size());
    }
    
    /**
     * Test of getActions method, of class Portefeuille.
     */
    @Test
    public void testGetActionsShouldWork() {
        Portefeuille instance = new Portefeuille(ACTIONS);
        Map<Action, Integer> result = instance.getActions();
        assertEquals(ACTIONS, result);
    }

    /**
     * Test of setAction method with negative quantity, of class Portefeuille.
     */
    @Test
    public void testSetActionshouldWork() {
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.setActions(ACTIONS);
        Map<Action, Integer> result = portefeuille.getActions();
        assertEquals(ACTIONS, result);
    }

    /**
     * Test of addAction method, of class Portefeuille.
     */
    @Test
    public void testAddActionShouldWork() {
        Portefeuille portefeuille = new Portefeuille();
        assertTrue(portefeuille.addAction(ACTION1, QUANTITY1));
    }

    /**
     * Test of addAction method with negative quantity, of class Portefeuille.
     */
    @Test
    public void testAddActionNegativeQuantity() {
        Portefeuille portefeuille = new Portefeuille();
        assertFalse(portefeuille.addAction(ACTION1, -1));
    }

    /**
     * Test of addAction method with zero quantity, of class Portefeuille.
     */
    @Test
    public void testAddActionZeroQuantity() {
        Portefeuille portefeuille = new Portefeuille();
        assertFalse(portefeuille.addAction(ACTION1, 0));
    }

    /**
     * Test of addAction method with existing action, of class Portefeuille.
     */
    @Test
    public void testAddActionExistingActionUpdateQuantity() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertAll(
                () -> assertTrue(portefeuille.addAction(ACTION1, 5)),
                () -> assertEquals(QUANTITY1 + 5, portefeuille.getActions().get(ACTION1))
        );
    }

    /*
     * Test of removeAction method, of class Portefeuille.
     */
    @Test
    public void testRemoveActionShouldWork() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertAll(
                () -> assertEquals(ACTION1, portefeuille.removeAction(ACTION1, 5)),
                () -> assertEquals(QUANTITY1 - 5, portefeuille.getActions().get(ACTION1))
        );
    }

    /*
     * Test of removeAction method with negative quantity, of class Portefeuille.
     */
    @Test
    public void testRemoveActionNegativeQuantity() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertNull(portefeuille.removeAction(ACTION1, -1));
    }

    /*
     * Test of removeAction method with zero quantity, of class Portefeuille.
     */
    @Test
    public void testRemoveActionZeroQuantity() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertNull(portefeuille.removeAction(ACTION1, 0));
    }

    /*
     * Test of removeAction method with non-existing action, of class Portefeuille.
     */
    @Test
    public void testRemoveActionNonExistingAction() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertNull(portefeuille.removeAction(ASTERION_LE_BON, 5));
    }

    /*
     * Test of removeAction method with quantity greater than available or equal, of class Portefeuille.
     * Should remove the action of the wallet
     */
    @Test
    public void testRemoveActionGreaterThanAvailableOrEqual() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertAll(
                () -> assertEquals(ACTION1, portefeuille.removeAction(ACTION1, 10)),
                () -> assertFalse(portefeuille.getActions().containsKey(ACTION1)),
                () -> assertEquals(ACTION2, portefeuille.removeAction(ACTION2, 69)),
                () -> assertFalse(portefeuille.getActions().containsKey(ACTION2))
        );
    }

    /*
     * Test of getQuantity method, of class Portefeuille.
     */
    @Test
    public void testGetQuantityShouldWork() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertAll(
                () -> assertEquals(QUANTITY1, portefeuille.getQuantity(ACTION1)),
                () -> assertEquals(QUANTITY2, portefeuille.getQuantity(ACTION2))
        );
    }

    /*
     * Test of getQuantity method when the action doesn't exist, of class Portefeuille.
     */
    @Test
    public void testGetQuantityNonExistingAction() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        assertEquals(-1, portefeuille.getQuantity(ASTERION_LE_BON));
    }
}