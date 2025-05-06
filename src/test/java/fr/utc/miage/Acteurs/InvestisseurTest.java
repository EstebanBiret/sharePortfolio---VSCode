package fr.utc.miage.Acteurs;

import static org.junit.jupiter.api.Assertions.*;

import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Market.Marche;

import java.util.HashMap;
import java.util.Map;

public class InvestisseurTest {

    private Investisseur investisseur;
    private ActionSimple action;
    private Marche marche;
    private Jour jour;

    @BeforeEach
    void setUp() {
        investisseur = new Investisseur("John", "Doe", "password", 1000);
        action = new ActionSimple("Tisseo");
        jour = Jour.getActualJour();

        // Enregistre la valeur de l'action pour ce jour
        action.enrgCours(jour, 150);

        // Ajoute l'action au marché avec 10 exemplaires
        HashMap<Action, Integer> actions = new HashMap<>();
        actions.put(action, 10);
        marche = new Marche(actions);
    }


    @Test
    void testBuyAction_Success() {
        boolean result = investisseur.buyAction(action, 5);
        assertTrue(result);
        assertEquals(1000 - 150f * 5, investisseur.getBalance(), 0.01f);
        assertEquals(5, investisseur.getWallet().getActions().get(action));
    }

    @Test
    void testBuyAction_InsufficientBalance() {
        investisseur.setBalance(100f);
        boolean result = investisseur.buyAction(action, 1);
        assertFalse(result);
        assertEquals(100f, investisseur.getBalance(), 0.01f);
    }

    // Autres tests...
}
