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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.utc.miage.shares.ActionSimple;

import fr.utc.miage.shares.Action;

public class MarcheTest {
    private final Action ACTION1 = new ActionSimple("Action1");
    private final Action ACTION2 = new ActionSimple("Action2");

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
        Marche marche = new Marche();
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
        Marche marche = new Marche();
        Marche.getActionsAvailable().put(ACTION1, 20);
        Marche.getActionsAvailable().put(ACTION2, 50);

        // WHEN
        HashMap<Action, Integer> actionsAvailable = Marche.getActionsAvailable();

        // THEN
        assertEquals(20, actionsAvailable.get(ACTION1));
        assertEquals(50, actionsAvailable.get(ACTION2));

    }
}
