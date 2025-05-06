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

package Market;

import java.util.HashMap;

import fr.utc.miage.shares.Action;

/**
 * This class represents an action market.
 * 
 * @author Maxime Segot-Laberou, Junfang He
 */
public class Marche {

    /**
     * Actions available on the market
     */
    private static HashMap<Action, Integer> actionsAvailable;


    /**
     * Constructor of the Marche class
     */
    public Marche() {
        Marche.actionsAvailable = new HashMap<>();
    }

    /**
     * Constructor of the Marche class
     * @param actionsAvailable the actions available on the market
     */
    public Marche(HashMap<Action, Integer> actionsAvailable) {
        Marche.actionsAvailable = actionsAvailable;
    }

    /**
     * Get the actions available on the market
     * @return the actions available on the market
     */
    public HashMap<Action, Integer> getActionsAvailable() {
        return actionsAvailable;
    }

    /**
     * Set the actions available on the market
     * @param actionsAvailable the actions available on the market
     */
    public void setActionsAvailable(HashMap<Action, Integer> actionsAvailable) {
        Marche.actionsAvailable = actionsAvailable;
    }

    /**
     * Allows to know if an action is available on the market with a given quantity
     * @param action the action to check
     * @param quantity the quantity of the action to check
     * @return true if the action is available on the market with the given quantity, false otherwise
     */
    public static boolean isActionAvailableWithQuantity(Action action, int quantity) {
        if (actionsAvailable.containsKey(action)) {
            return actionsAvailable.get(action) >= quantity;
        }
        return false;
    }

    /**
     * update the quantity of an action on the market
     * @param action the action to update
     * @param quantity the quantity of the action to update
     * @param isIncrease true if the quantity is increased, false if it is decreased
     * @return true if the action is updated, false otherwise
     */
    public static boolean updateActionQuantity(Action action, int quantity, boolean isIncrease) {
        if (actionsAvailable.containsKey(action)) {
            if (isIncrease) {
                actionsAvailable.put(action, actionsAvailable.get(action) + quantity);
            } else {
                if (actionsAvailable.get(action) >= quantity) {
                    actionsAvailable.put(action, actionsAvailable.get(action) - quantity);
                } else {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}