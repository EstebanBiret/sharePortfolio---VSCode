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
import java.util.List;
import java.util.stream.Stream;

import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.Jour;

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
    public static HashMap<Action, Integer> getActionsAvailable() {
        if(actionsAvailable == null) {
            actionsAvailable = new HashMap<>();
        }
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

    /**
     * Get an action by its libelle
     * @param libelle the libelle of the action to get
     * @return the action with the given libelle, null if not found
     */
    public static Action getActionByLibelle(String libelle) {
        for (Action action : actionsAvailable.keySet()) {
            if (action.getLibelle().equals(libelle)) {
                return action;
            }
        }
        return null;
    }

    /**
     * Clear the actions available on the market
     */
    public static void clearActionsAvailable() {
        actionsAvailable.clear();
    }

     /**
     * Displays the actions available on the market.
     * @return a string listing available actions and their quantites
     */
    public String displaysActionAvailable(){
    if (actionsAvailable.isEmpty()) return "Aucune action disponible sur le marché.";

    StringBuilder result = new StringBuilder("Actions disponibles sur le marché:\n");
    
    actionsAvailable.forEach(
        (action, quantity) -> result.append("- ")
                                .append(action.getLibelle())
                                .append(" : ")
                                .append(quantity)
                                .append(" unités\n")
    );
    return result.toString();
    }

    /**
     * Sorts the actions available on the market according to a given method.
     * @param triMethod the method to sort the actions either by "nom", "quantite" or "prix"
     * @param isAscendant true if the sorting is ascending, false if it is descending
     * @return a list of actions sorted according to the given method
     */
    public static ArrayList<Action> triActionsAvailable(String triMethod, boolean isAscendant) {
        if (actionsAvailable.isEmpty()) return new ArrayList<>();

        ArrayList<Action> actions = new ArrayList<>();
        final String method = triMethod.toLowerCase();

        Stream<Action> actionsStream = actionsAvailable.keySet().stream();
        switch (method) {
            case "nom":
                if (!isAscendant) {
                    actionsStream = actionsStream.sorted((a1, a2) -> -1 * a1.getLibelle().compareTo(a2.getLibelle()));
                }
                else actionsStream = actionsStream.sorted((a1, a2) -> a1.getLibelle().compareTo(a2.getLibelle()));
                break;
            case "quantite":
                if (!isAscendant) {
                    actionsStream = actionsStream.sorted((a1, a2) -> -1 * actionsAvailable.get(a1).compareTo(actionsAvailable.get(a2)));
                }
                else actionsStream = actionsStream.sorted((a1, a2) -> actionsAvailable.get(a1).compareTo(actionsAvailable.get(a2)));
                break;
            case "prix":
                if (!isAscendant) {
                    actionsStream = actionsStream.sorted((a1, a2) -> -1 * Double.compare(a1.valeur(Jour.getActualJour()), a2.valeur(Jour.getActualJour())));
                }
                else actionsStream = actionsStream.sorted((a1, a2) -> Double.compare(a1.valeur(Jour.getActualJour()), a2.valeur(Jour.getActualJour())));
                break;
            default:
                return new ArrayList<>();
        }

        actionsStream.forEach(actions::add);
        
        
        return actions;
    }

}
