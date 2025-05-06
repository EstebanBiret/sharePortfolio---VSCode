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

import fr.utc.miage.Market.Marche;
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

}