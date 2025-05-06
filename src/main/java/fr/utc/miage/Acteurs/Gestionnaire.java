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
     * @return true if the action was created, false otherwise
     */
    public boolean createAction(Action action) {
        // TODO Auto-generated method stub
        return false;
    }
    /**
     * Allows to delete an action
     * @param action the name of the action
     * @return true if the action was deleted, false otherwise
     */

    public boolean deleteAction(Action action) {
        // TODO Auto-generated method stub
        return false;
    }
     /**
     * Allows to update an action
     * @param action the name of the action
     * @return true if the action was updated   , false otherwise
     */

    public boolean updateActionSimple(Action action) {
        // TODO Auto-generated method stub
        return false;
    }


}
