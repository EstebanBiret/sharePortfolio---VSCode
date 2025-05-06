package fr.utc.miage.shares;

import fr.utc.miage.Acteurs.Investisseur;
import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Portefeuille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvestisseurTest {

    private Investisseur investisseur;
    private Portefeuille portefeuille;
    private Action actionTest;
    private Jour jour;

    @BeforeEach
    public void setUp() {
        investisseur = new Investisseur("Doe", "John", "password123", 1000f);
        portefeuille = new Portefeuille();
        actionTest = new Action("TST") {
            @Override
            public float valeur(Jour jour) {
                return 10f;
            }
        };
        jour = new Jour(2025, 100);
    }

    @Test
    public void testBuyActionSuccess() {
        boolean result = investisseur.buyAction(portefeuille, actionTest, 50, jour);
        assertTrue(result);
        assertEquals(500f, investisseur.getBalance(), 0.01);
        assertEquals(50, portefeuille.getQuantity(actionTest));
    }

    @Test
    public void testBuyActionFailsWhenNotEnoughBalance() {
        boolean result = investisseur.buyAction(portefeuille, actionTest, 200, jour); // 2000 > 1000
        assertFalse(result);
        assertEquals(1000f, investisseur.getBalance(), 0.01);
        assertEquals(-1, portefeuille.getQuantity(actionTest));
    }

    @Test
    public void testSellActionSuccess() {
        investisseur.buyAction(portefeuille, actionTest, 20, jour);
        float balanceAfterBuy = investisseur.getBalance();

        boolean result = investisseur.sellAction(portefeuille, actionTest, 10, jour);
        assertTrue(result);
        assertEquals(balanceAfterBuy + 100f, investisseur.getBalance(), 0.01);
        assertEquals(10, portefeuille.getQuantity(actionTest));
    }

    @Test
    public void testSellActionFailsWhenQuantityTooHigh() {
        investisseur.buyAction(portefeuille, actionTest, 5, jour);
        boolean result = investisseur.sellAction(portefeuille, actionTest, 10, jour);
        assertFalse(result);
        assertEquals(5, portefeuille.getQuantity(actionTest));
    }

    @Test
    public void testBuyActionThrowsExceptionWhenNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () ->
            investisseur.buyAction(portefeuille, actionTest, -1, jour)
        );
    }

    @Test
    public void testSellActionThrowsExceptionWhenNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () ->
            investisseur.sellAction(portefeuille, actionTest, -1, jour)
        );
    }
}
