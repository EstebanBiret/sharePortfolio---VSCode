/*
 * Copyright 2025 Esteban BIRET-TOSCANO;
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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActionComposeTest {

    private static final String FOO_COMPOSEE1 = "Foo Composee 1";
    private static final String FOO_COMPOSEE2 = "Foo Composee 2";

    @Test
    void testGetLibelleReturnConstructorParameter() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        final String result = action.getLibelle();

        Assertions.assertEquals(FOO_COMPOSEE1, result,
                "Property Libelle value should be the same as the parameter used for construction");
    }

    @Test
    void testToStringReturnConstructorParameter() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        final String result = action.toString();

        Assertions.assertEquals(FOO_COMPOSEE1, result,
                "toString() should return the libelle");
    }

    @Test
    void testEqualsWithSameObject() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);

        Assertions.assertTrue(action.equals(action));
    }

    @Test
    void testEqualsWithSimilarObject() {
        final ActionCompose action1 = new ActionComposeImpl(FOO_COMPOSEE1);
        final ActionCompose action2 = new ActionComposeImpl(FOO_COMPOSEE1);

        Assertions.assertTrue(action1.equals(action2));
    }

    @Test
    void testEqualsWithDifferentObject() {
        final ActionCompose action1 = new ActionComposeImpl(FOO_COMPOSEE1);
        final ActionCompose action2 = new ActionComposeImpl(FOO_COMPOSEE2);

        Assertions.assertFalse(action1.equals(action2));
    }

    @Test
    void testEqualsWithNullObject() {
        final ActionCompose action1 = new ActionComposeImpl(FOO_COMPOSEE1);

        Assertions.assertFalse(action1 == null);
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    void testEqualsWithObjectFromOtherClass() {
        final ActionCompose action1 = new ActionComposeImpl(FOO_COMPOSEE1);
        final Integer other = 0;

        Assertions.assertFalse(action1.equals(other));
    }

    @Test
    void testHashCode() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        Assertions.assertDoesNotThrow(action::hashCode, "hashcode must always provide a value");
    }

    @Test
    void testGetComposition() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        Map<ActionSimple, Double> composition = action.getComposition();

        Assertions.assertEquals(2, composition.size(), "Composition should contain two elements");
    }

    @Test
    void testSetComposition() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);

        Map<ActionSimple, Double> newComposition = new HashMap<>();
        newComposition.put(new DummyActionSimple("A3"), 100.0);

        action.setComposition(newComposition);

        Assertions.assertEquals(1, action.getComposition().size(), "Composition should be updated to one element");
        Assertions.assertTrue(action.getComposition().containsKey(new DummyActionSimple("A3")));
    }

    @Test
    void testValeur() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        float valeur = action.valeur(Jour.getActualJour());

        Assertions.assertEquals(0.0f, valeur, "Valeur should be 0.0 since DummyActionSimple returns 0.0");
    }

    @Test
    void testPourcentage() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        Map<ActionSimple, Double> pourcentage = action.pourcentage();

        Assertions.assertEquals(2, pourcentage.size(), "Pourcentage should return the original composition");
    }

    @Test
    void testModifierComposition() {
        final ActionCompose action = new ActionComposeImpl(FOO_COMPOSEE1);
        Map<ActionSimple, Double> newComp = new HashMap<>();
        newComp.put(new DummyActionSimple("A4"), 100.0);

        action.modifierComposition(newComp);

        Assertions.assertEquals(1, action.getComposition().size(), "Composition should contain only one element after modification");
    }


    private static class DummyActionSimple extends ActionSimple {
        public DummyActionSimple(String libelle) {
            super(libelle);
        }

        @Override
        public float valeur(Jour j) {
            return 0.0f;
        }
    }

    private static class ActionComposeImpl extends ActionCompose {

        public ActionComposeImpl(String libelle) {
            super(libelle, createComposition());
        }

        private static Map<ActionSimple, Double> createComposition() {
            Map<ActionSimple, Double> map = new HashMap<>();
            map.put(new DummyActionSimple("A1"), 50.0);
            map.put(new DummyActionSimple("A2"), 50.0);
            return map;
        }
    }
}
