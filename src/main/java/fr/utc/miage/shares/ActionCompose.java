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
 import java.util.Map;
 
 public class ActionCompose extends Action {
 
     /**
      * The composition of this composite action
      * Each simple action is associated with a percentage (from 0 to 100)
      */
     private Map<ActionSimple, Double> composition;
 
     /**
      * Builds a composite action from a label and a map of simple actions with their corresponding percentages
      *
      * @param libelle the name of the composite action
      * @param composition simple actions and their percentage weights
      */
     protected ActionCompose(String libelle, Map<ActionSimple, Double> composition) {
         super(libelle);
         this.composition = composition;
     }
 
     /**
      * Returns the current composition of this composite action
      *
      * @return a map of simple actions and their associated percentages
      */
     public Map<ActionSimple, Double> getComposition() {
         return composition;
     }
 
     /**
      * Updates the composition of this composite action.
      *
      * @param composition the new simple actions and their percentage weights
      */
     public void setComposition(Map<ActionSimple, Double> composition) {
         this.composition = composition;
     }
 
     /**
      * Computes the value of this composite action for a given day {@code j}.
      *
      * @param j the day for which to compute the value
      * @return the computed value of the composite action
      */
     @Override
     public float valeur(Jour j) {
         float total = 0f;
         for (Map.Entry<ActionSimple, Double> entry : composition.entrySet()) {
             ActionSimple action = entry.getKey();
             Double percentage = entry.getValue();
             total += action.valeur(j) * (percentage / 100);
         }
         return total;
     }
 
     /**
      * Returns the current percentage breakdown of the composition
      *
      * @return a map of simple actions with their percentage weights
      */
     public Map<ActionSimple, Double> pourcentage() {
         return this.composition;
     }
 
     /**
      * Modifies the percentage breakdown of this composite action.
      *
      * @param nouvelleComposition the new composition map
      */
     public void modifierComposition(Map<ActionSimple, Double> nouvelleComposition) {
         this.composition = nouvelleComposition;
     }
     
 }