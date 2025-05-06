package fr.utc.miage.Acteurs;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import fr.utc.miage.Market.Marche;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;

public class GestionnaireTest {

    private final String NAME_GESTIONNAIRE1 = "Gestionnaire1";
    private final String FIRST_NAME_GESTIONNAIRE1 = "FirstNameGestionnaire1";
    private final String PASSWORD_GESTIONNAIRE1 = "Password";

    private final Action ACTION1 = new ActionSimple("Action1");

    @Test
    void TestConstructeurGestionnaire(){
        //WHEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1,FIRST_NAME_GESTIONNAIRE1,PASSWORD_GESTIONNAIRE1);
        //THEN
        Assertions.assertAll(
            () -> Assertions.assertEquals(gestionnaire.getName(), NAME_GESTIONNAIRE1),
            () -> Assertions.assertEquals(gestionnaire.getFirstName(), FIRST_NAME_GESTIONNAIRE1)
        );
    }

    @Test
    void testCreateActionWithNonExistantAction() {
        //GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        //WHEN 
        gestionnaire.createAction(ACTION1, 10);

        //THEN

        Assertions.assertAll(
            () -> Assertions.assertTrue(Marche.getActionsAvailable().containsKey(ACTION1)),
            () -> Assertions.assertEquals(10, Marche.getActionsAvailable().get(ACTION1))
        );

        Marche.clearActionsAvailable(); // Clear the actions available on the market for the next test

    }

    @Test
    void testCreateActionWithExistantAction() {

        //GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        gestionnaire.createAction(ACTION1, 5);

        //WHEN 

        gestionnaire.createAction(ACTION1, 10);

        //THEN

        Assertions.assertAll(
            () -> Assertions.assertTrue(Marche.getActionsAvailable().containsKey(ACTION1)),
            () -> Assertions.assertEquals(15, Marche.getActionsAvailable().get(ACTION1))
        );
        Marche.clearActionsAvailable(); // Clear the actions available on the market for the next test

    }

    @Test
    void testDeleteActionWhenActionExists() {
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        gestionnaire.createAction(ACTION1, 10);

        // WHEN
        boolean result = gestionnaire.deleteAction(ACTION1);

        // THEN
        Assertions.assertAll(
            () -> Assertions.assertTrue(result),
            () -> Assertions.assertFalse(Marche.getActionsAvailable().containsKey(ACTION1))
        );

        Marche.clearActionsAvailable();
    }

    @Test
    void testDeleteActionWhenActionDoesNotExist() {
        // GIVEN
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        // WHEN
        boolean result = gestionnaire.deleteAction(ACTION1);

        // THEN
        Assertions.assertFalse(result);
    }

   @Test
    void testUpdateActionWhenActionExists() {
        // GIVEN 
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);
        Marche.getActionsAvailable().put(ACTION1, 100);

        // WHEN 
        boolean result = gestionnaire.updateAction(ACTION1);

        // THEN 
        assertAll(
            () -> assertTrue(result),
            () -> assertEquals(0, Marche.getActionsAvailable().get(ACTION1))
        );
    }

    @Test
    void testUpdateActionWhenActionDoesNotExist() {
        // GIVEN 
        Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        // WHEN 
        boolean result = gestionnaire.updateAction(ACTION1);

        // THEN 
        assertFalse(result);
    }

    @Test
    void testUpdateActionWithNull() {
         // GIVEN 
         Gestionnaire gestionnaire = new Gestionnaire(NAME_GESTIONNAIRE1, FIRST_NAME_GESTIONNAIRE1, PASSWORD_GESTIONNAIRE1);

        // WHEN 
        boolean result = gestionnaire.updateAction(null);

        // THEN 
        assertFalse(result);
    }

   
}
