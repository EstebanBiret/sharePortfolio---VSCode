package fr.utc.miage.shares;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

        Assertions.assertFalse(action1.equals(null));
    }

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
