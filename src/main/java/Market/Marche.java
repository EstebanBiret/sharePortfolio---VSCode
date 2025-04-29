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

    public Marche() {
        this.actionsAvailable = new HashMap<>();
    }

    public Marche(HashMap<Action, Integer> actionsAvailable) {
        this.actionsAvailable = actionsAvailable;
    }

    public HashMap<Action, Integer> getActionsAvailable() {
        return actionsAvailable;
    }

}
