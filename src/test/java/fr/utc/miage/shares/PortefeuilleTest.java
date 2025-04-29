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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
 
public class PortefeuilleTest {

    private static final ActionSimple ACTION1 = new ActionSimple("France2");
    private static final ActionSimple ACTION2 = new ActionSimple("Tesla");
    private static final int QUANTITY1 = 10;
    private static final int QUANTITY2 = 20;
    private static final Map<Action, Integer> ACTIONS = Map.of(ACTION1, QUANTITY1, ACTION2, QUANTITY2);

    /*
     * Test of constructor, of class Portefeuille.
     */
    @Test
    public void testPortefeuille() {
        Portefeuille instance = new Portefeuille();
        assertEquals(0, instance.getActions().size(), "The portefeuille should be empty after creation");
    }

    /*
     * Test of constructor, of class Portefeuille with actions.
     */
    @Test
    public void testPortefeuilleMap() {
        Map<Action, Integer> actions = Map.of(ACTION1, QUANTITY1, ACTION2, QUANTITY2);
        Portefeuille instance = new Portefeuille(actions);
        assertEquals(2, instance.getActions().size(), "The portefeuille should contain 2 actions after creation");
    }
    
    /**
     * Test of getActions method, of class Portefeuille.
     */
    @Test
    public void testGetActions() {
        Portefeuille instance = new Portefeuille(ACTIONS);
        Map<Action, Integer> result = instance.getActions();
        assertEquals(ACTIONS, result);
    }

    /**
     * Test of setAction method with negative quantity, of class Portefeuille.
     */
    @Test
    public void testSetActions() {
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
    public void testAddActionExistingAction() {
        Portefeuille portefeuille = new Portefeuille(ACTIONS);
        portefeuille.addAction(ACTION1, 5);
        /*assertAll(
                () -> assertTrue(portefeuille.addAction(ACTION1, 5)),
                () -> assertEquals(QUANTITY1 + 5, portefeuille.getActions().get(ACTION1))
                
        );*/
        assertEquals(QUANTITY1 + 5, portefeuille.getActions().get(ACTION1));
    }
}