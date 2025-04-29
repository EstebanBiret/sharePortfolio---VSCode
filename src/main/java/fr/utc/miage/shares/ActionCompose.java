/*
 * Copyright 2025 Esteban BIRET-TOSCANO Flavien DIAS;
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

    // Méthode pour modifier les pourcentages de composition
    public void modifierComposition(Map<ActionSimple, Double> nouvelleComposition) {
        this.composition = nouvelleComposition;
    }
}
