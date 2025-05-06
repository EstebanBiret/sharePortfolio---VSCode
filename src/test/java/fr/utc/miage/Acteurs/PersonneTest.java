/*
 * Copyright 2025 Esteban BIRET-TOSCANO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PersonneTest {

    private final String NAME = "John";
    private final String FIRST_NAME = "Doe";
    private final String PASSWORD = "password"; 

    @Test
    void testSetFirstName() {
        Personne personne = new Personne(NAME, FIRST_NAME, PASSWORD) {
        };
        String newFirstName = "Jane";
        personne.setFirstName(newFirstName);
        assertEquals(newFirstName, personne.getFirstName()); 
    }

    @Test
    void testSetName() {
        Personne personne = new Personne(NAME, FIRST_NAME, PASSWORD) {
        };
        String newName = "Smith";
        personne.setName(newName);
        assertEquals(newName, personne.getName()); 
    }
}
