/*
 * Copyright 2025 Segot-Laberou Maxime, Junfang He;.
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
     * Get the actions available on the market
     * @return the actions available on the market
     */
    public HashMap<Action, Integer> getActionsAvailable() {
        return actionsAvailable;
    }

}
