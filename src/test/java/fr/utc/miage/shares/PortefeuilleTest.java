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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
 
public class PortefeuilleTest {

    /*
     * Test of constructor, of class Portefeuille.
     */
    @Test
    public void testPortefeuille() {
        System.out.println("Portefeuille");
        Portefeuille instance = new Portefeuille();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    /**
     * Test of getActions method, of class Portefeuille.
     */
    @Test
    public void testGetActions() {
        System.out.println("getActions");
        Portefeuille instance = null;
        Map<Action, Integer> expResult = null;
        Map<Action, Integer> result = instance.getActions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAction method, of class Portefeuille.
     */
    @Test
    public void testAddAction() {
        System.out.println("addAction");
        Action action = null;
        int quantity = 0;
        Portefeuille instance = null;
        boolean expResult = false;
        boolean result = instance.addAction(action, quantity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}