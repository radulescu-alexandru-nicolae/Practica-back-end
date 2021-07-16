package frizerie.controll;

import frizerie.model.Frizer;
import frizerie.model.SQLUTIL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlFrizerTest {
    ControlFrizer controlFrizer = new ControlFrizer();

    @Test
    void addFrizer() {
        Frizer frizer = new Frizer("Melania", "Anca", 8574);
        controlFrizer.addFrizer(frizer);
    }

    @Test
    void deleteFrizer() {
        controlFrizer.deleteFrizer("Melania", "Anca");

    }

    @Test
    void afisareClientiZi() {
    }

    @Test
    void afisareClientiFrizer() {
        controlFrizer.afisareClientiFrizer("Melania", "Anca");
    }

    @Test
    void updateFrizer() {
    }

    @Test
    void alegereConcediu() {
        assertEquals(false, controlFrizer.alegereConcediu("Andu", "Alex", "2021-09-11", 7));
    }
}

