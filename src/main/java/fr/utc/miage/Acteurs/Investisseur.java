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

import java.util.Map;

import fr.utc.miage.Market.Marche;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionCompose;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Portefeuille;

/**
 * This class represents an investor.
 * 
 * @author Maxime Segot-Laberou, Junfang He
 */

public class Investisseur extends Personne {

    /**
     * Balance of the investor
     */
    private float balance;

    /**
     * Wallet of the investor
     */
    private Portefeuille wallet;


    /**
     * Constructor of the Investisseur class
     * @param nom the name of the investor
     * @param prenom the first name of the investor
     * @param password the password of the investor
     * @param balance the balance of the investor
     */

    public Investisseur(String nom, String prenom, String password, float balance) {
        super(nom, prenom, password);
        this.balance = balance;
        this.wallet = new Portefeuille();
    }

    /**
     * Constructor of the Investisseur class
     * @param nom the name of the investor
     * @param prenom the first name of the investor
     * @param password the password of the investor
     * @param balance the balance of the investor
     * @param wallet the wallet of the investor
     */

     public Investisseur(String nom, String prenom, String password, float balance, Portefeuille wallet) {
        super(nom, prenom, password);
        this.balance = balance;
        this.wallet = wallet;
    }

    /**
     * Allows to consult the balance of the investor
     * @return the balance of the investor
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Allows to set the balance of the investor
     * @param balance the balance of the investor
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * Allows to consult the wallet of the investor
     * @return the wallet of the investor
     */
    public Portefeuille getWallet() {
        return wallet;
    }

    /**
     * Allows to set the wallet of the investor
     * @param wallet the wallet of the investor
     */
    public void setWallet(Portefeuille wallet) {
        this.wallet = wallet;
    }

    /**
     * Allows to buy an action
     * @param actionSimple the action to buy
     * @param quantity the quantity of actions to buy
     * @return true if the purchase was made, false otherwise
     */
    
    public boolean buyActionSimple(ActionSimple actionSimple, int quantity) {
        //on vérifie si l'action est disponible sur le marché avec la quantité demandée
        if(Marche.isActionAvailableWithQuantity(actionSimple, quantity)) {

            //récupérer la valeur de l'action pour le jour du système
            Jour jour = Jour.getActualJour();
            float value = actionSimple.valeur(jour);

            //l'action n'a pas de valeur pour le jour actuel
            if (value == 0) return false;

            //on vérifie si l'investisseur a assez d'argent pour acheter l'action en quantité demandée
            if (balance >= value * quantity) {
                //on achète l'action
                wallet.addAction(actionSimple, quantity);
                //on retire le montant de l'achat du solde de l'investisseur
                balance -= value * quantity;
                //on retire l'action du marché
                Marche.updateActionQuantity(actionSimple, quantity, false);
                return true;
            } else {
                return false;
            }
        }
        return false; //l'action n'est pas disponible sur le marché avec la quantité demandée
    }

    /**
     * Allows to sell an action
     * @param actionSimple the action to sell
     * @param quantity the quantity of actions to sell
     * @return true if the sale was made, false otherwise
     */
    public boolean sellActionSimple(ActionSimple actionSimple, int quantity) {
        // Vérifie que l'investisseur possède assez d'actions à vendre
        if (wallet.getQuantity(actionSimple) < quantity) return false;

        // Récupère la valeur de l'action pour le jour actuel
        Jour jour = Jour.getActualJour();
        float value = actionSimple.valeur(jour);

        // Si la valeur est nulle, on ne peut pas vendre
        if (value == 0) return false;

        // Ajoute l'argent au solde de l'investisseur
        balance += value * quantity;

        // Retire les actions du portefeuille
        wallet.removeAction(actionSimple, quantity);

        // Ajoute les actions sur le marché
        Marche.updateActionQuantity(actionSimple, quantity, true);

        return true;
    }

    /**
     * Achète une action et l’ajoute au portefeuille de l'investisseur.
     * Le coût est déduit du solde de l’investisseur.
     *
     * @param actionCompose l'action à acheter
     * @param quantity la quantité à acheter
     * @return true si l’achat a été effectué, false sinon
     */
    public boolean buyActionCompose(ActionCompose actionCompose, int quantity) {
        //on vérifie si l'action est disponible sur le marché avec la quantité demandée
        if(Marche.isActionAvailableWithQuantity(actionCompose, quantity)) {

            //récupérer la valeur de l'action pour le jour du système
            Jour jour = Jour.getActualJour();
            float value = actionCompose.valeur(jour);

            //l'action n'a pas de valeur pour le jour actuel
            if (value == 0) return false;

            //on vérifie si l'investisseur a assez d'argent pour acheter l'action en quantité demandée
            if (balance >= value * quantity) {
                //on achète l'action
                wallet.addAction(actionCompose, quantity);
                //on retire le montant de l'achat du solde de l'investisseur
                balance -= value * quantity;
                //on retire l'action du marché
                Marche.updateActionQuantity(actionCompose, quantity, false);
                return true;
            } else {
                return false;
            }
        }
        return false; //l'action n'est pas disponible sur le marché avec la quantité demandée
    }

    /**
     * Vend une action du portefeuille de l'investisseur.
     * La méthode met à jour le solde de l'investisseur selon la valeur de l'action.
     *
     * @param actionCompose l'action à vendre
     * @param quantity la quantité à vendre
     * @return true si la vente a été effectuée, false sinon
     */
    public boolean sellActionCompose( ActionCompose actionCompose, int quantity) {
        // Vérifie que l'investisseur possède assez d'actions à vendre
        if (wallet.getQuantity(actionCompose) < quantity) return false;

        // Récupère la valeur de l'action pour le jour actuel
        Jour jour = Jour.getActualJour();
        float value = actionCompose.valeur(jour);

        // Si la valeur est nulle, on ne peut pas vendre
        if (value == 0) return false;

        // Ajoute l'argent au solde de l'investisseur
        balance += value * quantity;

        // Retire les actions du portefeuille
        wallet.removeAction(actionCompose, quantity);

        // Ajoute les actions sur le marché
        Marche.updateActionQuantity(actionCompose, quantity, true);

        return true;
    }
    /**
     * Allows to consult the value of an action in the wallet for a given day
     * @param actionSimple the action to consult
     * @param jour the day to consult
     * 
     * @return the value of the action for the given day, 0 if the action has no value for the given day
     */
    public float getValueOfActionForGivenDay(ActionSimple actionSimple, Jour jour) {
        return actionSimple.valeur(jour);
    }

    /**
     * Get total value of the wallet
     * @return the total value of the wallet
     */

    public float getTotalValueOfWallet() {
        float totalValue = 0;
        for (Map.Entry<Action,Integer> entry : wallet.getActions().entrySet()) {
            Action action = entry.getKey();
            totalValue += action.valeur(Jour.getActualJour()) * wallet.getQuantity(action);
        }
        return totalValue;
    }

}