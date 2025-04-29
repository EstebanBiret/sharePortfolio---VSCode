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

public abstract class Personne {

    /**
     * Name of the person
     */
    private String name;

    /**
    * First name of the person
    */
    private String firstName;

    /**
     * Password of the person
     */
    private String password;

    /**
     * Constructor of the Personne class
     * @param nom the name of the person
     * @param prenom the first name of the person
     * @param password the password of the person
     */
    protected Personne(String nom, String prenom, String password) {
        this.name = nom;
        this.firstName = prenom;
        this.password = password;
    }


    /**
     * Allows to consult the name of the person
     * @return the name of the person
     */
    public String getName() {
        return name;
    }
    /**
     * Allows to set the name of the person
     */
    
    public void setName(String nom) {
        this.name = nom;
    }

    /**
     * Allows to consult the first name of the person
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Allows to set the first name of the person
     */
    public void setFirstName(String prenom) {
        this.firstName = prenom;
    }
}
