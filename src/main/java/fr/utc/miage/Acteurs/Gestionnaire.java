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

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.utc.miage.Market.Marche;
import fr.utc.miage.Market.Transaction;
import fr.utc.miage.shares.Action;

/**
 * This class represents a manager.
 * 
 * @author Maxime Segot-Laberou, Junfang He
 */
public class Gestionnaire extends Personne {

    /**
     * Constructor of the Gestionnaire class
     * @param nom the name of the manager
     * @param prenom the first name of the manager
     * @param password the password of the manager
     */
    public Gestionnaire(String nom, String prenom, String password) {
        super(nom, prenom, password);
    }

    /**
     * Allows to create an action
     * @param action the name of the action
     * @param quantity the quantity of actions to create
     */
    public void createAction(Action action, int quantity) {
        if(Marche.getActionsAvailable().containsKey(action)) {
            // If the action already exists, we update its quantity
            int currentQuantity = Marche.getActionsAvailable().get(action);
            Marche.getActionsAvailable().put(action, currentQuantity + quantity);
        } else {
            Marche.getActionsAvailable().put(action, quantity);
        } 
    }

    /**
     * Allows to delete an action
     * @param action the name of the action
     * @return true if the action was deleted, false otherwise
     */

    public boolean deleteAction(Action action) {
        if (Marche.getActionsAvailable().containsKey(action)) {
        Marche.getActionsAvailable().remove(action);
        return true;
    }
    return false;
    }
     /**
     * Allows to update an action
     * @param action the name of the action
     * @param quantity the quantity of actions to update
     * @return true if the action was updated   , false otherwise
     */
    public boolean updateAction(Action action, int quantity) {
        if (action == null) return false;
        if (Marche.getActionsAvailable().containsKey(action)) {
            Marche.getActionsAvailable().put(action, quantity);
            return true;
        }
        return false;
    }

    /**
     * Calculates and returns a formatted string showing the most traded action
     * for each day, month, or year based on the given period.
     *
     * @param transactions the list of all transactions
     * @param periode  "jour", "mois", or "annee"
     * @return a formatted string showing the most traded action for each period
     */
    public String consultBestAction(List<Transaction> transactions, String periode) {
        if (transactions == null || transactions.isEmpty()) {
            return "No transactions found.";
        }
        
        DateTimeFormatter formatter = switch (periode.toLowerCase()) {
            case "jour" -> DateTimeFormatter.ofPattern("yyyy-MM-dd");
            case "mois" -> DateTimeFormatter.ofPattern("yyyy-MM");
            case "annee" -> DateTimeFormatter.ofPattern("yyyy");
            default -> throw new IllegalArgumentException("Invalid period: must be 'jour', 'mois', or 'annee'");
         };

        Map<String, Map<Action, Integer>> grouped = new HashMap<>();

        for (Transaction tx : transactions) {
            String key = tx.getDate().format(formatter);
            grouped.putIfAbsent(key, new HashMap<>());
            Map<Action, Integer> subMap = grouped.get(key);
            subMap.put(tx.getAction(), subMap.getOrDefault(tx.getAction(), 0) + tx.getQuantity());
        }

        StringBuilder result = new StringBuilder("Most traded actions by ").append(periode).append(":\n");

        for (var entry : grouped.entrySet()) {
            String date = entry.getKey();
            Map<Action, Integer> actions = entry.getValue();

            Action topAction = Collections.max(actions.entrySet(), Map.Entry.comparingByValue()).getKey();
            int maxQuantity = actions.get(topAction);

            result.append("- ")
                .append(date)
                .append(" : ")
                .append(topAction.getLibelle())
                .append(" (")
                .append(maxQuantity)
                .append(" unités)\n");
        }

        return result.toString();
    }

}