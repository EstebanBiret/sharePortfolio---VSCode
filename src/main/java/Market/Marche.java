package Market;

import java.util.HashMap;

import fr.utc.miage.shares.Action;

/**
 * This class represents an action market.
 * 
 * @author Maxime Segot-Laberou, Junfang He
 */
public class Marche {

    /**
     * Actions available on the market
     */
    private static HashMap<Action, Integer> actionsAvailable;


    /**
     * Constructor of the Marche class
     */
    public Marche() {
        this.actionsAvailable = new HashMap<>();
    }

    /**
     * Constructor of the Marche class
     * @param actionsAvailable the actions available on the market
     */
    public Marche(HashMap<Action, Integer> actionsAvailable) {
        this.actionsAvailable = actionsAvailable;
    }

    /**
     * Allows to add an action to the market
     * @param action the action to add
     * @param quantity the quantity of actions to add
     */
    public HashMap<Action, Integer> getActionsAvailable() {
        return actionsAvailable;
    }

}
