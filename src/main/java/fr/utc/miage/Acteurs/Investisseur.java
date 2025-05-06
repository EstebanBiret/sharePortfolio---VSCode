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
     * Constructor of the Investisseur class
     * @param nom the name of the investor
     * @param prenom the first name of the investor
     * @param password the password of the investor
     * @param solde the balance of the investor
     */

    public Investisseur(String nom, String prenom, String password, float solde) {
        super(nom, prenom, password);
        this.balance = solde;
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
 * Achète une action et l’ajoute au portefeuille de l'investisseur.
 * Le coût est déduit du solde de l’investisseur.
 *
 * @param p le portefeuille de l'investisseur
 * @param action l'action à acheter
 * @param quantite la quantité à acheter
 * @param jour le jour pour évaluer la valeur de l'action
 * @return true si l’achat a été effectué, false sinon
 */
public boolean buyAction(Portefeuille p, Action action, int quantite, Jour jour) {
    if (quantite <= 0) {
        throw new IllegalArgumentException("La quantité doit être positive.");
    }

    float valeurTotale = action.valeur(jour) * quantite;

    if (valeurTotale > getBalance()) {
        return false; // Fonds insuffisants
    }

    boolean ajoutee = p.addAction(action, quantite);
    if (ajoutee) {
        setBalance(getBalance() - valeurTotale); // Déduction du solde
        return true;
    }

    return false;
}


/**
 * Vend une action du portefeuille de l'investisseur.
 * La méthode met à jour le solde de l'investisseur selon la valeur de l'action.
 *
 * @param p le portefeuille de l'investisseur
 * @param action l'action à vendre
 * @param quantite la quantité à vendre
 * @param jour le jour auquel on évalue la valeur de l'action
 * @return true si la vente a été effectuée, false sinon
 */
public boolean sellAction(Portefeuille portefeuille, Action action, int quantity, Jour jour) {
    if (quantity <= 0) {
        throw new IllegalArgumentException("La quantité doit être positive.");
    }

    int currentQuantity = portefeuille.getQuantity(action);
    if (currentQuantity < quantity) {
        return false; 
    }

    float valeur = action.valeur(jour);
    portefeuille.removeAction(action, quantity);
    balance += valeur * quantity;
    return true;
}




}
