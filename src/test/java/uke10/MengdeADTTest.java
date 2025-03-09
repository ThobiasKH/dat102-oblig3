package uke10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class MengdeADTTest {
    
    private LenketMengde<Integer> lenketMengde;
    private JavaSetToMengde<Integer> javaSetToMengde;

    @BeforeEach
    void setUp() {
        lenketMengde = new LenketMengde<>();
        javaSetToMengde = new JavaSetToMengde<>();
    }

    @Test
    void testLeggTilOgAntallElementer() {
        lenketMengde.leggTil(1);
        lenketMengde.leggTil(2);
        assertEquals(2, lenketMengde.antallElementer());

        javaSetToMengde.leggTil(1);
        javaSetToMengde.leggTil(2);
        assertEquals(2, javaSetToMengde.antallElementer());
    }

    @Test
    void testInneholder() {
        lenketMengde.leggTil(1);
        assertTrue(lenketMengde.inneholder(1));
        assertFalse(lenketMengde.inneholder(2));

        javaSetToMengde.leggTil(1);
        assertTrue(javaSetToMengde.inneholder(1));
        assertFalse(javaSetToMengde.inneholder(2));
    }

    @Test
    void testFjern() {
        lenketMengde.leggTil(1);
        assertEquals(1, lenketMengde.fjern(1));
        assertNull(lenketMengde.fjern(2));

        javaSetToMengde.leggTil(1);
        assertEquals(1, javaSetToMengde.fjern(1));
        assertNull(javaSetToMengde.fjern(2));
    }

    @Test
    void testErDelmengdeAv() {
        lenketMengde.leggTil(1);
        lenketMengde.leggTil(2);

        JavaSetToMengde<Integer> other = new JavaSetToMengde<>();
        other.leggTil(1);
        other.leggTil(2);
        other.leggTil(3);

        assertTrue(lenketMengde.erDelmengdeAv(other));
        assertFalse(other.erDelmengdeAv(lenketMengde));
    }

    @Test
    void testErLik() {
        lenketMengde.leggTil(1);
        lenketMengde.leggTil(2);

        JavaSetToMengde<Integer> other = new JavaSetToMengde<>();
        other.leggTil(2);
        other.leggTil(1);

        assertTrue(lenketMengde.erLik(other));
    }

    @Test
    void testErDisjunkt() {
        lenketMengde.leggTil(1);
        lenketMengde.leggTil(2);

        JavaSetToMengde<Integer> other = new JavaSetToMengde<>();
        other.leggTil(3);
        other.leggTil(4);

        assertTrue(lenketMengde.erDisjunkt(other));

        other.leggTil(2);
        assertFalse(lenketMengde.erDisjunkt(other));
    }

    @Test
    void testErTom() {
        assertTrue(lenketMengde.erTom());
        assertTrue(javaSetToMengde.erTom());

        lenketMengde.leggTil(1);
        javaSetToMengde.leggTil(1);

        assertFalse(lenketMengde.erTom());
        assertFalse(javaSetToMengde.erTom());
    }
}
