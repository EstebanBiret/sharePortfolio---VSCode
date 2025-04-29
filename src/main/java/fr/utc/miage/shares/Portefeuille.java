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

import java.util.HashMap;
import java.util.Map;

public class Portefeuille {

    private Map <Action, Integer> actions;

    /*
     * Constructeur par défaut de la classe Portefeuille
     * Crée un portefeuille vide
     */
    public Portefeuille() {
        this.actions = new HashMap<>();
    }

    /*
     * Constructeur de la classe Portefeuille
     * Crée un portefeuille avec une map d'actions
     * @param actions : Map d'actions (simples et composées) et de leur quantité
     */
    public Portefeuille(Map<Action, Integer> actions) {
        this.actions = actions;
    }

    /*
     * Récupérer la map d'actions du portefeuille
     * @return : Map d'actions (simples et composées) et de leur quantité
     */
    public Map<Action, Integer> getActions() {
        return actions;
    }

    /*
     * Définir la map d'actions du portefeuille
     * @param actions : Map d'actions (simples et composées) et de leur quantité
     */
    public void setActions(Map<Action, Integer> actions) {
        this.actions = actions;
    }

    /*
     * Ajouter une action au portefeuille en autant de quantité que nécessaire
     * @param action : Action à ajouter
     * @param quantity : Quantité d'actions à ajouter
     */
    public boolean addAction(Action action, int quantity) {
        if (quantity <= 0) {
            return false; //on ne peut pas ajouter une quantité négative ou nulle
        }
        if (actions.containsKey(action)) { //si l'action existe déjà, on incrémente la quantité
            actions.put(action, actions.get(action) + quantity);
        } else { //sinon, on l'ajoute avec la quantité spécifiée
            actions.put(action, quantity);
        }
        return true;
    }

    /*
     * Supprimer une action du portefeuille en autant de quantité que nécessaire
     * @param action : Action à supprimer
     * @param quantity : Quantité d'actions à supprimer
     */
    public Action removeAction(Action action, int quantity) {
        if (quantity <= 0) {
            return null; //on ne peut pas supprimer une quantité négative ou nulle
        }
        if (actions.containsKey(action)) { //si l'action existe déjà, on décrémente la quantité
            int newQuantity = actions.get(action) - quantity;
            if (newQuantity <= 0) { //si la quantité devient négative ou nulle, on supprime l'action
                actions.remove(action);
            } else {
                actions.put(action, newQuantity);
            }
        } else {
            return null; //l'action n'existe pas dans le portefeuille
        }
        return action;
    }

    /*
     * Récupérer la quantité d'une action dans le portefeuille
     * @param action : Action dont on veut connaître la quantité
     * @return : La quantité d'actions dans le portefeuille
     */
    public int getQuantity(Action action) {
        if (actions.containsKey(action)) { //si l'action existe déjà, on retourne la quantité
            return actions.get(action);
        } else {
            return 0; //l'action n'existe pas dans le portefeuille
        }
    }

}