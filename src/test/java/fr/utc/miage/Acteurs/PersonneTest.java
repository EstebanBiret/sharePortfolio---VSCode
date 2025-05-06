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
