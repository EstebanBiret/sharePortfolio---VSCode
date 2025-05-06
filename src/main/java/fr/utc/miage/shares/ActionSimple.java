/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
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

/**
 * Allows the creation of simple Action objects.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionSimple extends Action {

    /**
     * Default value for the action object.
     */
    private static final int DEFAULT_ACTION_VALUE = 0;

    /**
     * Map of the action object and its value for each day.
     */
    private final Map<Jour, Float> mapCours;

    /**
     * Constructor of the ActionSimple class.
     * 
     * @param libelle the name of the action object
     */
    public ActionSimple(final String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap<>();
    }

    /**
     * Adds a value to the action object for a given day.
     * If the value is already set for this day, it is not modified.
     * 
     * @param j the day for which the value is set
     * @param v the value of the action for the given day
     */
    public void enrgCours(final Jour j, final float v) {
        if (!this.mapCours.containsKey(j)) {
            this.mapCours.put(j, v);
        }
    }

    /**
     * Provides the value of the action object for a given day.
     * If the value is not set for this day, the default value is returned.
     * 
     * @param j the day for which the value is requested
     * @return the value of the action for the given day
     */
    @Override
    public float valeur(final Jour j) {
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }
}
