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
     * Allows to buy an action
     * @param action the action to buy
     * @param quantity the quantity of actions to buy
     * @return true if the purchase was made, false otherwise
     */
    
    public boolean buyAction(Action action, int quantity) {
        // TODO Auto-generated method stub
        return false;
    }


    /**
     * Allows to sell an action
     * @param action the action to sell
     * @param quantity the quantity of actions to sell
     * @return true if the sale was made, false otherwise
     */

    public boolean vendreAction(Action action, int quantity) {
        // TODO Auto-generated method stub
        return false;
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
     */
    public void setBalance(float solde) {
        balance = solde;
    }
    
}
