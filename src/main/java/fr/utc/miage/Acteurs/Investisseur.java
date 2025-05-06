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

import Market.Marche;
import fr.utc.miage.shares.Action;
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
     * @param solde the balance of the investor
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
     * @param solde the balance of the investor
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
     * @param solde the balance of the investor
     */
    public void setBalance(float solde) {
        balance = solde;
    }

    /**
     * Allows to consult the wallet of the investor
     * @return the wallet of the investor
     */
    public Portefeuille getWallet() {
        return wallet;
    }

    /**
     * Allows to buy an action
     * @param action the action to buy
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
     * @param action the action to sell
     * @param quantity the quantity of actions to sell
     * @return true if the sale was made, false otherwise
     */

    public boolean sellAction(Action action, int quantity) {
        // TODO Auto-generated method stub
        return false;
    }
    
}