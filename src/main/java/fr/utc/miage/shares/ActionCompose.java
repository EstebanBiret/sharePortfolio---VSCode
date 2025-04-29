package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

public class ActionCompose extends Action {

    // Nouvelle structure : chaque ActionSimple a un pourcentage
    private Map<ActionSimple, Double> composition;

    protected ActionCompose(String libelle, Map<ActionSimple, Double> composition) {
        super(libelle);
        this.composition = composition;
    }

    public Map<ActionSimple, Double> getComposition() {
        return composition;
    }

    public void setComposition(Map<ActionSimple, Double> composition) {
        this.composition = composition;
    }

    @Override
    public float valeur(Jour j) {
        float total = 0f;
        for (Map.Entry<ActionSimple, Double> entry : composition.entrySet()) {
            ActionSimple action = entry.getKey();
            Double pourcentage = entry.getValue();
            total += action.valeur(j) * (pourcentage / 100);
        }
        return total;
    }

    // Méthode pour retourner les pourcentages de composition
    public Map<ActionSimple, Double> pourcentage() {
        return this.composition;
    }

}
